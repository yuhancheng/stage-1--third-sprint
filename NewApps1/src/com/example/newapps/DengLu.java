package com.example.newapps;


import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import com.example.newapps.UserDbAdapter;

public class DengLu extends Activity {
	private Button bt1,bt2;
	private EditText ed1;
	private AutoCompleteTextView at1;
	private CheckBox cb;
	private SharedPreferences sp;
	private UserDbAdapter mDbHelper;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.denglu_layout);
		bt1=(Button)findViewById(R.id.button1);
		bt2=(Button)findViewById(R.id.button2);
		ed1=(EditText)findViewById(R.id.editText1);
		at1=(AutoCompleteTextView)findViewById(R.id.autoCompleteTextView1);
		cb=(CheckBox)findViewById(R.id.checkBox1);
		cb.setChecked(true);
		at1.setThreshold(1);
		ed1.setInputType(InputType.TYPE_CLASS_TEXT| InputType.TYPE_TEXT_VARIATION_PASSWORD);
		sp = this.getSharedPreferences("passwordFile", MODE_PRIVATE);
		mDbHelper = new UserDbAdapter(this);
		mDbHelper.open();
		at1.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,int count) {
				String[] allUserName = new String[sp.getAll().size()];
				allUserName = sp.getAll().keySet().toArray(new String[0]);
				
				ArrayAdapter<String> adapter = new ArrayAdapter<String>(
						DengLu.this,
						android.R.layout.simple_dropdown_item_1line,
						allUserName);
				at1.setAdapter(adapter);
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable s) {
				ed1.setText(sp.getString(at1.getText().toString(), ""));// 

			}
		});
		bt1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent2 = new Intent(DengLu.this, ZhuCe.class);
				finish();
				startActivity(intent2);
			}
		});
		bt2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				String nameString=at1.getText().toString();
				String passwordString=ed1.getText().toString();
				if((nameString==null || nameString.equalsIgnoreCase(""))  || (passwordString==null || passwordString.equalsIgnoreCase("")))
				{
					Toast.makeText(DengLu.this, "用户名和密码是必填项", 1).show();
				}
				else{
					Cursor cursor=mDbHelper.getDiary(nameString);
					if(!cursor.moveToFirst())
					{
						Toast.makeText(DengLu.this, "该用户名不存在", 1).show();	
					}
					else if(!passwordString.equals(cursor.getString(2))){
						Toast.makeText(DengLu.this, "密码错误，请重新输入", 1).show();
					}
					else{
						if(cb.isChecked()){
							sp.edit().putString(nameString, passwordString).commit();
						}
						Toast.makeText(DengLu.this, "正在登录，请稍后...",
								Toast.LENGTH_SHORT).show();
		                Intent intent=new Intent(DengLu.this,MainExplain.class);
		                intent.putExtra("username", nameString);
		                finish();
		                startActivity(intent);
		                
					}
				}
			}
		});
		
	}

}
