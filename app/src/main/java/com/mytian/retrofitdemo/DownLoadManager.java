package com.mytian.retrofitdemo;

import android.content.Context;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import okhttp3.ResponseBody;

/**
 * Created by wjy on 2017/5/4.
 */

public class DownLoadManager {

    public static final String TAG = "DownLoadManager";
    public static final String APK_CONTENT_TYPE = "application/octet-stream";
    public static final String PNG_CONTENT_TYPE = "image/png";
    public static final String JPG_CONTENT_TYPE = "image/jpg";
    public static final String MP4_CONTENT_TYPE = "video/mp4";

    public static String fileSuffix = "";

    public static boolean writeResponseBodyToDisk(Context context, ResponseBody body) throws IOException {
        Log.d(TAG, "-->>>" + body.contentType().toString() + "~~~" + body.contentLength());
        String type = body.contentType().toString();
        if (type.contains(APK_CONTENT_TYPE)) {
            fileSuffix = ".apk";
        } else if (type.contains(MP4_CONTENT_TYPE)) {
            fileSuffix = ".mp4";
        } else if (type.contains(PNG_CONTENT_TYPE)) {
            fileSuffix = ".png";
        }
        String path = null;
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            path = Environment.getExternalStorageDirectory() + File.separator + "DownLoad" + File.separator + System.currentTimeMillis() + fileSuffix;
        } else {
            Toast.makeText(context, "SD卡异常", Toast.LENGTH_SHORT);
            return false;
        }
        File downloadFile = new File(path);
        InputStream is = body.byteStream();
        OutputStream os = null;
        byte[] buffer = new byte[1024];
        long fileSize = body.contentLength();
        long haveDownloadSize = 0;
        try {
            os = new FileOutputStream(downloadFile);
            int length = 0;
            while ((length = is.read(buffer)) != -1) {
                os.write(buffer, 0, length);
                haveDownloadSize += length;
            }
            os.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            if (is != null) is.close();
            if (os != null) os.close();
        }
        return true;
    }


}
