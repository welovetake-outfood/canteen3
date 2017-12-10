package com.example1.mycanteen;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class Studentchoose extends Activity implements CanteenlistFragment.Callbacks{

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_studentchoose);
    Bundle arguments=new Bundle();
    arguments.putInt(CanteenpageFragment.ITEM_ID, 1);
    CanteenpageFragment fragment=new CanteenpageFragment();
    fragment.setArguments(arguments);
    getFragmentManager().beginTransaction().replace(R.id.canteenpage_container, fragment).commit();
  }
  
  public void onItemSelected(Integer id) {
    Bundle arguments=new Bundle();
    arguments.putInt(CanteenpageFragment.ITEM_ID, id);
    CanteenpageFragment fragment=new CanteenpageFragment();
    fragment.setArguments(arguments);
    getFragmentManager().beginTransaction().replace(R.id.canteenpage_container, fragment).commit();
  }
  
}
