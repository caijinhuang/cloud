package com.cjh.common.response.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 响应状态
 *
 * @author cjh
 * @date 2020/3/9 10:28
 **/
@AllArgsConstructor
@Getter
public enum ResponseStatusEnum {
    /**
     * 响应状态提示码
     */
    FAILURE(0, "失败"),
    SUCCESS(1, "成功");
    private int code;
    private String msg;
}
