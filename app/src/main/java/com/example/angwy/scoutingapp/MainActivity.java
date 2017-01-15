package com.example.angwy.scoutingapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.actionbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_shooting:
                // User chose the shooting view so we show that.
                Intent intent = new Intent(this, ShootingActivity.class);
                startActivity(intent);

            case R.id.action_settings:
                // User chose the "Settings" item, show the app settings UI...
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

    /** Called when the user clicks the Send button */
    public void SaveDataToFile(View view) {
        Context context = getApplicationContext();
        CharSequence text;
        int duration = Toast.LENGTH_SHORT;

        if (this.isExternalStorageWritable())
        {
            String content = "hello world";

            try {
                File directory = this.getScoutingDataStorageDir();
                File dataFileHandle = new File(directory, "scoutingdata.txt");

                FileOutputStream outputStream = new FileOutputStream(dataFileHandle);
                outputStream.write(content.getBytes());
                outputStream.close();

                text = "File written: " + dataFileHandle.getPath();
            } catch (IOException e) {
                Log.e(LOG_TAG, e.toString());
                text = "unable to write file because of an exception: " + e.toString();
            }
        }
        else
        {
            text = "External storage not writeable";
        }

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    /* Checks if external storage is available for read and write */
    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equals(state);
    }

    /* Gets the directory where the scouting app will store its output files */
    public File getScoutingDataStorageDir() {
        File directoryPath = new File(Environment.getExternalStorageDirectory(),
                "ScoutingData");

        if (!directoryPath.exists()) {
            if (!directoryPath.mkdirs()) {
                Log.e(LOG_TAG, "Directory not created");
            }
        }

        return directoryPath;
    }
}
