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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class ExistingMenu extends Activity {
  public Schoolcanteen.Canteen canteen;
  public List<Dish> dishes;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_existingmenu);
    Intent intent=getIntent();
    canteen=(Schoolcanteen.Canteen)intent.getSerializableExtra("canteen");
    final ListView p = (ListView) findViewById(R.id.existinglistView1);
    final TextView text = (TextView) findViewById(R.id.existingappointmenttext);
    dishes=getdish(canteen.id);
    p.setAdapter(new ArrayAdapter<String>(this, R.layout.array_adapter,getData(dishes)));
    p.setOnItemClickListener(new ListView.OnItemClickListener(){
    @Override
    public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
        // TODO Auto-generated method stub
        //JSONArray o = null;
        try {
            //o=(JSONArray) getdish(canteen.id);
            String[] appointmenttext = new String[dishes.size()];
            for(int i=0;i<dishes.size();i++){
                //JSONObject jsonobject=o.getJSONObject(i);
                appointmenttext[i] = "菜品价格：" + dishes.get(i).getDishprice() +" 元\n" + "菜品介绍：" 
                        + dishes.get(i).getDishintrodiction() + "\n菜品评分：" + dishes.get(i).getDishscore() ;
            }
            text.setText("菜品详情：\n" + appointmenttext[arg2]);
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
            //长按修改菜品
          final Bundle data=new Bundle();
          data.putSerializable("picturename", (dishes.get(arg2)).getPicturename());
          //data.putInt("position", ptemp);
          //data.putParcelable("bitmap", b);
          Intent intent=new Intent(ExistingMenu.this,Update.class);
          intent.putExtras(data);
          //intent.putExtra("map",map);
          startActivity(intent);
            return true;  
        }  
    }); 
  }
  
  public void refresh() {
      onCreate(null);
  }
  
  private List<String> getData(List<Dish> dishes){
    List<String> data = new ArrayList<String>();
    //return data;
    for (int i=0;i<dishes.size();i++)
    {
      String name=dishes.get(i).getDishname();
      data.add(name);
    }
    return data;
}
  
  private List<Dish> getdish(int canteenid){
    List<Dish> d=new ArrayList<Dish>();
    JSONArray o;
    try {
      //Log.i("TestLog", "bbbbbbbbbbloginpro");
      o=getdishjson(canteenid);
      for (int i=0;i<o.length();i++)
      {
        JSONObject jsonobject=o.getJSONObject(i);
        Dish a=new Dish();
        a.setCanteenid(jsonobject.getInt("canteenid"));
        a.setDishintrodiction(jsonobject.getString("dishintrodiction"));
        a.setDishprice(jsonobject.getInt("dishprice"));
        a.setDishscore((float)jsonobject.getDouble("dishscore"));
        a.setPicturename(jsonobject.getString("picturename"));
        a.setCommentpeople(jsonobject.getInt("commentpeople"));
        a.setDishname(jsonobject.getString("dishname"));
        d.add(a);
      }
      //d= (List<Dish>)JSONArray.toCollection(o, Dish.class); //!!!!!!!!!!!!!!
      //d=(List)o.get("dish");
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    return d;
  }
  
  private JSONArray getdishjson(int canteenid) throws Exception{
    Map<String,String> map=new HashMap<String,String>();
    map.put("canteenid", String.valueOf(canteenid));
    String url=HttpUtil.BASE_URL+"GetdishesServlet";
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
