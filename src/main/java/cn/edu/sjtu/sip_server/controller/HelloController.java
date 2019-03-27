package cn.edu.sjtu.sip_server.controller;

import cn.edu.sjtu.sip_server.util.TResult;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
@Slf4j
@ApiModel
public class HelloController {
    
    @ApiOperation(value = "hello")
    @GetMapping("index")
    public TResult<String> getHello() {
        TResult<String> tResult = new TResult<>();
        tResult.setSuccess("Hello World!");
        return tResult;
    }
}