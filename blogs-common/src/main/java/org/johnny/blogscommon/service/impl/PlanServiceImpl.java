package org.johnny.blogscommon.service.impl;

import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.johnny.blogscommon.converter.PlanConverter;
import org.johnny.blogscommon.entity.plan.Plan;
import org.johnny.blogscommon.entity.plan.QPlan;
import org.johnny.blogscommon.enums.PlanTypeEnum;
import org.johnny.blogscommon.form.PlanInfoForm;
import org.johnny.blogscommon.repository.plan.PlanRepository;
import org.johnny.blogscommon.service.PlanService;
import org.johnny.blogscommon.vo.plan.PlanChartVo;
import org.johnny.blogscommon.vo.plan.PlanVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * plan impl
 *
 * @author johnny
 * @create 2020-08-17 下午6:31
 **/
@Service
public class PlanServiceImpl implements PlanService {

    private JPAQueryFactory queryFactory;

    @PostConstruct
    public void init() {
        queryFactory = new JPAQueryFactory(entityManager);
    }

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private PlanRepository planRepository;


    @Override
    public Page<PlanVo> findPage(Pageable pageable, PlanInfoForm planInfoForm) {

        List<PlanVo> planVoList = new ArrayList<>();
        QPlan qPlan = QPlan.plan;

        QueryResults<Plan> queryResults = queryFactory.select(qPlan)
                .from(qPlan)
                .orderBy(qPlan.createTime.desc())
                .offset(pageable.getPageNumber() * pageable.getPageSize())
                .limit(pageable.getPageSize())
                .fetchResults();


        if (!queryResults.isEmpty()) {

            queryResults.getResults().forEach(plan -> {

                PlanVo planVo = PlanConverter.INSTANCE.domain2vo(plan);
                planVoList.add(planVo);
            });
        }
        return new PageImpl<>(planVoList, pageable, queryResults.getTotal());
    }

    @Override
    public PlanChartVo findPlanChartInfo() {

        QPlan qPlan = QPlan.plan;

        List<PlanChartVo.PlanType> planTypeList = new ArrayList<>();

        Arrays.asList(PlanTypeEnum.values()).forEach(planTypeEnum -> {
                    PlanChartVo.PlanType planType = new PlanChartVo.PlanType();
                    planType.setName(planTypeEnum.getMsg());
                    planType.setValue(0L);
                    planType.setCode(planTypeEnum.getValue());
                    planTypeList.add(planType);
                }
        );
        List<String> typeList = Arrays.asList(PlanTypeEnum.values()).stream().map(PlanTypeEnum::getMsg).collect(Collectors.toList());
        Map<String, PlanChartVo.PlanType> listMap = planTypeList.stream().collect(Collectors.toMap(PlanChartVo.PlanType::getName, planType -> planType));


        List<Tuple> tuples = queryFactory.select(qPlan.planType, qPlan.planType.count())
                .from(qPlan)
                .groupBy(qPlan.planType)
                .fetch();
        if (!tuples.isEmpty()) {
            tuples.forEach(tuple -> {
                String status = tuple.get(0, String.class);
                Long count = tuple.get(1, Long.class);
                PlanChartVo.PlanType planType = listMap.get(status);
                planType.setValue(count);
            });
        }
        PlanChartVo planChartVo = new PlanChartVo();
        planChartVo.setPlanTypeList(planTypeList);
        planChartVo.setTypeList(typeList);
        return planChartVo;
    }

    @Override
    public void addPlanInfo(PlanVo planVo) {
        Plan plan  =  PlanConverter.INSTANCE.vo2domain(planVo);
        planRepository.save(plan);
    }


}