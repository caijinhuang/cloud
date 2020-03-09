package com.cjh.common.valid.enumvalid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;

/**
 * @author cjh
 * @date 2020/1/15 14:29
 **/

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = Range.InnerValid.class)
public @interface Range {

    String message();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    /**
     * 有效的枚举类型，优先级最高
     *
     * @return
     */
    Class<? extends RangeAdaptor> adaptor();

    /**
     * 是否允许为空值，空值则不做校验处理，直接跳过，非空则校验枚举。
     *
     * @return
     */
    boolean allowEmpty() default false;

    /**
     * 校验实现
     */
    class InnerValid implements ConstraintValidator<Range, Object> {

        private final static Logger LOGGER = LoggerFactory.getLogger(InnerValid.class);

        // 有效的枚举定义对象
        RangeAdaptor[] rangeAdaptors;
        // 是否允许为空
        boolean allowEmpty;

        @Override
        public void initialize(Range constraintAnnotation) {
            if (constraintAnnotation.adaptor() != null) {
                try {
                    Method method = constraintAnnotation.adaptor().getMethod("values");
                    allowEmpty = constraintAnnotation.allowEmpty();
                    this.rangeAdaptors = (RangeAdaptor[]) method.invoke(null, null);
                } catch (Exception e) {
                    LOGGER.error("校验初始化异常", e);
                }
            }
        }

        /**
         * 字段内容校验
         * @param value 校验内容
         * @param context 上下文
         * @return
         */
        @Override
        public boolean isValid(Object value, ConstraintValidatorContext context) {
            if (allowEmpty && value == null) {
                return true;
            }
            if (value == null) {
                return false;
            }
            for (RangeAdaptor adaptor : this.rangeAdaptors) {
                if (String.valueOf(value).equals(String.valueOf(adaptor.getCode()))) {
                    return true;
                }
            }
            return false;
        }
    }
}
