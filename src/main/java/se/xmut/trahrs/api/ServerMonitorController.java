package se.xmut.trahrs.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se.xmut.trahrs.common.ApiResponse;
import se.xmut.trahrs.domain.vo.ServerVo;

@RestController
@RequestMapping("/monitor/server")
public class ServerMonitorController {

    @GetMapping
    public ApiResponse server() throws Exception {
        ServerVo server = new ServerVo();
        server.copyTo();
        return ApiResponse.ok("获取服务器状态成功", server);
    }
}
