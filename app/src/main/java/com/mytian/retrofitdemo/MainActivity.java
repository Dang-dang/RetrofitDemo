package com.mytian.retrofitdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(SimpleService.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.button).setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        System.out.println("click");
        MytianCourse mytianCourse = retrofit.create(MytianCourse.class);
        Call<SimpleService> call = mytianCourse.getCourseList("200000004", "M1_IN8300_5.1_R_V1.13_20160830", "3e99e80b-e563-4052-82a8-89888e284d6d",
                "L00", "58", "0");
        call.enqueue(new Callback<SimpleService>() {
            @Override
            public void onResponse(Call<SimpleService> call, Response<SimpleService> response) {
                List<SimpleService.CourseBean> list = response.body().getList();
                for (SimpleService.CourseBean bean : list) {
                    System.out.println(bean.id + "~~~" + bean.dir_alias + "~~~" + bean.dir_name + "\n");
                    for (LessonBean lbean : bean.getList()) {
                        System.out.println(lbean.clz_name + "===" + lbean.pkg_name);
                    }
                }
            }

            @Override
            public void onFailure(Call<SimpleService> call, Throwable t) {
                System.out.println("fail");
            }
        });
    }
}
