package com.example1.mycanteen;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;

public class Updatemenu extends Activity {
  Dish dish;
  //private EditText edit1,edit2,edit3;
  private Getimage g=new Getimage();
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_updatemenu);
    Intent intent=getIntent();
    String picturename=(String)intent.getSerializableExtra("picturename");
    dish=getadish(picturename);
    final EditText edit1=(EditText) findViewById(R.id.updatemenueditText1);
    final EditText edit2=(EditText) findViewById(R.id.updatemenueditText2);
    final EditText edit3=(EditText) findViewById(R.id.updatemenueditText3);
    final ImageView ima=(ImageView) findViewById(R.id.updatemenuimageView1);
    edit1.setText(dish.getDishname());
    edit2.setText(dish.getDishprice());
    edit3.setText(dish.getDishintrodiction());
    final Handler handler = new Handler()
    {
        public void handleMessage(android.os.Message msg) 
        {
            //¸üÐÂUI
            //ImageView imageView = (ImageView) findViewById(R.id.lv);
            ima.setImageBitmap((Bitmap) msg.obj);
        };
    };  
    final String path=HttpUtil.BASE_URL+"i/"+picturename+".JPG";
    //final int positiontemp=position;
    Thread thread = new Thread() {
      @Override
      public void run() {
        Bitmap bm=g.get(path);
        //g.blist[positiontemp]=bm;
        Message msg = handler.obtainMessage();
        msg.obj = bm;
        handler.sendMessage(msg);
      }
    };
    thread.start();
  }
  
  
  private Dish getadish(String picturename){
    Dish a=new Dish();
    JSONObject o;
    try {
      //Log.i("TestLog", "bbbbbbbbbbloginpro");
      o=getdishjson(picturename);
      //for (int i=0;i<o.length();i++)
      //{
        //JSONObject jsonobject=o.getJSONObject(picturename);
        //Dish a=new Dish();
        a.setCanteenid(o.getInt("canteenid"));
        a.setDishintrodiction(o.getString("dishintrodiction"));
        a.setDishprice(o.getInt("dishprice"));
        a.setDishscore((float)o.getDouble("dishscore"));
        a.setPicturename(o.getString("picturename"));
        a.setCommentpeople(o.getInt("commentpeople"));
        a.setDishname(o.getString("dishname"));
        //d.add(a);
      //}
      //d= (List<Dish>)JSONArray.toCollection(o, Dish.class); //!!!!!!!!!!!!!!
      //d=(List)o.get("dish");
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    return a;
  }
  
  private JSONObject getdishjson(String picturename) throws Exception{
    Map<String,String> map=new HashMap<String,String>();
    map.put("picturename", picturename);
    String url=HttpUtil.BASE_URL+"GetdishServlet";
    JSONObject o=new JSONObject(HttpUtil.postRequest(url,map));
    return o;
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.updatemenu, menu);
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
