package org.johnny.blogscommon.converter;


import org.johnny.blogscommon.entity.alipay.PayOrderInfo;
import org.johnny.blogscommon.entity.blog.BlogInfo;
import org.johnny.blogscommon.form.AlipayNotifyParam;
import org.johnny.blogscommon.utils.DateMapper;
import org.johnny.blogscommon.vo.blog.BlogInfoVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
 * BlogInfo Converter
 *
 * @author johnny
 * @create 2019-11-08 上午11:20
 **/
@Mapper(uses = DateMapper.class)

public interface PayOrderInfoConverter {

    PayOrderInfoConverter INSTANCE = Mappers.getMapper(PayOrderInfoConverter.class);

    PayOrderInfo notifyParamToDomain(AlipayNotifyParam alipayNotifyParam);


}