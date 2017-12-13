package com.example1.mycanteen;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class Birthdaybook extends Activity {
  public Schoolcanteen.Canteen canteen;
  private Calendar cal;  
  private int year,month,day;
  private int yearbook=0,monthbook=0,daybook=0;
  TextView showtime,tip;
  ImageButton button;
  Button buttonsignup;
  RadioGroup rg;
  EditText phone,peoplename;
  private int lunchordinner;
  public int getLunchordinner() {
    return lunchordinner;
  }

  public void setLunchordinner(int lunchordinner) {
    this.lunchordinner = lunchordinner;
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_birthdaybook);
    //TextView canteenname=(TextView) findViewById(R.id.birthdaybooktextview);    
    Intent intent=getIntent();
    canteen=(Schoolcanteen.Canteen)intent.getSerializableExtra("canteen");
    //canteenname.setText("生日预定");
    getDate();
    tip=(TextView) findViewById(R.id.birthdaybooktextView5);
    showtime=(TextView) findViewById(R.id.birthdaybooktextView1);
    button=(ImageButton) findViewById(R.id.birthdaybookbutton1);
    //peoplenumber=(EditText) findViewById(R.id.birthdaybookeditText1);
    phone=(EditText) findViewById(R.id.birthdaybookeditText2);
    peoplename=(EditText) findViewById(R.id.birthdaybookeditText3);
    buttonsignup=(Button) findViewById(R.id.birthdaybookbutton2);
    rg = (RadioGroup) findViewById(R.id.birthdaybookGroup);
    button.setOnClickListener(new View.OnClickListener() {   
      @Override
      public void onClick(View v) {
        OnDateSetListener listener=new OnDateSetListener() {          
          @Override  
          public void onDateSet(DatePicker arg0, int year, int month, int day) {  
            showtime.setText(year+"-"+(++month)+"-"+day); //将选择的日期显示到TextView中,因为之前获取month直接使用，所以不需要+1，这个地方需要显示，所以+1
            yearbook=year;
            monthbook=month;
            daybook=day;
          }  
      };  
      DatePickerDialog dialog=new DatePickerDialog(Birthdaybook.this, 0,listener,year,month,day);//后边三个参数为显示dialog时默认的日期，月份从0开始，0-11对应1-12个月  
      dialog.show();  
      }
  });
    
    setLunchordinner(1);
    choice();
    
    buttonsignup.setOnClickListener(new View.OnClickListener() {   
      @Override
      public void onClick(View v) {
          if (validate()) {
            if (birthdaybookPro()>0) {
              final Bundle data=new Bundle();
            data.putSerializable("canteen", canteen);
            Intent intent=new Intent(Birthdaybook.this,Birthdaybook.class);
            intent.putExtras(data);
            startActivity(intent);
            finish();
            }
            else {
              tip.setText("提交失败");
            }
          }            
      }
  });
  }
  
  private int birthdaybookPro() {
    String phonestring=phone.getText().toString();
    String namestring=peoplename.getText().toString();
    JSONObject jsonObj;
    try {
      //Log.i("TestLog", "bbbbbbbbbbloginpro");
      jsonObj=book(phonestring,namestring);
      if (jsonObj.getInt("rflag")>0) {
        return 1;
      }
    }
    catch (Exception e) {
      tip.setText("服务器异常，请稍后再试");
      e.printStackTrace();
    }
    return 0;
  }
  
  private JSONObject book(String phonestring,String namestring) throws Exception{
    Map<String,String> map=new HashMap<String,String>();
    map.put("canteenid", String.valueOf(canteen.id));
    map.put("phone", phonestring);
    map.put("name", namestring);
    map.put("yearbook", String.valueOf(yearbook));
    map.put("monthbook", String.valueOf(monthbook));
    map.put("daybook", String.valueOf(daybook));
    map.put("lunchordinner", String.valueOf(lunchordinner));
    String url=HttpUtil.BASE_URL+"BirthdaybookServlet";//???????
    JSONObject o=new JSONObject(HttpUtil.postRequest(url,map));
    //Log.i("TestLog", url);
    //o=JSONObject.fromObject(HttpUtil.getRequest(url));
    return o;
  }
  
  private boolean validate() {
    if (yearbook==0) {
      tip.setText("请选择日期");
      return false;
    }
    //String pnstring=peoplenumber.getText().toString();
    String phonestring=phone.getText().toString();
    String namestring=peoplename.getText().toString();
    if (phonestring.trim()== null || phonestring.trim().length() == 0) {
      tip.setText("请输入联系电话");
      return false;
    }
    if (namestring.trim()== null || namestring.trim().length() == 0) {
      tip.setText("请输入姓名");
      return false;
    }
    if (yearbook<year) {
      tip.setText("该时间段不可预约");
      return false;
    }
    else if (yearbook==year) {
      if (monthbook<month) {
        tip.setText("该时间段不可预约");
        return false;
      }
      else if (monthbook==month) {
        if (daybook<=day) {
          tip.setText("该时间段不可预约");
          return false;
        }
      }
    }
  return true;
  }
  
  private void getDate() {  
    cal=Calendar.getInstance();  
    year=cal.get(Calendar.YEAR);       //获取年月日时分秒    
    Log.i("TestLog","year"+year);  
    month=cal.get(Calendar.MONTH);   //获取到的月份是从0开始计数  
    day=cal.get(Calendar.DAY_OF_MONTH);      
   }
  
  private void choice() {
    rg.setOnCheckedChangeListener(new OnCheckedChangeListener() {
      public void onCheckedChanged(RadioGroup group,int checkedId) {
        switch (checkedId) {
        case R.id.birthdaybookradioButton1:
          setLunchordinner(1);
          break;
        case R.id.birthdaybookradioButton2:
          setLunchordinner(2);
          break;
        }
      }
    });
  }
  
  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.birthdaybook, menu);
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
