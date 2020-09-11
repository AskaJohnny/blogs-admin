package org.johnny.blogscommon.repository.plan;

import org.johnny.blogscommon.entity.plan.Plan;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * plan 的 repository
 *
 * @author johnny
 * @create 2020-08-16 下午6:19
 **/
public interface PlanRepository extends JpaRepository<Plan, Long> {



}