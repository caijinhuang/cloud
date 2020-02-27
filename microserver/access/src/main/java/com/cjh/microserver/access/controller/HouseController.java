package com.cjh.microserver.access.controller;

import com.cjh.microserver.access.service.api.AccessService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import com.cjh.microserver.myribbon.api.dto.House;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author cjh
 * @date 2019/12/30 15:57
 **/
@Controller
@Log4j2
@RequestMapping("/v1/house")
public class HouseController {

    @Autowired
    AccessService accessService;

    @RequestMapping(value = "/data/{name}", method=RequestMethod.POST, produces= MediaType.APPLICATION_JSON_VALUE)// 这里指定RequestMethod，如果不指定Swagger会把所有RequestMethod都输出，在实际应用中，具体指定请求类型也使接口更为严谨。
    @ApiOperation(value="测试接口", notes="测试接口详细描述")
    @ResponseBody
    @GetMapping("/data/{name}")
    public String getHouseData(@PathVariable String name) {
        String result = accessService.getHouseData(name);
        return result;
    }

    @ResponseBody
    @PostMapping("/data/save")
    public House getHouseData(@RequestBody House house) {
        House h = accessService.postHouseData(house);
        return h;
    }
}
