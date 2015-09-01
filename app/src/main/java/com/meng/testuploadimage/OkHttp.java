package com.meng.testuploadimage;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.MultipartBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;

import java.io.File;
/**
 * Created by Administrator on 2015/9/1.
 */
public class OkHttp {
    public static final OkHttpClient client=new OkHttpClient();
    private static final MediaType MEDIA_TYPE_PNG = MediaType.parse("image/png");
    /**
     * 异步上传图片
     * @param url 地址
     * @param body 参数
     * @param files 文件
     * @param callback 回调函数
     */
    public static void asyncPost(String url,File file,Callback callback){
        MultipartBuilder multipartBuilder = new MultipartBuilder();
        multipartBuilder.type(MultipartBuilder.MIXED);
            multipartBuilder.addFormDataPart("user", "gengchuan");
        if (file != null && file.exists()) {
            multipartBuilder.addFormDataPart("image", "image", RequestBody.create(MEDIA_TYPE_PNG, file.getPath()));
        }
        RequestBody formBody = multipartBuilder.build();
        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .build();
        client.newCall(request).enqueue(callback);
    }
}
