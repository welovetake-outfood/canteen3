package com.example1.mycanteen;

import java.util.HashMap;
import java.util.Map;
import android.text.TextUtils;
import org.json.JSONObject;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;

public class CanteenpageFragment extends Fragment {
  public static final String ITEM_ID="item_id";
  public Schoolcanteen.Canteen canteen;
  public int fragmentid=0;
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    if (getArguments().containsKey(ITEM_ID)) {
      fragmentid=getArguments().getInt(ITEM_ID);
      canteen=Schoolcanteen.ITEM_MAP.get(fragmentid);
    }
    //setContentView(R.layout.fragment_canteenpage);
  }
  
  public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstancesState) {
    View rootView=inflater.inflate(R.layout.fragment_canteenpage, container,false);
    if (canteen!=null) {
      TextView t=(TextView)rootView.findViewById(R.id.canteenname);
      t.setText(canteen.name);
      ((TextView)rootView.findViewById(R.id.canteendesc)).setText(getdescPro());
      final Bundle data=new Bundle();
      data.putSerializable("canteen", canteen);
      //---------------------------------------------------------------
      ImageButton bnbook=(ImageButton)rootView.findViewById(R.id.buttonbook);
      bnbook.setOnClickListener(new OnClickListener()
      {
          public void onClick(View v)
          {
            Intent intent=new Intent(getActivity(),Bookcanteen.class);
            intent.putExtras(data);
            startActivity(intent);
          }
      });
      //------------------------------------------------------------
      ImageButton bnlm=(ImageButton)rootView.findViewById(R.id.buttonlm);
      bnlm.setOnClickListener(new OnClickListener()
      {
          public void onClick(View v)
          {
            Intent intent=new Intent(getActivity(),Leavemessage.class);
            intent.putExtras(data);
            startActivity(intent);
          }
      });
      //----------------------------------------------------------------
      ImageButton bnbir=(ImageButton)rootView.findViewById(R.id.buttonbir);
      bnbir.setOnClickListener(new OnClickListener()
      {
          public void onClick(View v)
          {
            Intent intent=new Intent(getActivity(),Birthdaybook.class);
            intent.putExtras(data);
            startActivity(intent);
          }
      });
      //------------------------------------------------------------------------

      //-------------------------------------------------------------------------
      ImageButton bnmenu=(ImageButton)rootView.findViewById(R.id.buttonmenu);
      bnmenu.setOnClickListener(new OnClickListener()
      {
          public void onClick(View v)
          {
            Intent intent=new Intent(getActivity(),Lookthroughmenu.class);
            intent.putExtras(data);
            startActivity(intent);
          }
      });
      //----------------------------------------------------------------------------
      ImageButton bndetail=(ImageButton)rootView.findViewById(R.id.buttondetail);
      bndetail.setOnClickListener(new OnClickListener()
      {
          public void onClick(View v)
          {
            Intent intent=new Intent(getActivity(),Lookthroughdetail.class);
            intent.putExtras(data);
            startActivity(intent);
          }
      });
    }
    return rootView;
  }

  private String getdescPro() {
    int canteenid=canteen.id;
    JSONObject jsonObj;
    String desc="服务器异常，请稍后再使";
    try {
      //Log.i("TestLog", "bbbbbbbbbbloginpro");
      jsonObj=query(canteenid);
      desc=jsonObj.getString("canteendesc");
      /*if (desc!="") {
        return jsonObj.getString("canteenid");
      }*/
    }
    catch (Exception e) {
      //text.setText("提示：服务器异常，请稍后再使");
      e.printStackTrace();
    }
    return desc;
  }
  private JSONObject query(int canteenid) throws Exception{
    Map<String,String> map=new HashMap<String,String>();
    map.put("canteenid", String.valueOf(canteenid));
    //map.put("password", pwd);
    //Log.i("TestLog", "cccccccccccc"+pwd);
    String url=HttpUtil.BASE_URL+"GetdescServlet";
    //String url="http://10.0.2.2:8080/Mycanteenserver/LoginServlet?userid=002&password=123";
    JSONObject o=new JSONObject(HttpUtil.postRequest(url,map));
    //Log.i("TestLog", url);
    //o=JSONObject.fromObject(HttpUtil.getRequest(url));
    return o;
  }
  

}