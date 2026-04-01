package com.sntrain.kribas.hextree1;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class InterceptHttpIntents extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_intercept_http_intents);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Log.v("InterceptHttpIntents","Intercepted intent");
        TextView httpTextView = findViewById(R.id.httpTextView);
        TextView httpEditView = findViewById(R.id.httpEditText);
        Button httpEditSend = findViewById(R.id.httpEditSend);

        Intent intent = getIntent();
        if (intent == null){
            Log.v("InterceptHttpIntents","Intent is null");
            httpTextView.setText("Intent is null");
        }else if (intent.getAction().equals("android.intent.action.VIEW")){
            Uri data = intent.getData();
            if (data == null){
                Log.v("InterceptHttpIntents","Intent is not null, data is null");
                httpTextView.setText("Intent is not null, data is null");
            }else {
                Log.v("InterceptHttpIntents","Intent is not null, data is not null");
                Log.v("InterceptHttpIntents","Data: "+data.toString());
                httpTextView.setText(data.toString());
                httpEditView.setText(data.toString());
            }
        }

        httpEditSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v("InterceptHttpIntents","Send button clicked");
                Intent editedIntent = new Intent();
                assert intent != null;
                editedIntent.setAction(intent.getAction());
                String editedData = httpEditView.getText().toString();
                editedIntent.setData(Uri.parse(editedData));
                Log.v("InterceptHttpIntents","Edited data:"+editedData);
                startActivity(editedIntent);
            }
        });
    }
}