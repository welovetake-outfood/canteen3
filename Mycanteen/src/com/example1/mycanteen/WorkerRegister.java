package com.example1.mycanteen;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

public class WorkerRegister extends Activity {
  Button buttonup;
  EditText workerid;
  EditText password;
  TextView text;
  RadioGroup rg;
  public int canteenid;
  public int getCanteenid() {
    return canteenid;
  }

  public void setCanteenid(int canteenid) {
    this.canteenid = canteenid;
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_workerregister);
    buttonup = (Button) findViewById(R.id.Register);
    workerid = (EditText) findViewById(R.id.registereditName);
    password = (EditText) findViewById(R.id.registereditPW);
    text = (TextView) findViewById(R.id.info);
    rg = (RadioGroup) findViewById(R.id.canteenGroup);
    text.setText("提示:");
    setCanteenid(1);
    buttonup.setOnClickListener(new View.OnClickListener() {   
        @Override
        public void onClick(View v) {
            if (validate()) {
              if (registerPro()>0) {
                Intent intent=new Intent(WorkerRegister.this,MainActivity.class);
                startActivity(intent);
                finish();
              }
              else {
                text.setText("提示：注册失败");
              }
            }            
        }
    });
        
    }
  
  private boolean validate() {
    String id=workerid.getText().toString().trim();
    String pwd=password.getText().toString().trim();
    if (id == null || id.length() == 0) {
      text.setText("提示:用户名为空！");
      return false;
  }
    if (id.length() > 12) {
      text.setText("提示:用户名过长！");
      return false;
  }
  if (id.matches(".*[/\\\\:*?\"<>|\t].*")  || id.matches(".*\\p{So}.*")) {
      text.setText("提示:用户名包含非法字符！");
      return false;
  } 
  if (pwd==null || pwd.length()==0) {
    text.setText("提示:密码为空！");
    return false;
  }
  return true;
  }
  
  private int registerPro() {
    String id=workerid.getText().toString();
    String pwd=password.getText().toString();
    canteenchoice();
    JSONObject jsonObj;
    try {
      //Log.i("TestLog", "bbbbbbbbbbloginpro");
      jsonObj=register(id,pwd,canteenid);
      if (jsonObj.getInt("rflag")>0) {
        return 1;
      }
    }
    catch (Exception e) {
      text.setText("提示：服务器异常，请稍后再使");
      e.printStackTrace();
    }
    return 0;
  }
  
  private JSONObject register(String userid,String pwd,int canteenid) throws Exception{
    Map<String,String> map=new HashMap<String,String>();
    map.put("userid", userid);
    map.put("password", pwd);
    map.put("canteenid", String.valueOf(canteenid));
    String url=HttpUtil.BASE_URL+"RegisterServer";
    JSONObject o=new JSONObject(HttpUtil.postRequest(url,map));
    //Log.i("TestLog", url);
    //o=JSONObject.fromObject(HttpUtil.getRequest(url));
    return o;
  }
  
  private void canteenchoice() {
    rg.setOnCheckedChangeListener(new OnCheckedChangeListener() {
      public void onCheckedChanged(RadioGroup group,int checkedId) {
        switch (checkedId) {
        case R.id.RadioXS:
          setCanteenid(1);
          break;
        case R.id.RadioXY:
          setCanteenid(2);
          break;
        case R.id.radioHD:
          setCanteenid(3);
          break;
        case R.id.radioQZ:
          setCanteenid(4);
          break;
        case R.id.radioJZ:
          setCanteenid(5);
          break;
        }
      }
    });
  }
        
  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.lookthroughdetail, menu);
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
