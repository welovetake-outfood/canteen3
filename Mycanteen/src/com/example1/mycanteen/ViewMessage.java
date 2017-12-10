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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class ViewMessage extends Activity {
  public Schoolcanteen.Canteen canteen;
  private ListView listview;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_viewmessage);
    listview=(ListView) findViewById(R.id.listView1);
    Intent intent=getIntent();
    canteen=(Schoolcanteen.Canteen)intent.getSerializableExtra("canteen");
    listview.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1,getData()));
    
  }
  
private List<String> getData(){ 
    List<String> data = new ArrayList<String>();
    //return data;
    JSONArray o;
    try {
      o=getcommentjson(canteen.id);
      for (int i=0;i<o.length();i++)
      {
        JSONObject jsonobject=o.getJSONObject(i);
        String a=jsonobject.getString("message");
        //Log.i("TestLog", a+"is comment");
        //String a=o.getJSONObject(i).toString();
        //data.add(jsonobject.getString("comment"));
        data.add(a);
        Log.i("TestLog", a+"is message");
      }
    }    
    catch (Exception e) {
      e.printStackTrace();
    }
    Log.i("TestLog", data.toString());
    return data;
}

private JSONArray getcommentjson(int canteenid) throws Exception{
  Map<String,String> map=new HashMap<String,String>();
  map.put("canteenid", String.valueOf(canteenid));
  String url=HttpUtil.BASE_URL+"GetcanteenmessageServlet";//!!!!!!!!!
  JSONArray o=new JSONArray(HttpUtil.postRequest(url,map));
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
