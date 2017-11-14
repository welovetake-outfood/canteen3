package com.example1.mycanteen;

//import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
//import android.view.Menu;
//import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
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
      ((TextView)rootView.findViewById(R.id.canteenname)).setText(canteen.name);
      ((TextView)rootView.findViewById(R.id.canteendesc)).setText(canteen.notice);
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
      ImageButton bncomment=(ImageButton)rootView.findViewById(R.id.buttoncomment);
      bncomment.setOnClickListener(new OnClickListener()
      {
          public void onClick(View v)
          {
            Intent intent=new Intent(getActivity(),Givecomment.class);
            intent.putExtras(data);
            startActivity(intent);
          }
      });
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

}
