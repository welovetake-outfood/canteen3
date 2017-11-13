package com.example1.mycanteen;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class LoginSuccess extends Activity {
  public Schoolcanteen.Canteen canteen;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_loginsuccess);
    ImageButton buttonup1 = (ImageButton) findViewById(R.id.WorkerFuntion1);
    buttonup1.setOnClickListener(new View.OnClickListener() {   
        @Override
        public void onClick(View v) {
            Intent intent=new Intent(LoginSuccess.this,AddMenu.class);
            startActivity(intent);
        }
    });
    ImageButton buttonup2 = (ImageButton) findViewById(R.id.WorkerFuntion2);
    buttonup2.setOnClickListener(new View.OnClickListener() {   
        @Override
        public void onClick(View v) {
            Intent intent=new Intent(LoginSuccess.this,ReleaseAnnouncement.class);
            startActivity(intent);
        }
    });
    ImageButton buttonup3 = (ImageButton) findViewById(R.id.WorkerFuntion3);
    buttonup3.setOnClickListener(new View.OnClickListener() {   
        @Override
        public void onClick(View v) {
            Intent intent=new Intent(LoginSuccess.this,CheckBirthday.class);
            startActivity(intent);
        }
    });
    ImageButton buttonup4 = (ImageButton) findViewById(R.id.WorkerFuntion4);
    buttonup4.setOnClickListener(new View.OnClickListener() {   
        @Override
        public void onClick(View v) {
            Intent intent=new Intent(LoginSuccess.this,CheckAppointment.class);
            startActivity(intent);
        }
    });
    ImageButton buttonup5 = (ImageButton) findViewById(R.id.WorkerFuntion5);
    buttonup5.setOnClickListener(new View.OnClickListener() {   
        @Override
        public void onClick(View v) {
            Intent intent=new Intent(LoginSuccess.this,ViewMassage.class);
            startActivity(intent);
        }
    });
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.loginsuccess, menu);
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
