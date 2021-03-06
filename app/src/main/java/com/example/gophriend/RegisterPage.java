
package com.example.gophriend;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;

//Source code: https://github.com/mitchtabian/DatePickerDialog
//Source code: https://www.youtube.com/watch?v=on_OrrX7Nw4

public class RegisterPage extends AppCompatActivity {
    private Button signUpButton;
    private Button yearButton;


    public void openSignUpActivity() {
        Intent singUpIntent = new Intent(this, MainActivity.class);
        startActivity(singUpIntent);
    }

    private static final String TAG = "RegisterPage";
    private TextView mDisplayDate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    //code for majors
    TextView Majors;
    boolean[] majors;
    ArrayList<Integer> majorslist = new ArrayList<>();
    String[] majorsArray = {"Computer Science", "Physics"};

    //code for Interests
    TextView Interests;
    boolean[] interests;
    ArrayList<Integer> interestslist = new ArrayList<>();
    String[] interestsArray = {"Chess", "Golf", "Video Games", "Boba", "Hotpot","Basketball"};


    //code for Year
    TextView Years;
    boolean[] years;
    ArrayList<Integer> yearlist = new ArrayList<>();
    CharSequence[] yearArray = {"Incoming", "Freshman", "Sophomore", "Junior", "Senior", "Graduate Student"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);

        signUpButton = (Button) findViewById(R.id.RegisterSignUp);

        //Code for Interests
        Interests = (TextView) findViewById(R.id.Select_interests);
        //Selected Interests array
        interests = new boolean[interestsArray.length];

        Interests.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //initilize alert dialog
                AlertDialog.Builder builder = new AlertDialog.Builder(
                        RegisterPage.this);
                builder.setTitle("Select your Interests");
                builder.setCancelable(false);
                builder.setMultiChoiceItems(interestsArray, interests, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        //check conditions
                        if (isChecked){
                            interestslist.add(which);
                            Collections.sort(interestslist);

                        }else{
                            interestslist.remove(which);

                        }
                    }
                });
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        StringBuilder stringBuilder = new StringBuilder();
                        for(int j = 0; j < interestslist.size(); j++){
                            stringBuilder.append(interestsArray[interestslist.get(j)]);
                            if(j != interestslist.size()-1){
                                stringBuilder.append(", ");
                            }
                        }
                        Interests.setText(stringBuilder.toString());
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.setNeutralButton("Clear All", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        for (int j = 0; j < interests.length; j++){
                            interests[j] = false;
                            interestslist.clear();
                            Interests.setText("");
                        }
                    }
                });
                builder.show();


            }
        });


        //Code for majors
        Majors = (TextView) findViewById(R.id.Input_Major);
        //Selected major array
        majors = new boolean[majorsArray.length];

        Majors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //initilize alert dialog
                AlertDialog.Builder builder = new AlertDialog.Builder(
                        RegisterPage.this);
                builder.setTitle("Select your major");
                builder.setCancelable(false);
                builder.setMultiChoiceItems(majorsArray, majors, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        //check conditions
                        if (isChecked){
                            majorslist.add(which);
                            Collections.sort(majorslist);

                        }else{
                            majorslist.remove(which);

                        }
                    }
                });
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        StringBuilder stringBuilder = new StringBuilder();
                        for(int j = 0; j < majorslist.size(); j++){
                            stringBuilder.append(majorsArray[majorslist.get(j)]);
                            if(j != majorslist.size()-1){
                                stringBuilder.append(", ");
                            }
                        }
                        Majors.setText(stringBuilder.toString());
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.setNeutralButton("Clear All", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        for (int j = 0; j < majors.length; j++){
                            majors[j] = false;
                            majorslist.clear();
                            Majors.setText("");
                        }
                    }
                });
                builder.show();
            }
        });
        //Source: https://www.youtube.com/watch?v=rTXafzJP3Lk

        Years = (TextView) findViewById(R.id.select_year);

        Years.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(RegisterPage.this);
                alertDialogBuilder.setTitle("Select your year");
                alertDialogBuilder.setCancelable(true);
                alertDialogBuilder.setSingleChoiceItems(yearArray, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Years.setText(yearArray[which]);
                        dialog.dismiss();
                    }
                });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.setCanceledOnTouchOutside(true);
                alertDialog.show();
            }
        });


                //Code for Date of Birth
        mDisplayDate = (TextView) findViewById(R.id.DOB);
        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        RegisterPage.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                Log.d(TAG, "onDateSet: mm/dd/yyy: " + month + "/" + day + "/" + year);

                String date = month + "/" + day + "/" + year;
                mDisplayDate.setText(date);
            }
        };
        //Button activity, Cite:https://www.youtube.com/watch?v=bgIUdb-7Rqo
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSignUpActivity();
            }
        });



    }

}