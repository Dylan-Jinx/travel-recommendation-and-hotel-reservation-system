package se.xmut.trahrs.api;

import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se.xmut.trahrs.common.ApiResponse;

@Api
@RestController
@RequestMapping("api/v1/test")
public class TestApi {

    final Logger logger = LoggerFactory.getLogger(TestApi.class);

    @GetMapping
    public ApiResponse apiTest(){
        return ApiResponse.ok("success");
    }

    @GetMapping("/{indic}")
    public ApiResponse apiPathIndic(@PathVariable("indic")String inDic){
        return ApiResponse.ok(inDic);
    }
}
