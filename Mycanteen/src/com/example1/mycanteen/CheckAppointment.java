package com.example1.mycanteen;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class CheckAppointment extends Activity {
  public Schoolcanteen.Canteen canteen;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_checkappointment);
    Intent intent=getIntent();
    canteen=(Schoolcanteen.Canteen)intent.getSerializableExtra("canteen");
    //mdatas=getbirthdaymsg(canteen.getId());
    final ListView p = (ListView) findViewById(R.id.listView1);
    final TextView text = (TextView) findViewById(R.id.appointmenttext);
    p.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1,getData()));
    p.setOnItemClickListener(new ListView.OnItemClickListener(){
    @Override
    public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
        // TODO Auto-generated method stub
        JSONArray o = null;
        try {
            o=getcanteenbookjson(canteen.getId());
            String[] appointmenttext = new String[o.length()];
            for(int i=0;i<o.length();i++){
                JSONObject jsonobject=o.getJSONObject(i);
                if(jsonobject.getInt("lunchordinner")==1) {
                    appointmenttext[i] = "预约人姓名：" + jsonobject.getString("peoplename") + "\n" + "联系方式：" 
                        + jsonobject.getString("phonenumber")  + "\n" + "预约日期：" + jsonobject.getInt("year") 
                        + "/" + jsonobject.getInt("month") + "/" + jsonobject.getInt("day") + "\n" 
                        + "预约时间：午餐" + "\n" + "用餐人数：" + jsonobject.getInt("peoplenumber");
                }
                if(jsonobject.getInt("lunchordinner")==2) {
                    appointmenttext[i] = "预约人姓名：" + jsonobject.getString("peoplename") + "\n" + "联系方式：" 
                        + jsonobject.getString("phonenumber")  + "\n" + "预约日期：" + jsonobject.getInt("year") 
                        + "/" + jsonobject.getInt("month") + "/" + jsonobject.getInt("day") + "\n" 
                        + "预约时间：晚餐" + "\n" + "用餐人数：" + jsonobject.getInt("peoplenumber");
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
                o=getcanteenbookjson(canteen.getId());
                JSONObject jsonobject=o.getJSONObject(arg2);
                String name = jsonobject.getString("peoplename"); //获取所需删除数据的名字
                System.out.println(name);
                setcanteenbooknamejson(name);
                refresh();
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
        o=getcanteenbooknamejson(canteen.id);
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
  
  private JSONArray getcanteenbookjson(int canteenid) throws Exception{
      Map<String,String> map=new HashMap<String,String>();
      map.put("canteenid", String.valueOf(canteenid));
      String url=HttpUtil.BASE_URL+"CheckCanteenbookServlet";//!!!!!!!!!
      JSONArray o=new JSONArray(HttpUtil.postRequest(url,map));
      Log.i("TestLog", "o="+o.toString());
      return o;
    }
  
  private JSONArray getcanteenbooknamejson(int canteenid) throws Exception{
      Map<String,String> map=new HashMap<String,String>();
      map.put("canteenid", String.valueOf(canteenid));
      String url=HttpUtil.BASE_URL+"CanteenbookNameServlet";//!!!!!!!!!
      JSONArray o=new JSONArray(HttpUtil.postRequest(url,map));
      Log.i("TestLog", "o="+o.toString());
      return o;
    }
  
  private JSONArray setcanteenbooknamejson(String name) throws Exception{
      Map<String,String> map=new HashMap<String,String>();
      map.put("name", String.valueOf(name));
      String url=HttpUtil.BASE_URL+"DeleteCanteenbook";//!!!!!!!!!
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
