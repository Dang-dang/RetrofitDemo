package com.mytian.retrofitdemo;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wjy on 2017/5/3.
 */

public class SimpleService {

    public static final String BASE_URL="http://www.mytian.com.cn/";
    public static final String GET_BASE_URL="https://api.github.com/";

    @SerializedName(value = "list")
    public List<CourseBean> list;



    public String result;

    public List<CourseBean> getList() {
        return list;
    }

    public void setList(List<CourseBean> list) {
        this.list = list;
    }

    public static class CourseBean{
        @SerializedName(value = "list")
        public List<LessonBean> list;
        public int id;
        public String dir_name;
        public String dir_alias;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getDir_name() {
            return dir_name;
        }

        public void setDir_name(String dir_name) {
            this.dir_name = dir_name;
        }

        public String getDir_alias() {
            return dir_alias;
        }

        public void setDir_alias(String dir_alias) {
            this.dir_alias = dir_alias;
        }

        public List<LessonBean> getList() {
            return list;
        }

        public void setList(List<LessonBean> list) {
            this.list = list;
        }
    }
}
