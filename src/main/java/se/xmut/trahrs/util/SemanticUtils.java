package se.xmut.trahrs.util;

import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import org.springframework.stereotype.Component;


/**
 * @author breeze
 * @date 2022/5/23 21:05
 */
@Component
public class SemanticUtils {

    private final String ROOT_URL = "http://127.0.0.1:5000";

    /**
     * 对评论进行情感分析
     * @param text 评论
     * @return 情感分析后的rating 趋近0为消极， 趋近1为积极
     */
    public double getSemanticAnalysisResult(String text){
        String url = "/semantic/analysis";
        JSONObject jsonObject = JSONUtil.createObj();
        jsonObject.putOnce("semantic", text);
        String res = HttpRequest.post(ROOT_URL+url)
                .header("Content-Type","application/json")
                .body(jsonObject.toString())
                .execute().body();
        JSONObject json = JSONUtil.parseObj(res);

        return Double.parseDouble(json.getStr("rating"));
    }

}
