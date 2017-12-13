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
import android.widget.TextView;

public class ReleaseAnnouncement extends Activity {
  public Schoolcanteen.Canteen canteen;
  private Button button;
  private EditText message;
  private TextView tip;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_releaseannouncement);
    button = (Button) findViewById(R.id.ReleaseAnnouncementbutton1);
    message = (EditText) findViewById(R.id.ReleaseAnnouncementeditText1);
    tip=(TextView) findViewById(R.id.ReleaseAnnouncementtextView1);
    tip.setText("提示：");
    Intent intent=getIntent();
    canteen=(Schoolcanteen.Canteen)intent.getSerializableExtra("canteen");
    button.setOnClickListener(new View.OnClickListener() {   
      @Override
      public void onClick(View v) {
          if (validate()) {
            //Log.i("TestLog", "aaaaaaaaaaaaaa");
            //int canteenid=loginPro();
            if (givedescPro()) {
              final Bundle data=new Bundle();
              data.putSerializable("canteen", canteen);
              Intent intent=new Intent(ReleaseAnnouncement.this,ReleaseAnnouncement.class);
              intent.putExtras(data);
              startActivity(intent);
              finish();
            }
            else {
              tip.setText("提示：提交失败");
            }
          }
      }
  });
  }
  
  private boolean givedescPro() {
    String msg=message.getText().toString();
    JSONObject jsonObj;
    try {
      //Log.i("TestLog", "bbbbbbbbbbloginpro");
      jsonObj=lm(msg,canteen.getId());
      if (jsonObj.getInt("rflag")>0) {
        return true;
      }
    }
    catch (Exception e) {
      tip.setText("提示：服务器异常，请稍后再试");
      e.printStackTrace();
    }
    return false;
  }
  
  private JSONObject lm(String desc,int canteenid) throws Exception{
    Map<String,String> map=new HashMap<String,String>();
    map.put("msg",desc);
    map.put("canteenid", String.valueOf(canteenid));
    String url=HttpUtil.BASE_URL+"GivedescServlet";//!!!!!!!!!
    JSONObject o=new JSONObject(HttpUtil.postRequest(url,map));
    return o;
  }
  
  private boolean validate() {
    String desc=message.getText().toString().trim();
    if (desc.equals("")) {
      tip.setText("提示：请填写新公告");
      //Log.i("TestLog", "aaaaaaaaaaaaaa");
      return false;
    }
    return true;
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.leavemessage, menu);
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