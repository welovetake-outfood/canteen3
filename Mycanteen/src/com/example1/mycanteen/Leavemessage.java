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

public class Leavemessage extends Activity {
  public Schoolcanteen.Canteen canteen;
  private Button button;
  private EditText message;
  private TextView tip;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_leavemessage);
    TextView text=(TextView) findViewById(R.id.leavemessagetextview);
    button = (Button) findViewById(R.id.leavemessagebutton1);
    message = (EditText) findViewById(R.id.leavemessageeditText1);
    tip=(TextView) findViewById(R.id.leavemessagetextView1);
    tip.setText("提示：");
    Intent intent=getIntent();
    canteen=(Schoolcanteen.Canteen)intent.getSerializableExtra("canteen");
    text.setText(canteen.name+" leavemessage");
    button.setOnClickListener(new View.OnClickListener() {   
      @Override
      public void onClick(View v) {
          if (validate()) {
            //Log.i("TestLog", "aaaaaaaaaaaaaa");
            //int canteenid=loginPro();
            if (givemessage()) {
              final Bundle data=new Bundle();
              data.putSerializable("canteen", canteen);
              Intent intent=new Intent(Leavemessage.this,Leavemessage.class);
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
  
  private boolean givemessage() {
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
      tip.setText("提示：服务器异常，请稍后再使");
      e.printStackTrace();
    }
    return false;
  }
  
  private JSONObject lm(String msg,int canteenid) throws Exception{
    Map<String,String> map=new HashMap<String,String>();
    map.put("msg", msg);
    map.put("canteenid", String.valueOf(canteenid));
    String url=HttpUtil.BASE_URL+"LeavemessageServlet";//!!!!!!!!!
    JSONObject o=new JSONObject(HttpUtil.postRequest(url,map));
    return o;
  }
  
  private boolean validate() {
    String userid=message.getText().toString().trim();
    if (userid.equals("")) {
      tip.setText("提示：请填写留言");
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
