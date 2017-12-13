package com.example1.mycanteen;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class CheckBirthday extends Activity {
  public Schoolcanteen.Canteen canteen;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_checkbirthday);
    Intent intent=getIntent();
    canteen=(Schoolcanteen.Canteen)intent.getSerializableExtra("canteen");
    final ListView p = (ListView) findViewById(R.id.listView1);
    final TextView text = (TextView) findViewById(R.id.appointmenttext);
    p.setAdapter(new ArrayAdapter<String>(this, R.layout.array_adapter,getData()));
    p.setOnItemClickListener(new ListView.OnItemClickListener(){
    @Override
    public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
        // TODO Auto-generated method stub
        JSONArray o = null;
        try {
            o=getbirthdayjson(canteen.getId());
            String[] appointmenttext = new String[o.length()];
            for(int i=0;i<o.length();i++){
                JSONObject jsonobject=o.getJSONObject(i);
                if(jsonobject.getInt("lunchordinner")==1) {
                    appointmenttext[i] = "预约人姓名：" + jsonobject.getString("peoplename") + "\n" + "联系方式：" 
                        + jsonobject.getString("phonenumber")  + "\n" + "预约日期：" + jsonobject.getInt("year") 
                        + "/" + jsonobject.getInt("month") + "/" + jsonobject.getInt("day") + "\n" 
                        + "预约时间：午餐" ;
                }
                if(jsonobject.getInt("lunchordinner")==2) {
                    appointmenttext[i] = "预约人姓名：" + jsonobject.getString("peoplename") + "\n" + "联系方式：" 
                        + jsonobject.getString("phonenumber")  + "\n" + "预约日期：" + jsonobject.getInt("year") 
                        + "/" + jsonobject.getInt("month") + "/" + jsonobject.getInt("day") + "\n" 
                        + "预约时间：晚餐";
                }
            }
            text.setText("预约详情：\n" + appointmenttext[arg2]); 
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    });
    
    p.setOnItemLongClickListener(new ListView.OnItemLongClickListener(){ 
        @Override  
        public boolean onItemLongClick(AdapterView<?> arg0, View arg1,  
                int arg2, long arg3) {  
            // TODO Auto-generated method stub  
            // When clicked, show a toast with the TextView text  
            //长按删除
            JSONArray o = null;
            try {
                o=getbirthdayjson(canteen.getId());
                JSONObject jsonobject=o.getJSONObject(arg2);
                final String name = jsonobject.getString("peoplename"); //获取所需删除数据的名字
                Dialog alertDialog = new AlertDialog.Builder(CheckBirthday.this).   
                        setTitle("确认删除？").   
                        setMessage("是否完成该条预约并删除信息").   
                        setIcon(R.drawable.ic_launcher).   
                        setPositiveButton("删除", new DialogInterface.OnClickListener() {   
                               
                            @Override   
                            public void onClick(DialogInterface dialog, int which) {   
                                // TODO Auto-generated method stub    
                                try {
                                    setbirthdaybooknamejson(name);
                                    refresh();
                                } catch (Exception e) {
                                    // TODO Auto-generated catch block
                                    e.printStackTrace();
                                }
                            }   
                        }).   
                        setNegativeButton("取消", new DialogInterface.OnClickListener() {   
                               
                            @Override   
                            public void onClick(DialogInterface dialog, int which) {   
                                // TODO Auto-generated method stub   
                                refresh();
                            }   
                        }).   
                        create();   
                alertDialog.show();   

            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return true;  
        }  
    });  
  }
  
  public void refresh() {
      onCreate(null);
  } 
  
  private List<String> getData(){
      List<String> data = new ArrayList<String>();
      //return data;
      JSONArray o;
      Log.i("TestLog",canteen.id +"is id");
      try {
        o=getbirthdaynamejson(canteen.id);
        for (int i=0;i<o.length();i++)
        {
          JSONObject jsonobject=o.getJSONObject(i);
          String a=jsonobject.getString("message");
          //Log.i("TestLog", a+"is comment");
          //String a=o.getJSONObject(i).toString();
          //data.add(jsonobject.getString("comment"));
          data.add(a);
          Log.i("TestLog", a+"is name");
        }
      }    
      catch (Exception e) {
        e.printStackTrace();
      }
      Log.i("TestLog", data.toString());
      return data;
  }
  
  private JSONArray getbirthdayjson(int canteenid) throws Exception{
      Map<String,String> map=new HashMap<String,String>();
      map.put("canteenid", String.valueOf(canteenid));
      String url=HttpUtil.BASE_URL+"CheckBirthdayServlet";//!!!!!!!!!
      JSONArray o=new JSONArray(HttpUtil.postRequest(url,map));
      Log.i("TestLog", "o="+o.toString());
      return o;
    }
  
  private JSONArray getbirthdaynamejson(int canteenid) throws Exception{
      Map<String,String> map=new HashMap<String,String>();
      map.put("canteenid", String.valueOf(canteenid));
      String url=HttpUtil.BASE_URL+"BirthdayNameServlet";//!!!!!!!!!
      JSONArray o=new JSONArray(HttpUtil.postRequest(url,map));
      Log.i("TestLog", "o="+o.toString());
      return o;
    }
  
  private JSONArray setbirthdaybooknamejson(String name) throws Exception{
      Map<String,String> map=new HashMap<String,String>();
      map.put("name", String.valueOf(name));
      String url=HttpUtil.BASE_URL+"DeleteBirthdaybook";//!!!!!!!!!
      JSONArray o=new JSONArray(HttpUtil.postRequest(url,map));
      Log.i("TestLog", "o="+o.toString());
      return o;
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
