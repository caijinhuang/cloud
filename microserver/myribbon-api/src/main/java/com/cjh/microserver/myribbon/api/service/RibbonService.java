package com.cjh.microserver.myribbon.api.service;

import com.cjh.microserver.myribbon.api.dto.House;

/**
 * @author cjh
 * @date 2020/1/6 16:23
 **/
public interface RibbonService {

    public String getData();

    public House getDataByName();

    public House getDataByName(House house);
}
