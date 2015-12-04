package com.example.newapps;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;


import android.support.v4.app.Fragment;
import android.R.integer;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Build;

public class MainStart extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// ÎÞtitle
		 requestWindowFeature(Window.FEATURE_NO_TITLE);
		// È«ÆÁ
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				 WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.start);
		final Intent localIntent=new Intent(this,MainExplain.class);
		  Timer timer=new Timer();
		  TimerTask tast=new TimerTask()
		  {
		   @Override
		   public void run(){
		    startActivity(localIntent);
		    finish();
		   }
		  };
		  timer.schedule(tast,1000*2);

	}

}
