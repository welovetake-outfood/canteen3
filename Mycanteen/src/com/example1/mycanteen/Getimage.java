package com.example1.mycanteen;

import java.io.InputStream;
import java.net.URL;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.InputStream;
import java.net.HttpURLConnection;
//import java.net.MalformedURLException;
//import java.net.URL;

//import org.apache.commons.httpclient.util.HttpURLConnection;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Getimage {
  //private String url;
  //public List<Bitmap> blist=new ArrayList<Bitmap>();
  Bitmap bmtemp;
  public Bitmap get(final String path) {
    try {
      //2:把网址封装为一个URL对象
      URL url = new URL(path);
       
      //3:获取客户端和服务器的连接对象，此时还没有建立连接
      HttpURLConnection conn = (HttpURLConnection) url.openConnection();
      //4:初始化连接对象
      conn.setRequestMethod("GET");
      //设置连接超时
      conn.setConnectTimeout(5000);
      //设置读取超时
      conn.setReadTimeout(5000);
      //5:发生请求，与服务器建立连接
      conn.connect();
      //如果响应码为200，说明请求成功
      if(conn.getResponseCode() == 200)
      {
          //获取服务器响应头中的流
          InputStream is = conn.getInputStream();
           
          //读取流里的数据，构建成bitmap位图
          Bitmap bm = BitmapFactory.decodeStream(is);
          bmtemp=bm;
          //显示在界面上
          //ImageView imageView = (ImageView) findViewById(R.id.lv);
          //imageView.setImageBitmap(bm);
      }
  } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
  }
    return bmtemp;
  }
}
