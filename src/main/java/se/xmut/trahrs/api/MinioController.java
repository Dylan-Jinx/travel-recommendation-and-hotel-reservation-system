package se.xmut.trahrs.api;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import se.xmut.trahrs.common.ApiResponse;
import se.xmut.trahrs.log.annotation.WebLog;
import se.xmut.trahrs.util.MinioUtils;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/minio")
public class MinioController {

    Logger logger = LoggerFactory.getLogger(MinioController.class);

    /**
     *
     * @param bucketName 桶的名称 例如：pythonfinalproject
     * @param objectName 对象的名称 例如：user_icon/admin_login.png  这里user_icon为上一级的文件夹
     * @return ApiResponse
     * @throws Exception
     */
    @WebLog(description = "Minio对象存储获取资源文件url")
    @GetMapping
    public ApiResponse getFileUrlByName(@RequestParam("bukectName")String bucketName,
                                        @RequestParam("objectName")String objectName) throws Exception {
        String resUrl = MinioUtils.getResUrl(bucketName, objectName);
        Map<String,String> minioReturnParamMap = new HashMap<>();
        minioReturnParamMap.put("resUrl",resUrl);
        return ApiResponse.ok(minioReturnParamMap);
    }

    @WebLog(description = "Minio对象存储上传资源文件")
    @PostMapping
    public ApiResponse uploadFile(@RequestParam("file") MultipartFile file,
                                  @RequestParam("bucketName") String bucketName) throws Exception {
        String fileName = file.getOriginalFilename();
        String objectname = generatorObjectName(fileName);
        boolean result = MinioUtils.uploadFile(file,bucketName,objectname,"multipart/form-data");
        if (result) {
            Map<String,String> minioReturnParamMap = new HashMap<>();
            minioReturnParamMap.put("resUrl",objectname);
            return ApiResponse.ok("文件上传成功",minioReturnParamMap);
        }else{
            return ApiResponse.error("文件上传失败，请稍后进行上传操作");
        }
    }

    private String generatorObjectName(String fileName) {
        String objectName = IdUtil.objectId();
        String extName = FileUtil.extName(fileName);
        return objectName+"."+extName;
    }

}
