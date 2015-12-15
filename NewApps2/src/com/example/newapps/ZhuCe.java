package com.example.newapps;


import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.newapps.UserDbAdapter;

public class ZhuCe extends Activity {
	private Button bt1,bt2;
	private EditText ed1,ed2,ed3;
	private UserDbAdapter mDbHelper;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 锟斤拷title
		setContentView(R.layout.zhuce_layout);
		bt1=(Button)findViewById(R.id.button1);
		bt2=(Button)findViewById(R.id.button2);
		ed1=(EditText)findViewById(R.id.editText1);
		ed2=(EditText)findViewById(R.id.editText2);
		ed3=(EditText)findViewById(R.id.editText3);
		mDbHelper = new UserDbAdapter(this);
		mDbHelper.open();
		ed2.setInputType(InputType.TYPE_CLASS_TEXT| InputType.TYPE_TEXT_VARIATION_PASSWORD);
		ed3.setInputType(InputType.TYPE_CLASS_TEXT	| InputType.TYPE_TEXT_VARIATION_PASSWORD);
		bt1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent4 = new Intent();
				intent4.setClass(ZhuCe.this, DengLu.class);
				startActivity(intent4);
				
			}
		});
		bt2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String username = ed1.getText().toString();
				String password = ed2.getText().toString();
				String conPassword = ed3.getText().toString();
				if((username == null||username.equalsIgnoreCase("")) || 
						(password == null||password.equalsIgnoreCase("")) 
						|| (conPassword == null||conPassword.equalsIgnoreCase(""))){
					Toast.makeText(ZhuCe.this, "用户名和密码是必填项",
							Toast.LENGTH_SHORT).show();
				}
				else{
					Cursor cursor=mDbHelper.getDiary(username);
					if(cursor.moveToFirst()){
						Toast.makeText(ZhuCe.this, "用户名已经存在",
								Toast.LENGTH_SHORT).show();
					}
					else if(!password.equals(conPassword)){
						Toast.makeText(ZhuCe.this, "两次密码输入不一致，请重新输入.",
								Toast.LENGTH_SHORT).show();
					}
					else{
						mDbHelper.createUser(username, password);
						Toast.makeText(ZhuCe.this, "注册成功，正转到登录界面，请稍后",
								Toast.LENGTH_SHORT).show();
						Intent intent3 = new Intent();
						intent3.setClass(ZhuCe.this, DengLu.class);
						startActivity(intent3);

					}
				}
			}
		});
		
	}

}