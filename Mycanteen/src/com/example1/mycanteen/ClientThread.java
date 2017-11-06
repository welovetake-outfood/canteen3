package com.example1.mycanteen;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketTimeoutException;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
//error
public class ClientThread implements Runnable{
  private Socket s;
  private Handler handler;
  public Handler revHandler;
  BufferedReader br=null;
  OutputStream os=null;
  public ClientThread(Handler handler)
  {
    this.handler=handler;
  }
  public void run(){
    try {
      s=new Socket("219.217.246.15",30001);
      br=new BufferedReader(new InputStreamReader(s.getInputStream()));
      os=s.getOutputStream();
      new Thread()
      {
        public void run() {
          String content=null;
          try {
            while ((content=br.readLine())!=null) {
              Message msg=new Message();
              msg.what=0x123;
              msg.obj=content;
              handler.sendMessage(msg);
            }
          }
          catch (IOException e)
          {
            e.printStackTrace();
          }
        }
      }.start();
      Looper.prepare();
      revHandler=new Handler() {
        public void handleMessage(Message msg) {
          if (msg.what==0x345) {
            try {
              os.write((msg.obj.toString()+"\r\n").getBytes("utf-8"));
            }
            catch (Exception e) {
              e.printStackTrace();
            }
          }
        }
      };
      Looper.loop();
    }
    catch (SocketTimeoutException el) {
      System.out.print("out of time");
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }
  }
}
