package com.principal.projetolivia.main;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.principal.projetolivia.R;
import com.principal.projetolivia.com.principal.projetolivia.util.User;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class NewProfileActivity extends AppCompatActivity {

    private EditText textName;
    private EditText textAge;
    private Calendar myCalendar = Calendar.getInstance();

    private RelativeLayout buttonValidation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_profile);


        textName = (EditText) findViewById(R.id.textNameProfile);
        textAge = (EditText) findViewById(R.id.textAgeProfile);
        textAge.setKeyListener(null);


        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }
        };

        textAge.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (MotionEvent.ACTION_UP == event.getAction()) {
                    DatePickerDialog datePickerDialog = new DatePickerDialog(v.getContext(), date, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH));

                    DatePicker datePicker = datePickerDialog.getDatePicker();

                    datePicker.setMaxDate(Calendar.getInstance().getTimeInMillis());
                    //datePicker.setCalendarViewShown(false);

                    datePickerDialog.show();
                }
                return true; // return is important...
            }
        });


        buttonValidation = (RelativeLayout) findViewById(R.id.buttonValidation);
        buttonValidation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (textName.getText().toString().matches("") || textAge.getText().toString().matches("")) {

                    if (textName.getText().toString().matches("")) {
                        Toast.makeText(v.getContext(), getString(R.string.nameError), Toast.LENGTH_LONG).show();
                    }
                    if (textAge.getText().toString().matches("")) {
                        Toast.makeText(v.getContext(), getString(R.string.ageError), Toast.LENGTH_LONG).show();
                    }

                    return;
                } else {
                    User newUser = new User(textName.getText().toString(), myCalendar);
                    MainActivity.userList.add(newUser);
                    MainActivity.currentUser = MainActivity.userList.size() - 1;

//                    View view = getActivity().getCurrentFocus();
//                    if (view != null) {
//                        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(getActivity().INPUT_METHOD_SERVICE);
//                        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
//                    }

                    Intent newActivity = new Intent(v.getContext(), MainActivity.class);
                    startActivity(newActivity);
                }
            }
        });
    }

    private void updateLabel() {

        String myFormat = "dd/MM/yy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(myFormat, Locale.getDefault());

        textAge.setText(simpleDateFormat.format(myCalendar.getTime()));
    }
}
