package com.example.newapps;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;



import android.support.v4.app.Fragment;
import android.R.integer;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Build;

public class MainActivity extends Activity {
	private Button button1;
	private Button button2;
	private Button sb1,sb2,sb3;
	private Button ab1, ab2, ab3, ab4;
	private TextView textView1,textView2,textView3,textView4;
	private int TiShu = 0, TrueAnswer = 0;
	private int a = 0, b = 0;
	private int count = 0;
	private int TrueNum = 0;
	private char op;
	private int UserAnswer = 0;
	private SoundPool sp;
	private int ready, yes, no;
	private List<String> list = new ArrayList<String>();
	private TextView myTextView;
	private Spinner mySpinner;
	private ArrayAdapter<String> adapter;
	private int nandu=1;
	private int Spinner=0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 无title
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		// 全屏
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_main);
		button1 = (Button) this.findViewById(R.id.button1);
		button2 = (Button) this.findViewById(R.id.button2);
		sb1 = (Button) this.findViewById(R.id.sb1);
		sb2 = (Button) this.findViewById(R.id.sb2);
		sb3 = (Button) this.findViewById(R.id.sb3);
		ab1 = (Button) this.findViewById(R.id.ab1);
		ab2 = (Button) this.findViewById(R.id.ab2);
		ab3 = (Button) this.findViewById(R.id.ab3);
		ab4 = (Button) this.findViewById(R.id.ab4);
		textView1 = (TextView) this.findViewById(R.id.textView1);
		textView2 = (TextView) this.findViewById(R.id.textView2);
		textView3 = (TextView) this.findViewById(R.id.textView3);
		textView4 = (TextView) this.findViewById(R.id.textView4);
		sp = new SoundPool(10, AudioManager.STREAM_SYSTEM, 5);
		ready = sp.load(this, R.raw.readygo, 1);
		yes = sp.load(this, R.raw.yes, 1);
		no = sp.load(this, R.raw.ohno, 1);
		ab1.setVisibility(View.INVISIBLE);
		ab2.setVisibility(View.INVISIBLE);
		ab3.setVisibility(View.INVISIBLE);
		ab4.setVisibility(View.INVISIBLE);
		button2.setVisibility(View.INVISIBLE);
		textView1.setVisibility(View.INVISIBLE);
		textView3.setVisibility(View.INVISIBLE);
		textView4.setVisibility(View.INVISIBLE);
		button1.setVisibility(View.INVISIBLE);
		textView2.setText("请选择难度：");
		
		list.add("5");
		list.add("6");
		list.add("7");
		list.add("8");
		list.add("9");
		list.add("10");
		myTextView = (TextView) findViewById(R.id.textView1);
		
		sb1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				nandu=1;
				textView1.setVisibility(View.VISIBLE);
				textView2.setText("");
				textView3.setVisibility(View.VISIBLE);
				textView4.setVisibility(View.VISIBLE);
				button1.setVisibility(View.VISIBLE);
				sb1.setVisibility(View.INVISIBLE);
				sb2.setVisibility(View.INVISIBLE);
				sb3.setVisibility(View.INVISIBLE);
				Spinner=1;
				sp();
				

			}
		});
		sb2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				nandu=2;
				textView1.setVisibility(View.VISIBLE);
				textView2.setText("");
				textView3.setVisibility(View.VISIBLE);
				textView4.setVisibility(View.VISIBLE);
				button1.setVisibility(View.VISIBLE);
				sb1.setVisibility(View.INVISIBLE);
				sb2.setVisibility(View.INVISIBLE);
				sb3.setVisibility(View.INVISIBLE);
				Spinner=1;
				sp();
				

			}
		});
		sb3.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				nandu=3;
				textView1.setVisibility(View.VISIBLE);
				textView2.setText("");
				textView3.setVisibility(View.VISIBLE);
				textView4.setVisibility(View.VISIBLE);
				button1.setVisibility(View.VISIBLE);
				sb1.setVisibility(View.INVISIBLE);
				sb2.setVisibility(View.INVISIBLE);
				sb3.setVisibility(View.INVISIBLE);
				Spinner=1;
				sp();
				

			}
		});
		button1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				showtext();
				button1.setVisibility(View.INVISIBLE);
				ab1.setVisibility(View.VISIBLE);
				ab2.setVisibility(View.VISIBLE);
				ab3.setVisibility(View.VISIBLE);
				ab4.setVisibility(View.VISIBLE);
				sp.play(ready, 1, 1, 0, 0, 1);
				Spinner=0;
				sp();
				

			}
		});
		button2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				AlertDialog.Builder builder=new Builder(MainActivity.this);
				builder.setMessage("此功能还没有实现，是否重新答题？");
				builder.setTitle("提示");
				builder.setPositiveButton("是", new android.content.DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						dialog.dismiss();
						button1.setVisibility(View.VISIBLE);
						count=0;
						TrueNum=0;
						textView4.setText("");
						textView2.setText("");
						ab1.setVisibility(View.INVISIBLE);
						ab2.setVisibility(View.INVISIBLE);
						ab3.setVisibility(View.INVISIBLE);
						ab4.setVisibility(View.INVISIBLE);
						button2.setVisibility(View.INVISIBLE);
						textView1.setVisibility(View.INVISIBLE);
						textView2.setText("请选择难度：");
						textView3.setVisibility(View.INVISIBLE);
						textView4.setVisibility(View.INVISIBLE);
						button1.setVisibility(View.INVISIBLE);
						sb1.setVisibility(View.VISIBLE);
						sb2.setVisibility(View.VISIBLE);
						sb3.setVisibility(View.VISIBLE);
						
						
					}
				});
				builder.setNegativeButton("否", new android.content.DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						dialog.dismiss();
						android.os.Process.killProcess(android.os.Process.myPid());
					}
				});
				builder.create().show();
				
			}
		});

	}

	public void onaButtonClicked(View view) {
		switch (view.getId()) {
		case R.id.ab1: {

			UserAnswer = Integer.parseInt(ab1.getText().toString().trim());
			if (UserAnswer == TrueAnswer) {
				TrueNum++;
				textView4.setTextColor(Color.BLUE);
				textView4.setText("答对了！");
				sp.play(yes, 1, 1, 0, 0, 1);
			} else {
				
				textView4.setTextColor(Color.RED);
				textView4.setText("答错了！正确答案是："+TrueAnswer);
				
				sp.play(no, 1, 1, 0, 0, 1);
				
			}
			
			showtext();
			
			break;
		}

		case R.id.ab2: {

			UserAnswer = Integer.parseInt(ab2.getText().toString().trim());
			if (UserAnswer == TrueAnswer) {
				TrueNum++;
				textView4.setTextColor(Color.BLUE);
				textView4.setText("答对了！");
				
				sp.play(yes, 1, 1, 0, 0, 1);
			} else {
				
				textView4.setTextColor(Color.RED);
				textView4.setText("答错了！正确答案是："+TrueAnswer);
				
				sp.play(no, 1, 1, 0, 0, 1);
				
					
				
			}
			
			showtext();
			
			break;
		}
		case R.id.ab3: {

			UserAnswer = Integer.parseInt(ab3.getText().toString().trim());
			if (UserAnswer == TrueAnswer) {
				TrueNum++;
				textView4.setTextColor(Color.BLUE);
				textView4.setText("答对了！");
				sp.play(yes, 1, 1, 0, 0, 1);

			} else {
				
				textView4.setTextColor(Color.RED);
				textView4.setText("答错了！正确答案是："+TrueAnswer);
				
					
				sp.play(no, 1, 1, 0, 0, 1);
				
			}
			
			showtext();
			
			break;
		}
		case R.id.ab4: {

			UserAnswer = Integer.parseInt(ab4.getText().toString().trim());
			if (UserAnswer == TrueAnswer) {
				TrueNum++;
				textView4.setTextColor(Color.BLUE);
				textView4.setText("答对了！");
				sp.play(yes, 1, 1, 0, 0, 1);
			} else {
				
				textView4.setTextColor(Color.RED);
				textView4.setText("答错了！正确答案是："+TrueAnswer);
				
				sp.play(no, 1, 1, 0, 0, 1);
				
			}
			
			showtext();
			break;
		}
		}
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
	/*
	 * 退出控件
	*/
	@Override
	public boolean onKeyDown(int keyCode,KeyEvent event)
	{
		if(keyCode==KeyEvent.KEYCODE_BACK&& event.getRepeatCount()==0)
		{
			dialog();
			return true;
		}
		return true;
	}
	protected void dialog()
	{
		AlertDialog.Builder builder=new Builder(MainActivity.this);
		builder.setMessage("确定要退出吗？");
		builder.setTitle("提示");
		builder.setPositiveButton("确认", new android.content.DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				dialog.dismiss();
				android.os.Process.killProcess(android.os.Process.myPid());
			}
		});
		builder.setNegativeButton("取消", new android.content.DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				dialog.dismiss();
			}
		});
		builder.create().show();
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.activity_main, container,
					false);
			return rootView;

		}
	}

	void showtext() {
		int c = 0, e = 0, f = 0, opnum = 0, chioce = 0;
		int temp = 0;
		if (count == TiShu && TrueNum==TiShu) {
			new AlertDialog.Builder(this).setTitle("结果:").setMessage("恭喜你的答案全对了，继续努力哦！")
			.setPositiveButton("确定", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					button1.setVisibility(View.VISIBLE);
					count=0;
					TrueNum=0;
					textView4.setText("");
					textView2.setText("");
					ab1.setVisibility(View.INVISIBLE);
					ab2.setVisibility(View.INVISIBLE);
					ab3.setVisibility(View.INVISIBLE);
					ab4.setVisibility(View.INVISIBLE);
					button2.setVisibility(View.INVISIBLE);
					textView1.setVisibility(View.INVISIBLE);
					textView2.setText("请选择难度：");
					textView3.setVisibility(View.INVISIBLE);
					textView4.setVisibility(View.INVISIBLE);
					button1.setVisibility(View.INVISIBLE);
					sb1.setVisibility(View.VISIBLE);
					sb2.setVisibility(View.VISIBLE);
					sb3.setVisibility(View.VISIBLE);
				}})
				.show();
		}
		else if(count == TiShu && TrueNum!=TiShu)
		{
			new AlertDialog.Builder(this).setTitle("结果").setMessage("你在"+TiShu+"题中答对了"+TrueNum+"题！请重新答题！")
			.setPositiveButton("确定", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				button1.setVisibility(View.VISIBLE);
				count=0;
				TrueNum=0;
				textView4.setText("");
				textView2.setText("");
				ab1.setVisibility(View.INVISIBLE);
				ab2.setVisibility(View.INVISIBLE);
				ab3.setVisibility(View.INVISIBLE);
				ab4.setVisibility(View.INVISIBLE);
				button2.setVisibility(View.INVISIBLE);
				textView1.setVisibility(View.INVISIBLE);
				textView2.setText("请选择难度：");
				textView3.setVisibility(View.INVISIBLE);
				textView4.setVisibility(View.INVISIBLE);
				button1.setVisibility(View.INVISIBLE);
				sb1.setVisibility(View.VISIBLE);
				sb2.setVisibility(View.VISIBLE);
				sb3.setVisibility(View.VISIBLE);
			}})
			.show();


			
		}
		else {
			Random ra = new Random();
			switch(nandu)
			{
			case 1:
			{
				a = ra.nextInt(10) + 1;
				b = ra.nextInt(10) + 1;
				break;
			}
			case 2:
			{
				a = ra.nextInt(20) + 20;
				b = ra.nextInt(20) + 20;
				break;
			}
			case 3:
			{
				a = ra.nextInt(50) + 50;
				b = ra.nextInt(50) + 50;
				break;
			}
			}
			
			opnum = Math.abs(ra.nextInt()) % 4 + 1;
			if (a < b) {
				temp = a;
				a = b;
				b = temp;
			}
			switch (opnum) {
			case 1:
				op = '+';
				break;
			case 2:
				op = '-';
				break;
			case 3:
				op = '×';
				break;
			case 4:
				op = '÷';
				break;
			}

			if ((op == '-') && (a < b)) {
				temp = a;
				a = b;
				b = temp;
			}

			if (op == '÷') {
				if (b == 0) {
					b = 1;
				}
				a = a * b;
			}
			switch (op) {
			case '+':
				TrueAnswer = a + b;
				break;
			case '-':
				TrueAnswer = a - b;
				break;
			case '×':
				TrueAnswer = a * b;
				break;
			case '÷':
				TrueAnswer = a / b;
				break;
			}
			
			textView2.setText(a + " " + op + " " + b + " = ?");

			c = ra.nextInt(12) + 1;
			switch (c) {
			case 1: {
				ab1.setText(TrueAnswer + "");
				ab2.setText(TrueAnswer - 1 + "");
				ab3.setText(TrueAnswer + 1 + "");
				ab4.setText(TrueAnswer + 3 + "");

				break;
			}
			case 2: {
				ab1.setText(TrueAnswer + 1 + "");
				ab2.setText(TrueAnswer - 3 + "");
				ab3.setText(TrueAnswer + 2 + "");
				ab4.setText(TrueAnswer + "");
				break;
			}
			case 3: {
				ab1.setText(TrueAnswer + 3 + "");
				ab2.setText(TrueAnswer - 2 + "");
				ab3.setText(TrueAnswer + "");
				ab4.setText(TrueAnswer + 1 + "");
				break;
			}
			case 4: {
				ab1.setText(TrueAnswer + "");
				ab2.setText(TrueAnswer - 3 + "");
				ab3.setText(TrueAnswer + 1 + "");
				ab4.setText(TrueAnswer - 1 + "");
				break;
			}
			case 5: {
				ab1.setText(TrueAnswer + 5 + "");
				ab2.setText(TrueAnswer + "");
				ab3.setText(TrueAnswer + 3 + "");
				ab4.setText(TrueAnswer - 2 + "");
				break;
			}
			case 6:
				ab1.setText(TrueAnswer + "");
				ab2.setText(TrueAnswer - 4 + "");
				ab3.setText(TrueAnswer + 2 + "");
				ab4.setText(TrueAnswer - 2 + "");
				break;
			case 7:
				ab1.setText(TrueAnswer + 4 + "");
				ab2.setText(TrueAnswer + "");
				ab3.setText(TrueAnswer + 2 + "");
				ab4.setText(TrueAnswer - 2 + "");
				break;
			case 8:
				ab1.setText(TrueAnswer + 3 + "");
				ab2.setText(TrueAnswer - 2 + "");
				ab3.setText(TrueAnswer + "");
				ab4.setText(TrueAnswer - 5 + "");
				break;
			case 9:
				ab1.setText(TrueAnswer + 2+ "");
				ab2.setText(TrueAnswer - 1 + "");
				ab3.setText(TrueAnswer + 3 + "");
				ab4.setText(TrueAnswer + "");
				break;
			case 10:
				ab1.setText(TrueAnswer + "");
				ab2.setText(TrueAnswer - 4 + "");
				ab3.setText(TrueAnswer + 2 + "");
				ab4.setText(TrueAnswer - 3 + "");
				break;
			case 11:
				ab1.setText(TrueAnswer + 6+ "");
				ab2.setText(TrueAnswer + "");
				ab3.setText(TrueAnswer + 4 + "");
				ab4.setText(TrueAnswer - 2 + "");
				break;
			case 12:
				ab1.setText(TrueAnswer + 5+ "");
				ab2.setText(TrueAnswer - 3 + "");
				ab3.setText(TrueAnswer + "");
				ab4.setText(TrueAnswer - 2 + "");
				break;
			}
			count++;
		}

	}
	void sp()
	{
		mySpinner = (Spinner) findViewById(R.id.spinner1);
		adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, list);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		mySpinner.setAdapter(adapter);
		mySpinner.setSelection(2);
		mySpinner.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
					public void onItemSelected(AdapterView<?> arg0, View arg1,
							int arg2, long arg3) {
						// TODO Auto-generated method stub
						
						/* 将mySpinner 显示 */
						if(Spinner==1)
						{
							TiShu = Integer.parseInt(adapter.getItem(arg2)
									.toString().trim());
						arg0.setVisibility(View.VISIBLE);
						}
						else {
							textView3.setVisibility(View.INVISIBLE);
							arg0.setVisibility(View.INVISIBLE);
						}
					}

					public void onNothingSelected(AdapterView<?> arg0) {
						// TODO Auto-generated method stub
						myTextView.setText("NONE");
						arg0.setVisibility(View.VISIBLE);
					}
				});
	}

}
