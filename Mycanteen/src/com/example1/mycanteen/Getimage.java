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
      //2:����ַ��װΪһ��URL����
      URL url = new URL(path);
       
      //3:��ȡ�ͻ��˺ͷ����������Ӷ��󣬴�ʱ��û�н�������
      HttpURLConnection conn = (HttpURLConnection) url.openConnection();
      //4:��ʼ�����Ӷ���
      conn.setRequestMethod("GET");
      //�������ӳ�ʱ
      conn.setConnectTimeout(5000);
      //���ö�ȡ��ʱ
      conn.setReadTimeout(5000);
      //5:�����������������������
      conn.connect();
      //�����Ӧ��Ϊ200��˵������ɹ�
      if(conn.getResponseCode() == 200)
      {
          //��ȡ��������Ӧͷ�е���
          InputStream is = conn.getInputStream();
           
          //��ȡ��������ݣ�������bitmapλͼ
          Bitmap bm = BitmapFactory.decodeStream(is);
          bmtemp=bm;
          //��ʾ�ڽ�����
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
