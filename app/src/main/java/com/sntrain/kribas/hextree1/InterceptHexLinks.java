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

public class InterceptHexLinks extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_intercept_hex_links);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Log.v("InterceptHexLinks","Intercepted intent");
        TextView hexTextView = findViewById(R.id.hexTextView);
        TextView hexEditView = findViewById(R.id.hexEditText);
        Button hexEditSend = findViewById(R.id.hexEditSend);

        Intent intent = getIntent();
        if (intent == null){
            Log.v("InterceptHexLinks","Intent is null");
            hexTextView.setText("Intent is null");
        }else if (intent.getAction().equals("android.intent.action.VIEW")){
            Uri data = intent.getData();
            if (data == null){
                Log.v("InterceptHexLinks","Intent is not null, data is null");
                hexTextView.setText("Intent is not null, data is null");
            }else {
                Log.v("InterceptHexLinks","Intent is not null, data is not null");
                Log.v("InterceptHexLinks","Data: "+data.toString());
                hexTextView.setText(data.toString());
                hexEditView.setText(data.toString());
            }
        }

        hexEditSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v("InterceptHexLinks","Send button clicked");
                Intent editedIntent = new Intent();
                assert intent != null;
                editedIntent.setAction("android.intent.action.VIEW");//intent.getAction());
                editedIntent.addCategory("android.intent.category.BROWSABLE");
                editedIntent.putExtra("com.android.browser.application_id",intent.getStringExtra("com.android.browser.application_id"));
                String editedData = hexEditView.getText().toString();
                editedIntent.setData(Uri.parse(editedData));
                Log.v("InterceptHexLinks","Edited data:"+editedData);
                startActivity(editedIntent);
            }
        });
    }
}