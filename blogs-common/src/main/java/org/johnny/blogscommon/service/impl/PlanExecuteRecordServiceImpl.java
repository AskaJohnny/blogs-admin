package org.johnny.blogscommon.service.impl;


import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.johnny.blogscommon.converter.PlanExecuteRecordConverter;
import org.johnny.blogscommon.entity.plan.Plan;
import org.johnny.blogscommon.entity.plan.PlanExecuteRecord;
import org.johnny.blogscommon.entity.plan.QPlanExecuteRecord;
import org.johnny.blogscommon.enums.PlanExecuteRecordTypeEnum;
import org.johnny.blogscommon.form.PlanExecuteRecordInfoForm;
import org.johnny.blogscommon.repository.plan.PlanExecutedRecordRepository;
import org.johnny.blogscommon.repository.plan.PlanRepository;
import org.johnny.blogscommon.service.PlanExecuteRecordService;
import org.johnny.blogscommon.utils.DateUtils;
import org.johnny.blogscommon.vo.plan.PlanExecuteRecordChartVo;
import org.johnny.blogscommon.vo.plan.PlanExecuteRecordVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.*;
import java.util.stream.Collectors;

/**
 * plan impl
 *
 * @author johnny
 * @create 2020-08-17 下午6:31
 **/
@Service
public class PlanExecuteRecordServiceImpl implements PlanExecuteRecordService {

    private JPAQueryFactory queryFactory;

    @PostConstruct
    public void init() {
        queryFactory = new JPAQueryFactory(entityManager);
    }

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private PlanRepository planRepository;

    @Autowired
    private PlanExecutedRecordRepository planExecutedRecordRepository;

    @Override
    public Page<PlanExecuteRecordVo> findPage(Pageable pageable, PlanExecuteRecordInfoForm planDetailInfoForm) {

        List<PlanExecuteRecordVo> planExecuteRecordVoList = new ArrayList<>();
        QPlanExecuteRecord qPlanExecuteRecord = QPlanExecuteRecord.planExecuteRecord;

        JPAQuery jpaQuery = queryFactory.select(qPlanExecuteRecord)
                .from(qPlanExecuteRecord)
                .orderBy(qPlanExecuteRecord.createDate.desc())
                .offset(pageable.getPageNumber() * pageable.getPageSize())
                .limit(pageable.getPageSize());

        if (planDetailInfoForm.getPlanId() != null && planDetailInfoForm.getPlanId() != 0L) {
            jpaQuery.where(qPlanExecuteRecord.planId.eq(planDetailInfoForm.getPlanId()));
        }

        QueryResults<PlanExecuteRecord> queryResults = jpaQuery.fetchResults();
        if (!queryResults.isEmpty()) {
            queryResults.getResults().forEach(planExecuteRecord -> {
                PlanExecuteRecordVo planExecuteRecordVo = PlanExecuteRecordConverter.INSTANCE.domain2vo(planExecuteRecord);
                planExecuteRecordVoList.add(planExecuteRecordVo);
            });
        }
        return new PageImpl<>(planExecuteRecordVoList, pageable, queryResults.getTotal());
    }

    /**
     * 根据 plan id 查询 计划的 图表数据
     *
     * @param id
     * @return : PlanExecuteRecordChartVo 图表数据 的封装
     */
    @Override
    public PlanExecuteRecordChartVo findPlanExecuteRecordChartInfo(Long id) {
        PlanExecuteRecordChartVo planExecuteRecordChartVo = new PlanExecuteRecordChartVo();
        if (id != null && id != 0L) {
            List<PlanExecuteRecordChartVo.PlanTimeType> planTimeTypeList = new ArrayList<>();
            Plan plan = planRepository.findById(id).orElse(null);
            if (plan != null) {
                //计算当前剩余天数
                long betweenCount = DateUtils.betweenDate(new Date(), plan.getEndTime(), DateUtils.YEAR_MONTH_DAY_FORMAT);
                PlanExecuteRecordChartVo.PlanTimeType remainPlanTimeType = new PlanExecuteRecordChartVo.PlanTimeType();
                remainPlanTimeType.setName("剩余天数");
                remainPlanTimeType.setValue(betweenCount);
                planTimeTypeList.add(remainPlanTimeType);
                //
                Arrays.asList(PlanExecuteRecordTypeEnum.values()).forEach(planExecuteRecordTypeEnum -> {
                            PlanExecuteRecordChartVo.PlanTimeType planTimeType = new PlanExecuteRecordChartVo.PlanTimeType();
                            planTimeType.setName(planExecuteRecordTypeEnum.getMsg());
                            planTimeType.setValue(0L);
                            planTimeType.setCode(planExecuteRecordTypeEnum.getValue());
                            planTimeTypeList.add(planTimeType);
                        }
                );

                Map<String, PlanExecuteRecordChartVo.PlanTimeType> listMap = planTimeTypeList.stream().collect(Collectors.toMap(PlanExecuteRecordChartVo.PlanTimeType::getCode, planTimeType -> planTimeType));

                List<Tuple> tuples = queryFactory.select(QPlanExecuteRecord.planExecuteRecord.status, QPlanExecuteRecord.planExecuteRecord.status.count())
                        .from(QPlanExecuteRecord.planExecuteRecord)
                        .groupBy(QPlanExecuteRecord.planExecuteRecord.status)
                        .where(QPlanExecuteRecord.planExecuteRecord.planId.eq(id))
                        .fetch();
                if (!tuples.isEmpty()) {
                    tuples.forEach(tuple -> {
                        String status = tuple.get(0, String.class);
                        Long count = tuple.get(1, Long.class);
                        PlanExecuteRecordChartVo.PlanTimeType planTimeType = listMap.get(status);
                        planTimeType.setValue(count);
                    });
                }
                planExecuteRecordChartVo.setPlanTimeTypeList(planTimeTypeList);
            }
        }

        //SELECT * FROM plan_execute_record WHERE date_sub(curdate(), interval 7 day) <= date(create_date);


        List<String> dateList = new ArrayList<>();

        List<PlanExecuteRecord> planExecuteRecordList = planExecutedRecordRepository.findSevenDate(id);
        if (!CollectionUtils.isEmpty(planExecuteRecordList)) {
            planExecuteRecordList.forEach(planExecuteRecord -> {
                dateList.add(DateUtils.formatDate(planExecuteRecord.getCreateDate(),DateUtils.YEAR_MONTH_DAY_FORMAT));
                planExecuteRecordChartVo.getConsumeDateList().add(planExecuteRecord.getTakeTime());
            });
        }
        planExecuteRecordChartVo.setDateList(dateList);
        return planExecuteRecordChartVo;
    }

    @Override
    public void addPlanExecuteRecord(PlanExecuteRecordVo planExecuteRecordVo) {
        PlanExecuteRecord planExecuteRecord = PlanExecuteRecordConverter.INSTANCE.vo2domain(planExecuteRecordVo);
        planExecutedRecordRepository.save(planExecuteRecord);
    }

}