package com.example1.mycanteen;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class Update extends Activity {
  Dish dish;
  EditText edit1,edit2,edit3;
  TextView tip,name;
  private Getimage g=new Getimage();
  String picturename;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_update);
    Intent intent=getIntent();
    picturename=(String)intent.getSerializableExtra("picturename");
    dish=getadish(picturename);
    edit1=(EditText) findViewById(R.id.updatemenueditText1);
    edit2=(EditText) findViewById(R.id.updatemenueditText2);
    edit3=(EditText) findViewById(R.id.updatemenueditText3);
    tip=(TextView) findViewById(R.id.updatemenutextView1);
    name=(TextView) findViewById(R.id.updatemenutextview1);
    name.setText(dish.getDishname());
    Button commitcomment=(Button) findViewById(R.id.updatemenubutton1);
    final ImageView ima=(ImageView) findViewById(R.id.updatemenuimageView1);
    //edit1.setHint(dish.getDishname());
    //edit2.setHint(dish.getDishprice());
    //edit3.setHint(dish.getDishintrodiction());
    final Handler handler = new Handler()
    {
        public void handleMessage(android.os.Message msg) 
        {
            //更新UI
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
    commitcomment.setOnClickListener(new View.OnClickListener() {   
      @Override
      public void onClick(View v) {
          if (validate()) {
            //Log.i("TestLog", "aaaaaaaaaaaaaa");
            int flag=updatedish(picturename);
              if (flag>0) {
                final Bundle data=new Bundle();
                data.putSerializable("picturename",picturename);
                Intent intent=new Intent(Update.this,Update.class);
                intent.putExtras(data);
                startActivity(intent);
                finish();
              }
              else {
                tip.setText("提示：错误，重新输入");
              }
          }
      }
  });
    
  }
  
  private int updatedish(String picturename) {
    String dishname=edit1.getText().toString();
    String dishprice=edit2.getText().toString();
    String dishintro=edit3.getText().toString();
    JSONObject jsonObj;
    try {
      jsonObj=change(picturename,dishprice,dishname,dishintro);
      if (jsonObj.getInt("rflag")>0) {
        return jsonObj.getInt("rflag");
      }
    }
    catch (Exception e) {
      tip.setText("提示：服务器异常，请稍后再试");
      e.printStackTrace();
    }
    return 0;
  }
  
  private JSONObject change(String picturename,String dishprice,String dishname,String dishintro) throws Exception{
    Map<String,String> map=new HashMap<String,String>();
    map.put("picturename", picturename);
    map.put("dishprice", dishprice+"");
    map.put("dishname", dishname);
    map.put("dishintro", dishintro);
    //Log.i("TestLog", "cccccccccccc"+pwd);
    String url=HttpUtil.BASE_URL+"UpdateServlet";//!!!!!
    //String url="http://10.0.2.2:8080/Mycanteenserver/LoginServlet?userid=002&password=123";
    JSONObject o=new JSONObject(HttpUtil.postRequest(url,map));
    //Log.i("TestLog", url);
    //o=JSONObject.fromObject(HttpUtil.getRequest(url));
    return o;
  }
  
  private boolean validate() {
    String dishname=edit1.getText().toString().trim();
    String dishprice=edit1.getText().toString().trim();
    String dishintro=edit1.getText().toString().trim();
    if (dishname.equals("")||dishprice.equals("")||dishintro.equals("")) {
      tip.setText("提示：必须都填");
      //Log.i("TestLog", "aaaaaaaaaaaaaa");
      return false;
    }
    return true;
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
    getMenuInflater().inflate(R.menu.update, menu);
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
