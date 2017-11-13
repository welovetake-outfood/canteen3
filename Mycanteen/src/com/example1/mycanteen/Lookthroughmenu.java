package com.example1.mycanteen;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import com.example1.mycanteen.MyHorizontalScrollView.CurrentImageChangeListener;
import com.example1.mycanteen.MyHorizontalScrollView.OnItemClickListener;
import com.example1.mycanteen.R.drawable;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
//import android.widget.TextView;
//import net.sf.json.JSONArray;

public class Lookthroughmenu extends Activity {
  public Schoolcanteen.Canteen canteen;
  private MyHorizontalScrollView mHorizontalScrollView; 
  private HorizontalScrollViewAdapter mAdapter;  
  private ImageView mImg;
  private List<Dish> mdatas;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    //Log.i("TestLog", "aaaaaaaaaaaaaaa");
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_lookthroughmenu);
    //Log.i("TestLog", "bbbbbbbbbbbmenu");
    Intent intent=getIntent();
    canteen=(Schoolcanteen.Canteen)intent.getSerializableExtra("canteen");
    //Log.i("TestLog", "cccccccccccccccmenu");
    mHorizontalScrollView = (MyHorizontalScrollView) findViewById(R.id.id_horizontalScrollView);
    //Log.i("TestLog", "ddddddddddddmenu");
    mImg=(ImageView) findViewById(R.id.id_content);  
    //Log.i("TestLog", "eeeeeeeeeeeeeeeeeemenu");
    mdatas=getdish(canteen.getId());
    //Log.i("TestLog", "fffffffffffffmenu");
    mAdapter = new HorizontalScrollViewAdapter(this, mdatas);
  //添加滚动回调  
    //Log.i("TestLog", "ccccccccccccc");
    mHorizontalScrollView  
            .setCurrentImageChangeListener(new CurrentImageChangeListener()  
            {  
                public void onCurrentImgChanged(int position,  
                        View viewIndicator)  
                {  
                  Class<drawable> drawable  =  R.drawable.class;
                  Field field = null;
                  try {
                    field = drawable.getField(mdatas.get(position).getPicturename());
                    int r_id = field.getInt(field.getName());
                    mImg.setImageResource(r_id);
                      viewIndicator.setBackgroundColor(Color  
                              .parseColor("#AA024DA4"));  
                } catch (Exception e) {
                    Log.i("ERROR", "PICTURE NOT　FOUND！");
                }
                }  
            });  
    //添加点击回调  
    mHorizontalScrollView.setOnItemClickListener(new OnItemClickListener()  
    {  

        public void onClick(View view, int position)  
        {  
          final int ptemp=position;
            //mImg.setImageResource(mdatas.get(position));
          Class<drawable> drawable  =  R.drawable.class;
          Field field = null;
          try {
            field = drawable.getField(mdatas.get(position).getPicturename());
            int r_id = field.getInt(field.getName());
            mImg.setImageResource(r_id);
            mImg.setOnClickListener(new OnClickListener() {
              public void onClick(View v)
              {
                final Bundle data=new Bundle();
                data.putSerializable("picturename", (mdatas.get(ptemp)).getPicturename());
                Intent intent=new Intent(Lookthroughmenu.this,Menudetail.class);
                intent.putExtras(data);
                startActivity(intent);
              }
            });
            view.setBackgroundColor(Color.parseColor("#AA024DA4"));  
        } catch (Exception e) {
            Log.i("ERROR", "PICTURE NOT　FOUND！");
        }
          }  
    });  
    //设置适配器  
    mHorizontalScrollView.initDatas(mAdapter);
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
    getMenuInflater().inflate(R.menu.lookthroughmenu, menu);
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
