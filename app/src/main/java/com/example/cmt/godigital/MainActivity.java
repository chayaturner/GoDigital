package com.example.cmt.godigital;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    protected Button aboutUsBtn;
    protected Button surveyBtn;
    protected Toolbar toolbar;
    private AlertDialog alertDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        aboutUsView();
        surveyView();

        //internet connection error alert
        alertDialog = new AlertDialog.Builder(MainActivity.this).create();
        alertDialog.setTitle("Uh Oh!");
        alertDialog.setMessage("No Internet Connection");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

    }

    //method to check if internet connection is available and returns true or false
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public void aboutUsView(){
        aboutUsBtn = (Button) findViewById(R.id.aboutUsBtn);
        aboutUsBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this, AboutUsActivity.class);

                //show alert if no internet, else continue to website
                if(!isNetworkAvailable()) {
                    alertDialog.show();
                } else {
                    startActivity(intent);
                }
            }
        });
    }


    public void surveyView(){
        surveyBtn = (Button)findViewById(R.id.surveyBtn);
        surveyBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this, SurveyActivity.class);
                if(!isNetworkAvailable()){
                    alertDialog.show();
                } else {
                    startActivity(intent);
                }
            }
        });
    }



}
