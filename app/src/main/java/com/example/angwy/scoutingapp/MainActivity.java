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
                File dataFileHandle = this.getDocumentStorageDir("ScoutingData");
                FileOutputStream outputStream = new FileOutputStream(dataFileHandle);
                outputStream.write(content.getBytes());
                outputStream.close();

                text = getString(R.string.data_written) + dataFileHandle.getPath();
            } catch (IOException e) {
                Log.e(LOG_TAG, e.toString());
                text = getString(R.string.unable_to_write_file);
            }
        }
        else
        {
            text = getString(R.string.unable_to_write_file);
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

    public File getDocumentStorageDir(String albumName) {
        // Get the directory for the user's public pictures directory.
        File file = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DOCUMENTS), albumName);
        if(!file.exists()) {
            if (!file.mkdirs()) {
                Log.e(LOG_TAG, "Directory not created");
            }
        }
        return file;
    }
}
