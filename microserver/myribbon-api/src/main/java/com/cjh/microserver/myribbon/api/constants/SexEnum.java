package com.cjh.microserver.myribbon.api.constants;

import com.cjh.common.valid.enumvalid.RangeAdaptor;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 性别
 *
 * @author cjh
 * @date 2020/3/9 14:53
 **/
@Getter
@AllArgsConstructor
public enum SexEnum implements RangeAdaptor {

    /**
     * 性别枚举
     */
    MAN("1", "男"),
    WOMAN("0", "女");

    private String code;
    private String msg;
}
