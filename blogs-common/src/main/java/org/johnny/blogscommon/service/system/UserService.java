package org.johnny.blogscommon.service.system;

import org.johnny.blogscommon.entity.user.UserEntity;
import org.johnny.blogscommon.service.ConditionService;
import org.johnny.blogscommon.vo.common.PageVo;
import org.johnny.blogscommon.vo.requestvo.system.UserReqVo;
import org.johnny.blogscommon.vo.resultvo.system.UserResultVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * 用户Service
 *
 * @author johnny
 * @create 2020-07-13 下午4:56
 **/
public interface UserService extends ConditionService<UserEntity> {


    Page<UserResultVo> listByCondition(UserReqVo userReqVo, Pageable pageVo);

    void submitUser(UserReqVo userReqVo);

    UserResultVo findUserInfo(Long id);

    UserResultVo findByUserName(String userName);
}