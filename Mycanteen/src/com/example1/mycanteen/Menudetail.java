package com.example1.mycanteen;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import com.example1.mycanteen.R.drawable;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
//import android.widget.RatingBar.OnRatingBarChangeListener;
import android.widget.TextView;

public class Menudetail extends Activity {
  Dish dish;
  String picturename;
  int position;
  private Getimage g=new Getimage();
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_menudetail);
    TextView text1=(TextView) findViewById(R.id.menudetailtextview1);
    TextView text2=(TextView) findViewById(R.id.menudetailtextView2);
    TextView text3=(TextView) findViewById(R.id.menudetailtextView3);
    TextView text4=(TextView) findViewById(R.id.menudetailtextView4);
    final ImageView ima=(ImageView) findViewById(R.id.menudetailimageView1);
    final RatingBar star=(RatingBar) findViewById(R.id.menudetailratingBar1);
    final EditText comment=(EditText) findViewById(R.id.menudetaileditText);
    Button commitcomment=(Button) findViewById(R.id.menudetailbutton1);
    Intent intent=getIntent();
    picturename=(String)intent.getSerializableExtra("picturename");
    dish=getadish(picturename);
    text1.setText(dish.getDishname());
    text2.append(Integer.toString(dish.getDishprice()));
    text3.append((dish.getDishscore())+"");
    text4.append(dish.getDishintrodiction());
    /*Class<drawable> drawable  =  R.drawable.class;
    Field field = null;
    try {
        field = drawable.getField(dish.getPicturename());
        int r_id = field.getInt(field.getName());
        ima.setImageResource(r_id);
    } catch (Exception e) {
        Log.i("ERROR", "PICTURE NOT　FOUND！");
    }*/
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
    //ima.setImageBitmap(bmp);
    commitcomment.setOnClickListener(new View.OnClickListener() {   
      @Override
      public void onClick(View v) {
        String dishname=dish.getPicturename();
        String usercomment=comment.getText().toString();
        float score=star.getRating();
        int commentpeople=dish.getCommentpeople();
        float finalscore=(commentpeople*(dish.getDishscore())+score)/(commentpeople+1);
        if (addcomment(usercomment,dishname)>0 && changescore(finalscore,commentpeople+1,dishname)>0) {
          final Bundle data=new Bundle();
          data.putSerializable("picturename",picturename);
          Intent intent=new Intent(Menudetail.this,Menudetail.class);
          intent.putExtras(data);
          startActivity(intent);
          finish();
        }
        else {
          
        }
      }
  });
    
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
  
  private int changescore(float score,int commentpeople,String dishname) {
    JSONObject jsonObj;
    try {
      //Log.i("TestLog", "bbbbbbbbbbloginpro");
      jsonObj=change(score,commentpeople,dishname);
      if (jsonObj.getInt("rflag")>0) {
        return jsonObj.getInt("rflag");
      }
    }
    catch (Exception e) {
      //text.setText("提示：服务器异常，请稍后再使");
      e.printStackTrace();
    }
    return 0;
  }
   
  private int addcomment(String usercomment,String dishname) {
    JSONObject jsonObj;
    try {
      //Log.i("TestLog", "bbbbbbbbbbloginpro");
      jsonObj=add(usercomment,dishname);
      if (jsonObj.getInt("flag")>0) {
        return jsonObj.getInt("flag");
      }
    }
    catch (Exception e) {
      //text.setText("提示：服务器异常，请稍后再使");
      e.printStackTrace();
    }
    return 0;
  }

  private JSONObject change(float score,int commentpeople,String dishname) throws Exception{
    Map<String,String> map=new HashMap<String,String>();
    map.put("dishscore", score+"");
    map.put("commentpeople", commentpeople+"");
    map.put("picturename", dishname);
    //Log.i("TestLog", "cccccccccccc"+pwd);
    String url=HttpUtil.BASE_URL+"Scoreupdate";
    //String url="http://10.0.2.2:8080/Mycanteenserver/LoginServlet?userid=002&password=123";
    JSONObject o=new JSONObject(HttpUtil.postRequest(url,map));
    //Log.i("TestLog", url);
    //o=JSONObject.fromObject(HttpUtil.getRequest(url));
    return o;
  }
  
  private JSONObject add(String usercomment,String dishname) throws Exception{
    Map<String,String> map=new HashMap<String,String>();
    map.put("comment", usercomment);
    map.put("dishname", dishname);
    //Log.i("TestLog", "cccccccccccc"+pwd);
    String url=HttpUtil.BASE_URL+"Givecommentservlet";
    //String url="http://10.0.2.2:8080/Mycanteenserver/LoginServlet?userid=002&password=123";
    JSONObject o=new JSONObject(HttpUtil.postRequest(url,map));
    //Log.i("TestLog", url);
    //o=JSONObject.fromObject(HttpUtil.getRequest(url));
    return o;
  }
    
  
  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menudetail, menu);
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
