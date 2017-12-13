package com.example1.mycanteen;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NewMenu extends Activity {
  public Schoolcanteen.Canteen canteen;
  private EditText dishnameinput,dishpriceinput,dishdescinput;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_newmenu);
    Button buttonup1 = (Button) findViewById(R.id.button1);
    buttonup1.setOnClickListener(new View.OnClickListener() {   
        @Override
        public void onClick(View v) {
            Intent intent=new Intent(NewMenu.this,ExistingMenu.class);
            startActivity(intent);
        }
    });
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.addmenu, menu);
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
