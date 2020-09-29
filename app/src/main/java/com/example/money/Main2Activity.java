package com.example.money;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import static android.widget.Toast.LENGTH_SHORT;


public class Main2Activity extends AppCompatActivity {

    private static final String TAG = "Main2Activity";
    EditText dollarrate, eurorate, wonrate;
    EditText tv1;
    Handler handler;
    

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        new Thread(new myrun()).start();
        tv1=findViewById(R.id.tv1);
        //tv1.setText("123");

        handler=new Handler(){
            public void handleMessage(Message msg){
                if(msg.what==5){
                    String str=(String)msg.obj;
                    Log.i(TAG,"handleMessage:getMessage msg="+str);
                    tv1.setText(""+str);
                }

                super.handleMessage(msg);
            }
        };

        Message msg=handler.obtainMessage(5);
        //msg.what=5;
        msg.obj="Hello from run()";
        handler.sendMessage(msg);


        //设置初始汇率
        dollarrate=findViewById(R.id.dollartext);
        eurorate=findViewById(R.id.eurotext);
        wonrate=findViewById(R.id.wontext);

        Intent intent=getIntent();
        Float dollar2=intent.getFloatExtra("dollar_rate_key",0.0f);
        Float euro2=intent.getFloatExtra("euro_rate_key",0.0f);
        Float won2=intent.getFloatExtra("won_rate_key",0.0f);

        Log.i(TAG,"onCreate:dollar2="+dollar2);
        Log.i(TAG,"onCreate:euro2="+euro2);
        Log.i(TAG,"onCreate:won2="+won2);

        dollarrate.setText(""+dollar2);
        eurorate.setText(""+euro2);
        wonrate.setText(""+won2);

    }

    public void save(View btn){

        Intent intent=getIntent();
        String strDollar=dollarrate.getText().toString();
        String strEuro=eurorate.getText().toString();
        String strWon=wonrate.getText().toString();
        Float newDollar=Float.parseFloat(strDollar);
        Float newEuro=Float.parseFloat(strEuro);
        Float newWon=Float.parseFloat(strWon);

        Bundle bdl=new Bundle();
        bdl.putFloat("key_dollar",newDollar);
        bdl.putFloat("key_euro",newEuro);
        bdl.putFloat("key_won",newWon);
        intent.putExtras(bdl);
        setResult(2,intent);
        finish();



    }

}

class myrun implements Runnable{

    private final String TAG=getClass().getSimpleName();

    public void run(){


        Log.i(TAG,"run:123456789");
    }
}


