package com.cjh.microserver.redis.api.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author cjh
 * @date 2020/3/9 20:35
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
