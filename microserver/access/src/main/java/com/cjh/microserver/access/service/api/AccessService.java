package com.cjh.microserver.access.service.api;

import com.cjh.microserver.myribbon.api.dto.House;

/**
 * @author cjh
 * @date 2019/12/30 16:02
 **/
public interface AccessService {

    String handle();

    String handleByTemp();

    String getHouseData(String name);

    House postHouseData(House house);
}
