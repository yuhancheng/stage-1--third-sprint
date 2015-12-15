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
import android.widget.TextView;

public class MainExplain extends Activity {
	private Button e1;
	String username;
	private TextView tv1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 无title
		 requestWindowFeature(Window.FEATURE_NO_TITLE);
		// 全屏

		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				 WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.explain);
		username = getIntent().getStringExtra("username");
		e1=(Button)this.findViewById(R.id.e1);
		tv1=(TextView)findViewById(R.id.textView1);
		tv1.setText(username+"同学欢迎使用");
		
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
