package com.example.angwy.scoutingapp;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /** Called when the user clicks the Send button */
    public void SaveDataToFile(View view) {
        Context context = getApplicationContext();
        CharSequence text = "null";
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
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    public File getScoutingDataStorageDir() {
        File directoryPath = new File(getApplicationContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES),
                "ScoutingData");

        if (!directoryPath.mkdirs()) {
            Log.e(LOG_TAG, "Directory not created");
        }
        return directoryPath;
    }
}
