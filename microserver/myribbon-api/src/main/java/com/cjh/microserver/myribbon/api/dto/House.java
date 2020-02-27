package com.cjh.microserver.myribbon.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author cjh
 * @date 2020/1/6 20:11
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class House {
    private String name;
    private int age;
    private String address;
}
