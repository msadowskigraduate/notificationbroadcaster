package com.example.sadowsm3.mybroadcastapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    public static final String INTENT_ACTION_NAME = "com.example.sadowsm3.MY_ACTION";
    public static final String NOTIFICATION_MEASSAGE_KEY = "notificationMessage";
    private static final String TAG = "NotificationSenderActivity";
    private EditText editText;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.editText);
        button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String message = String.valueOf(editText.getText());
                Intent notificationIntent = new Intent();
                notificationIntent.setAction(INTENT_ACTION_NAME);
                notificationIntent.putExtra(NOTIFICATION_MEASSAGE_KEY, message);
                notificationIntent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
                Toast.makeText(getApplicationContext(), "message sent: " +
                        editText.getText(), Toast.LENGTH_LONG).show();
                Log.d(TAG, "message sent: " + editText.getText());
                sendBroadcast(notificationIntent);
            }
        });
    }
}