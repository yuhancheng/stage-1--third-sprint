package com.example.newapps;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ChooseType extends Activity {
	private Button bt1,bt2;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		// ÎÞtitle
		// requestWindowFeature(Window.FEATURE_NO_TITLE);
		// È«ÆÁ
		//getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				// WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.choosetype);
		bt1=(Button)findViewById(R.id.button1);
		bt2=(Button)findViewById(R.id.button2);
		bt2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(ChooseType.this,TianKong.class);
				 startActivity(intent); 
				 finish();
			}
		});
bt1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(ChooseType.this,MainActivity.class);
				 startActivity(intent); 
				 finish();
			}
		});
		
	}

}