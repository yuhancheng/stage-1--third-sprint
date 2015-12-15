package com.example.newapps;



import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class TianKong extends Activity {
	//private String expressing;
	private Spinner mySpinner;
	private int count = 0;
	private int count1;
	private int TrueNum = 0;
	private int a = 0, b = 0,c=0,d=0,opnum = 0,opnum1=0,opnum2=0;
	private int TiShu = 0,TrueAnswer = 0;
	private SoundPool sp;
	private char op1,op2,op3;
	private ArrayAdapter<String> adapter;
	private int Spinner=0;
	private TextView myTextView;
	private EditText ed1;
	private TextView textView3,textView2,textView1,textView4,textView5;
	private int ready, yes, no;
	private int op;
	private Button button1,bt1;
	String str;
	int i=0;

	private ProgressBar pb;
	private static final int next=0x002;
	private Handler handler= new Handler(){
		public void handleMessage(android.os.Message msg) {
		//进行ui更新
			switch (msg.what) {
			case next:
			pb.setProgress(count1);
			break;
			default:
				break;
			}
		};
	};
	
	private List<String> list = new ArrayList<String>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		// 无title
		 requestWindowFeature(Window.FEATURE_NO_TITLE);
		// 全屏

		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				 WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.tiankong);
		ed1=(EditText)findViewById(R.id.editText1);
		myTextView = (TextView) findViewById(R.id.textView1);
		textView3 = (TextView) findViewById(R.id.textView6);
		textView2 = (TextView) findViewById(R.id.textView3);
		textView4 = (TextView) this.findViewById(R.id.textView5);
		textView5 = (TextView) this.findViewById(R.id.textView4);
		textView1 = (TextView) this.findViewById(R.id.textView1);
		button1 = (Button) this.findViewById(R.id.button2);
		pb=(ProgressBar)this.findViewById(R.id.progressBar1);
		pb.setProgress(0);
		bt1 = (Button) this.findViewById(R.id.button1);
		sp = new SoundPool(10, AudioManager.STREAM_SYSTEM, 5);
		ready = sp.load(this, R.raw.readygo, 1);
		yes = sp.load(this, R.raw.yes, 1);
		no = sp.load(this, R.raw.ohno, 1);
		textView5.setVisibility(View.INVISIBLE);
		ed1.setVisibility(View.INVISIBLE);
		bt1.setVisibility(View.INVISIBLE);
		list.add("5");
		list.add("6");
		list.add("7");
		list.add("8");
		list.add("9");
		list.add("10");
		Spinner=1;
		sp();
		button1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				showtext();
				button1.setVisibility(View.INVISIBLE);
				textView5.setVisibility(View.VISIBLE);
				textView2.setVisibility(View.VISIBLE);
				ed1.setVisibility(View.VISIBLE);
				bt1.setVisibility(View.VISIBLE);
				sp.play(ready, 1, 1, 0, 0, 1);
				Spinner=0;
				sp();
			}
		});
		bt1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				 String str1,str2;
				 Double db1,db2=0.0;
				 pb.setMax(TiShu);
				 pb.setProgress(0);
				 i++;
				
				 new Thread(new Runnable() {
						
						@Override
						public void run() {
							
								// TODO Auto-generated method stub
							
							count1=i;//buchangwei wu
							System.out.println(count1);
							Message msg=new Message();								
							msg.what=next;
							handler.sendMessage(msg);
						}
					}).start();
				 textView4.setVisibility(View.VISIBLE);
				str=textView2.getText().toString();
				//Operating exp1 = new Operating(str);
				System.out.println(str);
				str1=String.valueOf(arithmetic(str));
				str2=ed1.getText().toString();
				
				db1=Double.parseDouble(str1);
				if(str2==null || str2.equalsIgnoreCase(""))
						{
					Toast.makeText(TianKong.this, "你没有输入答案，跳到下一题", 1).show();
						}
				else if(str2.matches("^[a-zA-Z]*"))
				{
					Toast.makeText(TianKong.this, "你输入的格式错误，跳到下一题", 1).show();
				}
				else
				{
				db2=Double.parseDouble(str2);
				}
				System.out.println(db1);
				System.out.println(db2);
				if (db1.equals(db2)) {
					TrueNum++;
					textView4.setTextColor(Color.BLUE);
					textView4.setText("答对了！");
					ed1.setText("");
					sp.play(yes, 1, 1, 0, 0, 1);
				} else {
					
					textView4.setTextColor(Color.RED);
					textView4.setText("答错了！正确答案是："+db1);	
					Toast.makeText(TianKong.this, "上一道题的题目为"+str, Toast.LENGTH_LONG).show();
					ed1.setText("");
					sp.play(no, 1, 1, 0, 0, 1);
				}
				
				showtext();
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
		AlertDialog.Builder builder=new Builder(TianKong.this);
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
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.tiankong, container,
					false);
			return rootView;

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
	void showtext() {
		int  e = 0, f = 0,  chioce = 0;
		int temp = 0;
		if (count == TiShu && TrueNum==TiShu) {
			new AlertDialog.Builder(this).setTitle("结果:").setMessage("恭喜你的答案全对了，继续努力哦！")
			.setPositiveButton("确定", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					button1.setVisibility(View.VISIBLE);
					count=0;
					TrueNum=0;
					textView4.setText("");
					
					textView1.setVisibility(View.VISIBLE);
					textView3.setVisibility(View.VISIBLE);
					textView4.setVisibility(View.INVISIBLE);
					textView2.setVisibility(View.INVISIBLE);
					button1.setVisibility(View.VISIBLE);
					textView5.setVisibility(View.INVISIBLE);
					ed1.setVisibility(View.INVISIBLE);
					bt1.setVisibility(View.INVISIBLE);
					Spinner=1;
					sp();
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
				
				textView1.setVisibility(View.VISIBLE);
				textView3.setVisibility(View.VISIBLE);
				textView4.setVisibility(View.INVISIBLE);
				textView2.setVisibility(View.INVISIBLE);
				button1.setVisibility(View.VISIBLE);
				textView5.setVisibility(View.INVISIBLE);
				ed1.setVisibility(View.INVISIBLE);
				bt1.setVisibility(View.INVISIBLE);
				Spinner=1;
				sp();
			}})
			.show();


			
		}
		else {
			Random ra = new Random();
			
				a = ra.nextInt(20) + 20;
				b = ra.nextInt(20) + 20;
				c = ra.nextInt(20) + 20;
				d = ra.nextInt(20) + 20;
			
			op = Math.abs(ra.nextInt()) % 5 + 1;
			opnum = Math.abs(ra.nextInt()) % 4 + 1;
			opnum1 = Math.abs(ra.nextInt()) % 4 + 1;
			opnum2 = Math.abs(ra.nextInt()) % 4 + 1;
			switch (opnum) {
			case 1:
				op1 = '+';
				break;
			case 2:
				op1 = '-';
				break;
			case 3:
				op1 = '*';
				break;
			case 4:
				op1 = '/';
				break;
			}
			switch (opnum1) {
			case 1:
				op2 = '+';
				break;
			case 2:
				op2 = '-';
				break;
			case 3:
				op2 = '*';
				break;
			case 4:
				op2 = '/';
				break;
			}
			switch (opnum2) {
			case 1:
				op3 = '+';
				break;
			case 2:
				op3 = '-';
				break;
			case 3:
				op3 = '*';
				break;
			case 4:
				op3 = '/';
				break;
			}

			

			
			switch (op) {
			case 1:
				textView2.setText("("+a+""+op1+""+b+")"+op2+""+c+""+op3+""+d);
				break;
			case 2:
				textView2.setText("("+a+""+op1+""+b+""+op2+""+c+")"+op3+""+d);
				break;
			case 3:
				textView2.setText(a+""+op1+"("+b+""+op2+""+c+")"+op3+""+d);
				break;
			case 4:
				textView2.setText(a+""+op1+"("+b+""+op2+""+c+""+op3+""+d+")");
				break;
			case 5:
				textView2.setText(a+""+op1+""+b+""+op2+"("+c+""+op3+""+d+")");
				break;
			}
			
			

			
			count++;
		}
	}
	public static String parseExp(String expression){ 
        //String numberReg="^((?!0)\\d+(\\.\\d+(?<!0))?)|(0\\.\\d+(?<!0))$"; 
        expression=expression.replaceAll("\\s+", "").replaceAll("^\\((.+)\\)$", "$1"); 
        String checkExp="\\d";
        Double db11;
        String minExp="^((\\d+(\\.\\d+)?)|(\\[\\-\\d+(\\.\\d+)?\\]))[\\+\\-\\*\\/]((\\d+(\\.\\d+)?)|(\\[\\-\\d+(\\.\\d+)?\\]))$"; 
        //最小表达式计算 
        if(expression.matches(minExp)){ 
            String result=calculate(expression); 
            System.out.println("+"+result);
            return Double.parseDouble(result)>=0?result:"["+result+"]"; 
        } 
        //计算不带括号的四则运算 
        String noParentheses="^[^\\(\\)]+$"; 
        String priorOperatorExp="(((\\d+(\\.\\d+)?)|(\\[\\-\\d+(\\.\\d+)?\\]))[\\*\\/]((\\d+(\\.\\d+)?)|(\\[\\-\\d+(\\.\\d+)?\\])))"; 
        String operatorExp="(((\\d+(\\.\\d+)?)|(\\[\\-\\d+(\\.\\d+)?\\]))[\\+\\-]((\\d+(\\.\\d+)?)|(\\[\\-\\d+(\\.\\d+)?\\])))"; 
        if(expression.matches(noParentheses)){ 
            Pattern patt=Pattern.compile(priorOperatorExp); 
            Matcher mat=patt.matcher(expression); 
            if(mat.find()){ 
                String tempMinExp=mat.group(); 
                expression=expression.replaceFirst(priorOperatorExp, parseExp(tempMinExp)); 
            }else{ 
                patt=Pattern.compile(operatorExp); 
                mat=patt.matcher(expression); 
                 
                if(mat.find()){ 
                    String tempMinExp=mat.group(); 
                    expression=expression.replaceFirst(operatorExp, parseExp(tempMinExp)); 
                } 
            } 
            return parseExp(expression); 
        } 
        //计算带括号的四则运算 
        String minParentheses="\\([^\\(\\)]+\\)"; 
        Pattern patt=Pattern.compile(minParentheses); 
        Matcher mat=patt.matcher(expression); 
        if(mat.find()){ 
            String tempMinExp=mat.group(); 
            expression=expression.replaceFirst(minParentheses, parseExp(tempMinExp)); 
        } 
        return parseExp(expression); 
    }

    public static double arithmetic(String exp){ 
        String result1 = parseExp(exp).replaceAll("[\\[\\]]", ""); 
        Double db11=Double.parseDouble(result1);
        DecimalFormat df=new DecimalFormat("######0.00"); 
        String result=String.valueOf(df.format(db11));
        return Double.parseDouble(result); 
    } 
    
    
	public static String calculate(String exp){ 
        exp=exp.replaceAll("[\\[\\]]", ""); 
        String number[]=exp.replaceFirst("(\\d)[\\+\\-\\*\\/]", "$1,").split(","); 
        BigDecimal number1=new BigDecimal(number[0]); 
        BigDecimal number2=new BigDecimal(number[1]); 
        BigDecimal number3=new BigDecimal("0");
        BigDecimal result=null; 
         
        String operator=exp.replaceFirst("^.*\\d([\\+\\-\\*\\/]).+$", "$1"); 
        if("+".equals(operator)){ 
            result=number1.add(number2); 
        }else if("-".equals(operator)){ 
            result=number1.subtract(number2); 
        }else if("*".equals(operator)){ 
            result=number1.multiply(number2); 
        }else if("/".equals(operator)){ 
        	try
        	{
            result=number1.divide(number2,5,BigDecimal.ROUND_HALF_UP); 
        	}catch(ArithmeticException e)
        	{
        		result=number3;
        	}
            
        } 
         
        return result!=null?result.toString():null; 
    }
	
}
