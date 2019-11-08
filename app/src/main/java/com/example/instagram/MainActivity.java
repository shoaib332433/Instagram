package com.example.instagram;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText kickboxer,kickboxerspeed;
    TextView txt;
    Button getalldata;
    String getalldatar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ParseInstallation.getCurrentInstallation().saveInBackground();

        kickboxer=findViewById(R.id.kickboxer);
        kickboxerspeed=findViewById(R.id.kickboxerspeed);

        txt=findViewById(R.id.datafromserver);

        txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    final ParseQuery<ParseObject> obj=new ParseQuery<ParseObject>("Boxer");
                    obj.getInBackground("0fwTH5Hb88", new GetCallback<ParseObject>() {
                        @Override
                        public void done(ParseObject object, ParseException e) {

                            if (object != null && e==null){
                                txt.setText("Kickboxerspeed: "+object.get("kickboxerspeed"));
                            }
                        }
                    });


                }catch (Exception e){
                    FancyToast.makeText(MainActivity.this,e.getMessage().toString(),Toast.LENGTH_SHORT,FancyToast.ERROR,true).show();
                }


            }
        });


        getalldata=findViewById(R.id.getalldata);

        getalldata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getalldatar="";

                ParseQuery<ParseObject> obj=ParseQuery.getQuery("Boxer");
                obj.findInBackground(new FindCallback<ParseObject>() {
                    @Override
                    public void done(List<ParseObject> objects, ParseException e) {
                        if (e==null){
                            if (objects.size()>0){
                                for(ParseObject kickdata : objects){
                                    getalldatar=getalldatar+kickdata.get("kickboxerspeed")+"\n";
                                }
                                FancyToast.makeText(MainActivity.this,getalldatar+"",FancyToast.LENGTH_SHORT,FancyToast.SUCCESS,true).show();
                            }
                        }
                    }
                });
            }
        });
    }

    public void clicksavedataincloud(View view) {

        try {

            ParseObject boxer = new ParseObject("Boxer");
            boxer.put("kickboxerspeed", Integer.parseInt(kickboxerspeed.getText().toString()));
            boxer.put("kickboxer", Integer.parseInt(kickboxer.getText().toString()));

            boxer.saveInBackground(new SaveCallback() {
                @Override
                public void done(ParseException e) {
                    if (e==null){
                        FancyToast.makeText(MainActivity.this,"Sucessfully",Toast.LENGTH_SHORT,FancyToast.SUCCESS,true).show();
                    }else{
                        FancyToast.makeText(MainActivity.this,"Error",Toast.LENGTH_SHORT,FancyToast.ERROR,true).show();
                    }
                }
            });

        }catch (Exception e){
            FancyToast.makeText(this,e.getMessage().toString(),Toast.LENGTH_SHORT,FancyToast.ERROR,true).show();
        }


    }
}
