package com.example1.mycanteen;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
//import net.sf.json.JSONObject;
//import com.google.gson.*;
//import net.sf.json.*;
//import net.sf.json.JSONArray;
//import net.sf.json.JSONObject;
//import net.sf.json.JsonConfig;
//import net.sf.json.util.JSONUtils;

public class Workerlogin extends Activity {
  Button buttonup;
  Button buttonup1;
  EditText id;
  EditText password;
  TextView text;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_workerlogin);
    buttonup = (Button) findViewById(R.id.toRegister);
    buttonup1 = (Button) findViewById(R.id.Login1);
    id = (EditText) findViewById(R.id.EnterName);
    password = (EditText) findViewById(R.id.EnterPW);
    text = (TextView) findViewById(R.id.info1);
    buttonup1.setOnClickListener(new View.OnClickListener() {   
        @Override
        public void onClick(View v) {
            if (validate()) {
              //Log.i("TestLog", "aaaaaaaaaaaaaa");
                if (loginPro()>0) {
                  Intent intent=new Intent(Workerlogin.this,LoginSuccess.class);
                  startActivity(intent);
                  finish();
                }
                else {
                  text.setText("提示：错误，重新输入");
                }
            }
        }
    });
    buttonup.setOnClickListener(new View.OnClickListener() {   
        @Override
        public void onClick(View v) {

            //跳转到注册页面
            Intent intent=new Intent(Workerlogin.this,WorkerRegister.class);
            startActivity(intent);
        }
    });
  }
  
  private boolean validate() {
    String userid=id.getText().toString().trim();
    if (userid.equals("")) {
      text.setText("提示：用户名是必填项");
      //Log.i("TestLog", "aaaaaaaaaaaaaa");
      return false;
    }
    String pwd=password.getText().toString().trim();
    if (pwd.equals("")) {
      text.setText("提示：用户密码是必填项");
      //Log.i("TestLog", "aaaaaaaaaaaaaa");
      return false;
    }
    return true;
  }
  
  private int loginPro() {
    String userid=id.getText().toString();
    String pwd=password.getText().toString();
    JSONObject jsonObj;
    try {
      //Log.i("TestLog", "bbbbbbbbbbloginpro");
      jsonObj=query(userid,pwd);
      if (jsonObj.getInt("canteenid")>0) {
        return jsonObj.getInt("canteenid");
      }
    }
    catch (Exception e) {
      text.setText("提示：服务器异常，请稍后再使");
      e.printStackTrace();
    }
    return 0;
  }
  private JSONObject query(String userid,String pwd) throws Exception{
    Map<String,String> map=new HashMap<String,String>();
    map.put("userid", userid);
    map.put("password", pwd);
    //Log.i("TestLog", "cccccccccccc"+pwd);
    String url=HttpUtil.BASE_URL+"LoginServlet";
    //String url="http://10.0.2.2:8080/Mycanteenserver/LoginServlet?userid=002&password=123";
    JSONObject o=new JSONObject(HttpUtil.postRequest(url,map));
    //Log.i("TestLog", url);
    //o=JSONObject.fromObject(HttpUtil.getRequest(url));
    return o;
  }


  
  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    //getMenuInflater().inflate(R.menu.workerlogin, menu);
    getMenuInflater().inflate(R.menu.workerlogin, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();
    if (id == R.id.action_settings) {
      return true;
    }
    return super.onOptionsItemSelected(item);
  }
}
