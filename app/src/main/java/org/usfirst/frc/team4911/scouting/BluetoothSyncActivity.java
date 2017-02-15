package org.usfirst.frc.team4911.scouting;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.usfirst.frc.team4911.scouting.bluetoothfilepusher.*;

import java.util.Date;

public class BluetoothSyncActivity extends AppCompatActivity {
    private static final int REQUEST_ENABLE_BT = 1;
    private static final String TAG = "CyberKnightBluePusher";
    private static final String DEFAULT_TARGET_DEVICE_NAME_PREFERENCE = "DEFAULT_TARGET_DEVICE_NAME";

    private TextView textView_log;
    private TextView textView_targetDevice;
    private BluetoothAdapter mBluetoothAdapter;
    private BluetoothPusherService filePusher;
    private BluetoothDevice mBluetoothDevice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth_sync);

        this.textView_log = (TextView) findViewById(R.id.textView_Log);
        this.textView_targetDevice = (TextView) findViewById(R.id.textView_TargetDevice);

        // get the bluetooth adapter so that we can use it
        this.mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (!mBluetoothAdapter.isEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
        }

        // create our new file pusher with a really basic handler that
        // just logs all the messages it receives back
        this.filePusher = new BluetoothPusherService(new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case BluetoothPusherService.StatusMessageTypes.SEND_SUCCESS:
                        log("Succeeded: " + msg.getData().getString("file"));
                        break;
                    case BluetoothPusherService.StatusMessageTypes.SEND_FAILED:
                        log("Failed: " + msg.getData().getString("file"));
                        break;
                }

            }
        });

        String targetDeviceName = getPreferences(MODE_PRIVATE).getString(DEFAULT_TARGET_DEVICE_NAME_PREFERENCE, "");
        if (!targetDeviceName.isEmpty()) {
            new BluetoothDeviceManager(getApplicationContext()).findDevice(targetDeviceName, new DevicePickedHandler());
        }
    }


    private class DevicePickedHandler implements BluetoothDeviceManager.BluetoothDevicePickResultHandler {
        @Override
        public void onDevicePicked(BluetoothDevice device) {
            getPreferences(MODE_PRIVATE).edit().putString(DEFAULT_TARGET_DEVICE_NAME_PREFERENCE, device.getName()).apply();

            log("Device Picked: " + device.getName() + " - " + device.getAddress());
            textView_targetDevice.setText(device.getName());
            BluetoothSyncActivity.this.mBluetoothDevice = device;
        }

        @Override
        public void onNoDevicePicked() {
            log("Failed to connect to device.");
        }
    }

    protected void selectDeviceButton_Clicked(View v) {
        // Use the device picker dialog to have the user select the device
        // to send the files too.
        new BluetoothDeviceManager(this).pickDevice(new DevicePickedHandler());
    }

    protected void sendButton_Clicked(View v) {
        Toast.makeText(getApplicationContext(), "Send button clicked", Toast.LENGTH_SHORT).show();
        //ConnectedDevice connection = filePusher.connect(mBluetoothDevice);
        //// TODO: Make this more elegant ffs
        //connection.send(org.usfirst.frc.team4911.scouting.matchscouting.FinishMatchFragment.getScoutingDataStorageDir());
    }

    private void log(String line) {
        Log.d(TAG, "" + line);
        textView_log.setText(new Date() + ": " + line + "\n" + textView_log.getText());
    }
}