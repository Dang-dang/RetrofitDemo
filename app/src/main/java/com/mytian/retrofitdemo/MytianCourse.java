package com.mytian.retrofitdemo;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by wjy on 2017/5/3.
 */

public interface MytianCourse {

    @FormUrlEncoded
    @POST("/myt_class/classAction_getDirClazList.do")
    Call<SimpleService> getCourseList(@Field("uid") String uid,
                                      @Field("deviceVersion") String deviceversion,
                                      @Field("token") String token,
                                      @Field("level") String level,
                                      @Field("client_version") String clientersion,
                                      @Field("client_type") String clienttype);

}
