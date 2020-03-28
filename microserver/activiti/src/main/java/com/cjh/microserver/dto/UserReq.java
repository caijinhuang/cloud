package com.cjh.microserver.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Date;

/**
 * @author cjh
 * @date 2020/3/12 10:34
 **/
@Data
@Builder
public class UserReq {
    private Integer userId;

    @NotBlank(message = "用户账号不能为空！")
    private String account;

    @NotBlank(message = "用户昵称不能为空！")
    private String nickName;

    private String openId;

    private String avatarUrl;

    private Date modifyTime;

    private Date createTime;
}
