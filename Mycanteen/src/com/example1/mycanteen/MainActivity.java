package com.example1.mycanteen;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    Button bnworker=(Button) findViewById(R.id.button1);
    bnworker.setOnClickListener(new OnClickListener()
        {
            public void onClick(View source)
            {
              Intent intent=new Intent(MainActivity.this,Workerlogin.class);
              startActivity(intent);
            }
        });
    Button bnstudent=(Button) findViewById(R.id.button2);
    bnstudent.setOnClickListener(new OnClickListener()
    {
        public void onClick(View source)
        {
          Intent intent=new Intent(MainActivity.this,Studentchoose.class);
          startActivity(intent);
        }
    });
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.main, menu);
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
