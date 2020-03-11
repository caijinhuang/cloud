package com.cjh.common.response;

import com.cjh.common.response.dto.ResponseResult;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.HandlerExecutionChain;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.mvc.method.annotation.AbstractMappingJacksonResponseBodyAdvice;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.util.List;

/**
 * @author cjh
 * @date 2020/3/10 16:31
 **/
@Log4j2
@RestControllerAdvice
public class ResponseAdvice extends AbstractMappingJacksonResponseBodyAdvice {

    @Autowired
    DispatcherServlet dispatcherServlet;

    @Override
    protected void beforeBodyWriteInternal(MappingJacksonValue bodyContainer,
                                           MediaType contentType,
                                           MethodParameter returnType,
                                           ServerHttpRequest request,
                                           ServerHttpResponse response) {
        HandlerExecutionChain handler = getMapperHandler(((ServletServerHttpRequest)request).getServletRequest());
        // 具有RestWrap注解的类的响应才会被包装。
        Annotation[] annotations = returnType.getContainingClass().getAnnotations();
        for (Annotation annotation : annotations) {
            if (annotation instanceof RestWrap) {
                populateBody(bodyContainer);
                return;
            }
        }

    }

    private void populateBody(MappingJacksonValue bodyContainer) {
        Object value = bodyContainer.getValue();
        // 如果不是restful响应对象，强制包一层
        if (!(value instanceof ResponseResult)) {
            bodyContainer.setValue(ResponseResult.builder().data(value));
        }
    }

    @SneakyThrows
    private HandlerExecutionChain getMapperHandler(HttpServletRequest request) {
        List<HandlerMapping> handlerMappings = dispatcherServlet.getHandlerMappings();
        if (handlerMappings != null) {
            for (HandlerMapping mapping : handlerMappings) {
                HandlerExecutionChain handler = mapping.getHandler(request);
                if (handler != null) {
                    return handler;
                }
            }
        }
        return null;
    }

}
