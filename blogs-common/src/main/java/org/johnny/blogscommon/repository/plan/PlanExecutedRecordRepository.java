package org.johnny.blogscommon.repository.plan;

import org.johnny.blogscommon.entity.plan.PlanExecuteRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * 计划的执行 记录  Repository
 *
 * @author johnny
 * @create 2020-08-16 下午6:20
 **/
public interface PlanExecutedRecordRepository extends JpaRepository<PlanExecuteRecord, Long> {


    @Query(nativeQuery = true, value = "SELECT * FROM plan_execute_record WHERE plan_id = :id  and date_sub(curdate(), interval 7 day) <= date(create_date)")
    List<PlanExecuteRecord> findSevenDate(@Param("id") Long id);
}