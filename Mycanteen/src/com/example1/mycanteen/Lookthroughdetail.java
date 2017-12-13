package com.example1.mycanteen;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class Lookthroughdetail extends Activity {
  public Schoolcanteen.Canteen canteen;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    //TextView text=(TextView) findViewById(R.id.detail);
    Intent intent=getIntent();
    canteen=(Schoolcanteen.Canteen)intent.getSerializableExtra("canteen");
    switch(canteen.getId()) {
    case 1:
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lookthroughdetail1);
        break;
    case 2:
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lookthroughdetail2);
        break;
    case 3:
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lookthroughdetail3);
        break;
    case 4:
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lookthroughdetail4);
        break;
    case 5:
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lookthroughdetail5);
        break;
    default:
        break;
    }
    //text.setText(canteen.name+" lookthroughdetail");
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.lookthroughdetail, menu);
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
