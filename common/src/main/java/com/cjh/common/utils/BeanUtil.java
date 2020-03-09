package com.cjh.common.utils;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.BeanUtils;

/**
 * bean工具，继承了BeanUtils的特性。
 * @author cjh
 * @date 2019/9/20 13:51
 **/
public class BeanUtil extends BeanUtils {

    /**
     * 将对象转换成指定类型的对象
     * 注意：转换过程只有相同类型的相同字段才会被copy到新的对象中去
     * @param source 原对象
     * @param target 目标对象类型
     * @param <T> 目标对象类型实例
     * @return
     */
    public static <T> T copyProperties(Object source, Class target) {
        Object obj = null;
        try {
            obj = target.newInstance();
            copyProperties(source, obj);
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return (T) obj;
    }

    /**
     * 将对象转换成指定类型的对象
     * 注意：字段名和字段类型必须保持一致，否则报错。慎用！
     * @param source 原对象
     * @param target 目标对象类型
     * @param <T> 目标对象类型实例
     * @return
     */
    public static <T> T copyPropertiesKeepType(Object source, Class target) {
        Object obj = JSON.parseObject(JSON.toJSONString(source),target);
        return (T) obj;
    }
}
