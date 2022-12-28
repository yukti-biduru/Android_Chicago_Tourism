package com.example.chicago_tourism;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
// Activity for navigating to attractions and hotels in chicago
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button attractions_button = findViewById(R.id.button);
        Button hotels_button = findViewById(R.id.button2);

        attractions_button.setOnClickListener(attractions_listener);
        hotels_button.setOnClickListener(hotels_listener);
    }

    // listener for attractions button
    public View.OnClickListener attractions_listener = v -> {
        Toast.makeText(this,"You've selected Chicago Attractions!",Toast.LENGTH_SHORT).show();
        checkPermissionsAndBroadcast();

    };

    // listener for hotels button
    public View.OnClickListener hotels_listener = v -> {
        Toast.makeText(this,"You've selected Chicago Hotels!",Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.putExtra("display_required", "hotels");
        sendBroadcast(intent);
    };

    // checking the permission before broadcasting and asking for permission if not there
    public void checkPermissionsAndBroadcast()
    {
        if(ActivityCompat.checkSelfPermission(this,"com.example.chicago_tourism.mp3") == PackageManager.PERMISSION_GRANTED)
        {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.addCategory(Intent.CATEGORY_DEFAULT);
            intent.putExtra("display_required", "attractions");
            sendBroadcast(intent);
        }
        else
        {
            ActivityCompat.requestPermissions(this, new String[]{"com.example.chicago_tourism.mp3"},0);
        }
    }

//    checking result on requesting the permissions required
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(grantResults.length > 0)
        {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_DEFAULT);
                intent.putExtra("display_required", "attractions");
                sendBroadcast(intent);
            }
            else
            {
                Toast.makeText(this, "Required permission is not provided", Toast.LENGTH_SHORT).show();
            }
        }
    }
}