package com.ffn.grphapro.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ffn.grphapro.R;
import com.ffn.grphapro.mrsunpackage.mrsunview.mrsunutils.AppLogMessageMgr;
import com.ffn.grphapro.mrsunpackage.mrsunview.mrsunutils.MrsunAPpCleanUtils;
import com.ffn.grphapro.mrsunpackage.mrsunview.mrsunutils.ViewClickUtils;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button bar = (Button) findViewById(R.id.bar);
        Button yuan = (Button) findViewById(R.id.yuan);
        Button line = (Button) findViewById(R.id.line);
        ViewClickUtils.addTouchDrak(yuan,false);
        ViewClickUtils.addTouchLight(line,false);
        TextView textView = (TextView) findViewById(R.id.tv_size);
        if("0".equals(MrsunAPpCleanUtils.getAppClearSize(this)) || TextUtils.isEmpty(MrsunAPpCleanUtils.getAppClearSize(this))){
            textView.setText("该应用缓存大小为：0MB");
        }else{
            textView.setText("该应用缓存大小为："+MrsunAPpCleanUtils.getAppClearSize(this));
        }

        AppLogMessageMgr.e("MainActivity",MrsunAPpCleanUtils.getAppClearSize(this));
        bar.setOnClickListener(this);
        yuan.setOnClickListener(this);
        line.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bar:
                Intent intent = new Intent(MainActivity.this, BarActivity.class);
                startActivity(intent);
                break;
            case R.id.line:
                Intent intent2 = new Intent(MainActivity.this, LineActivity.class);
                startActivity(intent2);
                break;
            case R.id.yuan:
                Intent intent1=new Intent(MainActivity.this,YuanActivity.class);
                startActivity(intent1);
                break;
        }
    }
}
