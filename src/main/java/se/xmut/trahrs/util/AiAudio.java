package se.xmut.trahrs.util;

import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONUtil;
import cn.hutool.json.JSONObject;
import org.springframework.stereotype.Component;


@Component
public class AiAudio {
    private final String ROOT_URL = "http://" +
            YamlUtil.getStringByYaml("Flask.host") + ":" +
            YamlUtil.getStringByYaml("Flask.port");
    public String getAudioText(){
        String url="/audio";
        String text= HttpRequest.get(ROOT_URL+url)
                .header("Content-Type","application/json")
                .execute().body();
        JSONObject json = JSONUtil.parseObj(text);
        return String.valueOf(json.getStr("text"));

    }
}
