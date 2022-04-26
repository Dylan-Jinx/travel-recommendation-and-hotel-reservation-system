package se.xmut.trahrs.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import se.xmut.trahrs.common.ApiResponse;

@Controller
@RequestMapping("api/v1/test")
public class TestApi {

    @GetMapping
    public ApiResponse apiTest(){
        return ApiResponse.ok("success");
    }

    @GetMapping("/{indic}")
    public ApiResponse apiPathIndic(@PathVariable("indic")String inDic){
        return ApiResponse.ok(inDic);
    }
}
