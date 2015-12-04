package com.example.newapps;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainExplain extends Activity {
	private Button e1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// ÎÞtitle
		 requestWindowFeature(Window.FEATURE_NO_TITLE);
		// È«ÆÁ

		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				 WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.explain);
		e1=(Button)this.findViewById(R.id.e1);
		
		e1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				//Toast.makeText(MainActivity.this,"hello", 1).show();
				 Intent intent = new Intent(MainExplain.this,ChooseType.class);
				 startActivity(intent); 
				 finish();

			}
		});
		
	}

}
