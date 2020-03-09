package com.cjh.microserver.myribbon.api.dto;

import com.cjh.common.valid.enumvalid.Range;
import com.cjh.microserver.myribbon.api.constants.SexEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author cjh
 * @date 2020/1/6 20:11
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class House {

    @NotBlank(message = "姓名不能为空！")
    @ApiModelProperty(value = "姓名")
    private String name;

    @DecimalMin(message = "年龄不符合规范!",value = "1")
    @NotNull(message = "年龄不能为空！")
    @ApiModelProperty(value = "年龄")
    private Integer age;

    @NotNull(message = "性别不能为空！")
    @Range(message = "性别编码不正确！",adaptor = SexEnum.class)
    @ApiModelProperty(value = "性别")
    private Integer sex;

    @ApiModelProperty(value = "居住地址")
    private String address;
}
