package com.example1.mycanteen;

import java.lang.reflect.Field;

import com.example1.mycanteen.R.drawable;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

public class Menudetail extends Activity {
  Dish dish;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_menudetail);
    TextView text=(TextView) findViewById(R.id.menudetailtextview);
    ImageView ima=(ImageView) findViewById(R.id.menudetailimageView1);
    Intent intent=getIntent();
    dish=(Dish)intent.getSerializableExtra("dish");
    text.setText(dish.getPicturename()+" dish");
    Class<drawable> drawable  =  R.drawable.class;
    Field field = null;
    try {
        field = drawable.getField(dish.getPicturename());
        int r_id = field.getInt(field.getName());
        ima.setImageResource(r_id);
    } catch (Exception e) {
        Log.i("ERROR", "PICTURE NOT¡¡FOUND£¡");
    }
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
