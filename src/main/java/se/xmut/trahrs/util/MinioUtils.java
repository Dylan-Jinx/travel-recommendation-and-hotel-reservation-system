package se.xmut.trahrs.util;

import cn.hutool.core.util.IdUtil;
import io.minio.*;
import io.minio.http.Method;
import okhttp3.*;
import org.springframework.web.multipart.MultipartFile;
import se.xmut.trahrs.exception.BucketNotFoundException;
import se.xmut.trahrs.exception.CreateTempFileFailedException;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.time.ZonedDateTime;
import java.util.Base64;
import java.util.Map;

public class MinioUtils {
    private static final String HOST = "42.192.5.34";
    private static final String PORT = "9000";
    private static final String ACCESSKEY = "minioadmin";
    private static final String SECRETKEY = "cSLxZqgpn2$DcHxb4Kx&SD@msZZOPG6s7UpF@hPYcMywsyLW5nhw9QZabsSlz0gb";
    private static final Long FILESIZELOWLIMIT = 10 * 1024L;
    private static final Long FILESIZEHIGHLIMIT = 1024 * 1024 * 1024L;

    private static MinioClient getMinioClient() {
        MinioClient minioClient = MinioClient.builder()
                .endpoint(HOST, 9000, false)
                .credentials(ACCESSKEY,SECRETKEY)
                .build();
        return minioClient;
    }

    @Deprecated
    public static String getBase64CodeOfImage(String bucketName, String objectName) throws Exception {
        GetObjectResponse resp = MinioUtils.getObjectRes(bucketName, objectName);
        byte datas[] = resp.readAllBytes();
        Base64.Encoder encoder = Base64.getEncoder();
        String base64Head = "data:image/jpg;base64,";
        String result = base64Head+encoder.encodeToString(datas);
        return result;
    }

    public static String getResUrl(String bucketName, String objectName) throws Exception {
        MinioClient minioClient = getMinioClient();
        if(isExistBucket(minioClient, bucketName)){
            String resUrl = minioClient.getPresignedObjectUrl(GetPresignedObjectUrlArgs.builder()
                    .bucket(bucketName)
                    .object(objectName)
                    .method(Method.GET)
                    .build());
            return resUrl;
        }else{
            throw new BucketNotFoundException("The bucket information could not be found");
        }
    }

    public static boolean uploadFile(MultipartFile file, String bucketName, String objectName, String contentType) throws Exception {
        MinioClient minioClient = getMinioClient();
        if(!isExistBucket(minioClient, bucketName)){
            minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
        }
        PostPolicy postPolicy = createPostPolicy(bucketName, objectName, contentType);
        Map<String, String> formData = minioClient.getPresignedPostFormData(postPolicy);
        boolean result = uploadFileByOkHttp(file, formData, contentType, bucketName, objectName);
        return result;
    }

    private static PostPolicy createPostPolicy(String bucketName, String objectName, String contentType) {
        PostPolicy postPolicy = new PostPolicy(bucketName, ZonedDateTime.now().plusDays(7));
        postPolicy.addEqualsCondition("key", objectName);
        postPolicy.addEqualsCondition("Content-Type", contentType);
        postPolicy.addContentLengthRangeCondition(FILESIZELOWLIMIT, FILESIZEHIGHLIMIT);
        return postPolicy;
    }

    private static boolean uploadFileByOkHttp(MultipartFile file, Map<String, String> formData, String contentType, String bucketName, String objectName) throws Exception {
        MultipartBody.Builder multipartBuilder = createMultipartBody(file, formData, contentType, objectName);
        Response response = createRequestByOkhttp(bucketName, multipartBuilder);
        return response.isSuccessful();
    }

    private static Response createRequestByOkhttp(String bucketName, MultipartBody.Builder multipartBuilder) throws IOException {
        String prefix = "http://";
        Request request = new Request.Builder()
                .url(prefix +HOST+":"+PORT+"/"+bucketName)
                .post(multipartBuilder.build())
                .build();
        OkHttpClient client = new OkHttpClient()
                .newBuilder()
                .build();
        Response response = client.newCall(request).execute();
        return response;
    }

    private static MultipartBody.Builder createMultipartBody(MultipartFile file, Map<String, String> formData, String contentType, String objectName) throws Exception {
        MultipartBody.Builder multipartBuilder = new MultipartBody.Builder();
        multipartBuilder.setType(MultipartBody.FORM);
        for(Map.Entry<String, String> entry : formData.entrySet()){
            multipartBuilder.addFormDataPart(entry.getKey(), entry.getValue());
        }
        multipartBuilder.addFormDataPart("key", objectName);
        multipartBuilder.addFormDataPart("Content-Type", contentType);
        File tempFile = getFileObjAndRandomName(file);
        multipartBuilder.addFormDataPart("file", objectName, RequestBody.create(tempFile, null));
        return multipartBuilder;
    }

    private static File getFileObjAndRandomName(MultipartFile file) throws Exception{
        String fileName = file.getOriginalFilename();
        String prefix = fileName.substring(fileName.lastIndexOf("."));
        try {
            File tempFile = File.createTempFile(prefix, IdUtil.objectId());
            file.transferTo(tempFile);
            return tempFile;
        }catch (Exception e){
            e.printStackTrace();
            throw new CreateTempFileFailedException(e.getMessage());
        }
    }

    private static boolean isExistBucket(MinioClient minioClient, String bucketName) throws Exception {
        return minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
    }

    private static GetObjectResponse getObjectRes(String bucketName, String objectName) throws Exception {
        MinioClient minioClient = getMinioClient();
        if(isExistBucket(minioClient,bucketName)){
            GetObjectResponse objRes = minioClient.getObject(GetObjectArgs.builder()
                    .bucket(bucketName)
                    .object(objectName)
                    .build()
            );
            return objRes;
        }else{
            throw new BucketNotFoundException("The bucket information could not be found");
        }
    }

//    public static void main(String[] args) throws Exception {
//
//        System.out.println(MinioUtils.getResUrl("pythonfinalproject", "user_icon/admin_login.png"));
//    }
}
