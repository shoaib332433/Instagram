package com.example.instagram;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParseObject;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class MainActivity extends AppCompatActivity {

    EditText kickboxer,kickboxerspeed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ParseInstallation.getCurrentInstallation().saveInBackground();

        kickboxer=findViewById(R.id.kickboxer);
        kickboxerspeed=findViewById(R.id.kickboxerspeed);

    }

    public void clicksavedataincloud(View view) {

        try {

            ParseObject boxer = new ParseObject("Boxer");
            boxer.put("kickboxerspeed", Integer.parseInt(kickboxerspeed.getText().toString()));
            boxer.put("kickboxer", Integer.parseInt(kickboxer.getText().toString()));

            boxer.saveInBackground(new SaveCallback() {
                @Override
                public void done(ParseException e) {
                    if (e == null) {
                        FancyToast.makeText(MainActivity.this, "Sucessfully", FancyToast.LENGTH_SHORT, FancyToast.SUCCESS, true).show();
                    } else {
                        Toast.makeText(MainActivity.this, e.getMessage() + "", Toast.LENGTH_SHORT).show();
                    }

                }
            });
        } catch (Exception e) {
            FancyToast.makeText(MainActivity.this, e.getMessage(), FancyToast.LENGTH_SHORT, FancyToast.ERROR, true).show();
        }


    }
}
