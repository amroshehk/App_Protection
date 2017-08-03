package com.applefish.protectingapps;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ViewDataActivity extends AppCompatActivity {
    myDbAdapter helper;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_data);

        textView=(TextView)findViewById(R.id.tv1);
        helper = new myDbAdapter(this);
        String data = helper.getData();

        textView.setText(data);

    }
}
