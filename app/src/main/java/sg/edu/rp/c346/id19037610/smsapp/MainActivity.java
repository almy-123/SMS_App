package sg.edu.rp.c346.id19037610.smsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText etTo, etContent;
    Button send;
    private BroadcastReceiver mr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkPermission();

        etTo = findViewById(R.id.etTo);
        etContent = findViewById(R.id.etContent);
        send = findViewById(R.id.btnSend);
        mr = new MessageReceiver();



        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SmsManager smsManager = SmsManager.getDefault();

                String to = etTo.getText().toString();
                String msg = etContent.getText().toString();

                smsManager.sendTextMessage(to, null, msg, null, null);
                Toast.makeText(MainActivity.this, "Message Sent", Toast.LENGTH_LONG).show();
                etContent.setText("");
            }
        });
    }

    private void checkPermission(){
        int permissionSendSMS = ContextCompat.checkSelfPermission(this,
                Manifest.permission.SEND_SMS);
        int permissionRecvSMS = ContextCompat.checkSelfPermission(this,
                Manifest.permission.RECEIVE_SMS);
        if(permissionSendSMS!= PackageManager.PERMISSION_GRANTED &&
        permissionRecvSMS!=PackageManager.PERMISSION_GRANTED){
            String[] permissionNeeded = new String[]{Manifest.permission.SEND_SMS,
            Manifest.permission.RECEIVE_SMS};
            ActivityCompat.requestPermissions(this, permissionNeeded, 1);
        }
    }
}
