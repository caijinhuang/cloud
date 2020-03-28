package com.cjh.microserver.data.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo implements Serializable {
    private static final long serialVersionUID = 212880904489748334L;

    private Integer userId;

    private String account;

    private String nickName;

    private String openId;

    private String avatarUrl;

    private Date modifyTime;

    private Date createTime;
}