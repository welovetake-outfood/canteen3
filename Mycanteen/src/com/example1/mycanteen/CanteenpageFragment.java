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
import android.widget.TextView;

public class CanteenpageFragment extends Fragment {
  public static final String ITEM_ID="item_id";
  Schoolcanteen.Canteen canteen;
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    if (getArguments().containsKey(ITEM_ID)) {
      canteen=Schoolcanteen.ITEM_MAP.get(getArguments().getInt(ITEM_ID));
    }
    //setContentView(R.layout.fragment_canteenpage);
  }
  
  public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstancesState) {
    View rootView=inflater.inflate(R.layout.fragment_canteenpage, container,false);
    if (canteen!=null) {
      ((TextView)rootView.findViewById(R.id.canteenname)).setText(canteen.name);
      //((TextView)rootView.findViewById(R.id.canteendesc)).setText(canteen.desc);
      Button bnbook=(Button)rootView.findViewById(R.id.buttonbook);
      bnbook.setOnClickListener(new OnClickListener()
      {
          public void onClick(View v)
          {
            Intent intent=new Intent(getActivity(),Bookcanteen.class);
            startActivity(intent);
          }
      });
    }
    return rootView;
  }

}
