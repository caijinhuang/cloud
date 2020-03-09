package com.cjh.microserver.myribbon.controller;

import com.cjh.common.response.dto.ResponseResult;
import com.cjh.microserver.myribbon.api.dto.House;
import com.cjh.microserver.myribbon.api.service.RibbonService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author cjh
 * @date 2020/1/6 16:21
 **/
@Log4j2
@Controller
@RequestMapping("/v1/house")
public class RibbonController implements RibbonService {

    @Override
    @ResponseBody
    @GetMapping("/data")
    public String getData() {
        return "success";
    }

    @Override
    @ResponseBody
    @GetMapping("/data/{name}")
    public House getDataByName() {
        return House.builder().name("Miss cai").age(25).address("fu zhou").build();
    }

    @Override
    @ResponseBody
    @PostMapping("/data/save")
    public House getDataByName(@RequestBody @Valid House house) {
        log.info("接收的报文信息：{}", house);
        return House.builder().name("Miss cai").age(25).address("fu zhou").build();
    }


    /**
     * 入参校验示例报文
     *
     * @param house
     * @return
     */
    @Override
    @ResponseBody
    @PostMapping("/data/post")
    @ApiOperation(value = "API入参校验报文", notes = "测试接口", tags = {"valid"})
    public ResponseResult getData(@RequestBody @Valid House house) {
        log.info("接收的报文信息：{}", house);
        House h = House.builder().name("Miss cai").age(25).address("fu zhou").build();
        return ResponseResult.builder().data(h);
    }
}
