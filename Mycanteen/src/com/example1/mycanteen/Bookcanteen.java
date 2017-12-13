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

public class Bookcanteen extends Activity {
  public Schoolcanteen.Canteen canteen;
  private Calendar cal;  
  private int year,month,day;
  private int yearbook=0,monthbook=0,daybook=0;
  TextView showtime,tip;
  ImageButton button;
  Button buttonsignup;
  RadioGroup rg;
  EditText peoplenumber,phone,peoplename;
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
    setContentView(R.layout.activity_bookcanteen);
    //TextView canteenname=(TextView) findViewById(R.id.bookcanteentextview);    
    Intent intent=getIntent();
    canteen=(Schoolcanteen.Canteen)intent.getSerializableExtra("canteen");
    //canteenname.setText("����Ԥ��");
    getDate();
    tip=(TextView) findViewById(R.id.bookcanteentextView5);
    showtime=(TextView) findViewById(R.id.bookcanteentextView1);
    button=(ImageButton) findViewById(R.id.bookcanteenbutton1);
    peoplenumber=(EditText) findViewById(R.id.bookcanteeneditText1);
    phone=(EditText) findViewById(R.id.bookcanteeneditText2);
    peoplename=(EditText) findViewById(R.id.bookcanteeneditText3);
    buttonsignup=(Button) findViewById(R.id.bookcanteenbutton2);
    rg = (RadioGroup) findViewById(R.id.bookcanteenGroup);
    button.setOnClickListener(new View.OnClickListener() {   
      @Override
      public void onClick(View v) {
        OnDateSetListener listener=new OnDateSetListener() {          
          @Override  
          public void onDateSet(DatePicker arg0, int year, int month, int day) {  
            showtime.setText(year+"-"+(++month)+"-"+day); //��ѡ���������ʾ��TextView��,��Ϊ֮ǰ��ȡmonthֱ��ʹ�ã����Բ���Ҫ+1������ط���Ҫ��ʾ������+1
            yearbook=year;
            monthbook=month;
            daybook=day;
          }  
      };  
      DatePickerDialog dialog=new DatePickerDialog(Bookcanteen.this, 0,listener,year,month,day);//�����������Ϊ��ʾdialogʱĬ�ϵ����ڣ��·ݴ�0��ʼ��0-11��Ӧ1-12����  
      dialog.show();  
      }
  });

    setLunchordinner(1);
    choice();
    buttonsignup.setOnClickListener(new View.OnClickListener() {   
      @Override
      public void onClick(View v) {
          if (validate()) {
            if (bookcanteenPro()>0) {
              final Bundle data=new Bundle();
            data.putSerializable("canteen", canteen);
            Intent intent=new Intent(Bookcanteen.this,Bookcanteen.class);
            intent.putExtras(data);
            startActivity(intent);
            finish();
            }
            else {
              tip.setText("ע��ʧ��");
            }
          }            
      }
  });
   
  }
  
  private int bookcanteenPro() {
    String pnstring=peoplenumber.getText().toString();
    String phonestring=phone.getText().toString();
    String namestring=peoplename.getText().toString();
    JSONObject jsonObj;
    try {
      //Log.i("TestLog", "bbbbbbbbbbloginpro");
      jsonObj=book(pnstring,phonestring,namestring);
      if (jsonObj.getInt("rflag")>0) {
        return 1;
      }
    }
    catch (Exception e) {
      tip.setText("�������쳣�����Ժ�����");
      e.printStackTrace();
    }
    return 0;
  }
  
  private JSONObject book(String pnstring,String phonestring,String namestring) throws Exception{
    Map<String,String> map=new HashMap<String,String>();
    map.put("canteenid", String.valueOf(canteen.id));
    map.put("peoplenumber", pnstring);
    map.put("phone", phonestring);
    map.put("name", namestring);
    map.put("yearbook", String.valueOf(yearbook));
    map.put("monthbook", String.valueOf(monthbook));
    map.put("daybook", String.valueOf(daybook));
    map.put("lunchordinner", String.valueOf(lunchordinner));
    String url=HttpUtil.BASE_URL+"CanteenbookServlet";
    JSONObject o=new JSONObject(HttpUtil.postRequest(url,map));
    return o;
  }
   
  private boolean validate() {
    if (yearbook==0) {
      tip.setText("��ѡ������");
      return false;
    }
    String pnstring=peoplenumber.getText().toString();
    String phonestring=peoplenumber.getText().toString();
    String namestring=peoplenumber.getText().toString();
    if (pnstring.trim()== null || pnstring.trim().length() == 0) {
      tip.setText("����������");
      return false;
    }
    if (phonestring.trim()== null || phonestring.trim().length() == 0) {
      tip.setText("��������ϵ�绰");
      return false;
    }
    if (namestring.trim()== null || namestring.trim().length() == 0) {
      tip.setText("����������");
      return false;
    }
    int pn=Integer.parseInt(pnstring);
    //String pwd=password.getText().toString().trim();
    if (pn>25) {
      tip.setText("��������");
      return false;
  }
    if (yearbook<year) {
      tip.setText("��ʱ��β���ԤԼ");
      return false;
    }
    else if (yearbook==year) {
      if (monthbook<month) {
        tip.setText("��ʱ��β���ԤԼ");
        return false;
      }
      else if (monthbook==month) {
        if (daybook<=day) {
          tip.setText("��ʱ��β���ԤԼ");
          return false;
        }
      }
    }
  return true;
  }
  
  private void getDate() {  
    cal=Calendar.getInstance();  
    year=cal.get(Calendar.YEAR);       //��ȡ������ʱ����    
    Log.i("TestLog","year"+year);  
    month=cal.get(Calendar.MONTH);   //��ȡ�����·��Ǵ�0��ʼ����  
    day=cal.get(Calendar.DAY_OF_MONTH);      
   }
  
  private void choice() {
    rg.setOnCheckedChangeListener(new OnCheckedChangeListener() {
      public void onCheckedChanged(RadioGroup group,int checkedId) {
        switch (checkedId) {
        case R.id.bookcanteenradioButton1:
          setLunchordinner(1);
          break;
        case R.id.bookcanteenradioButton2:
          setLunchordinner(2);
          break;
        }
      }
    });
  }
   
  /*public void onClick(View view) {  
      switch (view.getId()) {  
      //case R.id.bookcanteentextView1:  
      case R.id.bookcanteenbutton1:
          OnDateSetListener listener=new OnDateSetListener() {  
                
              @Override  
              public void onDateSet(DatePicker arg0, int year, int month, int day) {  
                showtime.setText(year+"-"+(++month)+"-"+day);      //��ѡ���������ʾ��TextView��,��Ϊ֮ǰ��ȡmonthֱ��ʹ�ã����Բ���Ҫ+1������ط���Ҫ��ʾ������+1  
              }  
          };  
          DatePickerDialog dialog=new DatePickerDialog(Bookcanteen.this, 0,listener,year,month,day);//�����������Ϊ��ʾdialogʱĬ�ϵ����ڣ��·ݴ�0��ʼ��0-11��Ӧ1-12����  
          dialog.show();  
          break;  

      default:  
          break;  
      }  
  }  */
  

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.bookcanteen, menu);
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
