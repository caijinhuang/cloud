package com.cjh.common.response;

import com.cjh.common.response.dto.ResponseResult;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.AbstractMappingJacksonResponseBodyAdvice;

/**
 * @author cjh
 * @date 2020/3/10 16:31
 **/
@Log4j2
@RestControllerAdvice
public class ResponseAdvice extends AbstractMappingJacksonResponseBodyAdvice {

    @Override
    protected void beforeBodyWriteInternal(MappingJacksonValue bodyContainer,
                                           MediaType contentType,
                                           MethodParameter returnType,
                                           ServerHttpRequest request,
                                           ServerHttpResponse response) {
        Object value = bodyContainer.getValue();
        // 如果不是restful响应对象，强制包一层
        if (!(value instanceof ResponseResult)) {
            bodyContainer.setValue(ResponseResult.builder().data(value));
        }
    }

}
