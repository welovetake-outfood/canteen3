package com.example1.mycanteen;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class WorkerRegister extends Activity {
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_workerregister);
    final Button buttonup = (Button) findViewById(R.id.Register);
    final EditText name = (EditText) findViewById(R.id.editName);
    final TextView text = (TextView) findViewById(R.id.info);
    buttonup.setOnClickListener(new View.OnClickListener() {   
        @Override
        public void onClick(View v) {
            text.setText("提示:");
            if (name.getText().toString() == null || name.getText().toString().length() == 0) {
                text.setText("提示:用户名为空！");
            }
            if (name.getText().toString().length() > 12) {
                text.setText("提示:用户名过长！");
            }
            if (name.getText().toString().length() <= 0  
                    || name.getText().toString().matches(".*[/\\\\:*?\"<>|\t].*")  || name.getText().toString().matches(".*\\p{So}.*")) {
                text.setText("提示:用户名包含非法字符！");
            } else {
                
            }
        }
    });
        
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
