package se.xmut.trahrs.common;

import lombok.Data;

import java.util.HashMap;

@Data
public class ApiResponse {
    private Integer code;
    private String errorMsg;
    private Object data;

    private ApiResponse(int code, String errorMsg, Object data) {
        this.code = code;
        this.errorMsg = errorMsg;
        this.data = data;
    }

    public static ApiResponse ok() {
        return new ApiResponse(0, "", new HashMap<>());
    }

    public static ApiResponse ok(Object data) {
        return new ApiResponse(0, "", data);
    }

    public static ApiResponse error(String errorMsg) {
        return new ApiResponse(0, errorMsg, new HashMap<>());
    }

    public static ApiResponse ok(String errorMsg, Object data) {
        return new ApiResponse(0, errorMsg, data);
    }
}
