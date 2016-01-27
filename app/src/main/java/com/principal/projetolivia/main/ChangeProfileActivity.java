package com.principal.projetolivia.main;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.principal.projetolivia.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by roosq on 20/01/2016.
 */
public class ChangeProfileActivity extends AppCompatActivity {

    private EditText textName;
    private EditText textAge;
    private Calendar myCalendar = Calendar.getInstance();
    private RelativeLayout buttonValidation;

    private OnChange mCallback;

    public interface OnChange{
        public void onProfileChanged();
    }

    protected void onCreate(Bundle savedInstanceState) {
        MainActivity.RemoveTheBar(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_profile);

        try{
            mCallback =(OnChange) this;
        } catch (ClassCastException e){
            throw new ClassCastException(this.toString()
                    + " must implement OnChange");
        }

        textName = (EditText) findViewById(R.id.textNameChangeProfile);
        textName.setText(MainActivity.getCurrentUser().getName());

        textAge = (EditText) findViewById(R.id.textAgeChangeProfile);
        textAge.setKeyListener(null);
        String myFormat = "dd/MM/yy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(myFormat, Locale.getDefault());
        textAge.setText(simpleDateFormat.format(MainActivity.getCurrentUser().getDateOfBirth().getTime()));

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
                    DatePickerDialog datePickerDialog = new DatePickerDialog(v.getContext(), date, MainActivity.getCurrentUser().getDateOfBirth().get(Calendar.YEAR), MainActivity.getCurrentUser().getDateOfBirth().get(Calendar.MONTH), MainActivity.getCurrentUser().getDateOfBirth().get(Calendar.DAY_OF_MONTH));

                    DatePicker datePicker = datePickerDialog.getDatePicker();

                    datePicker.setMaxDate(Calendar.getInstance().getTimeInMillis());
                    //datePicker.setCalendarViewShown(false);

                    datePickerDialog.show();
                }
                return true; // return is important...
            }
        });

        buttonValidation = (RelativeLayout) findViewById(R.id.buttonValidationChangeProfile);
        buttonValidation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate();
            }
        });
    }

    private void validate(){
        if (textName.getText().toString().matches("") || textAge.getText().toString().matches("")) {

            if (textName.getText().toString().matches("")) {
                Toast.makeText(this, getString(R.string.nameError), Toast.LENGTH_LONG).show();
            }
            if (textAge.getText().toString().matches("")) {
                Toast.makeText(this, getString(R.string.ageError), Toast.LENGTH_LONG).show();
            }

            return;
        } else {
            MainActivity.getCurrentUser().setDateOfBirth(myCalendar);
            MainActivity.getCurrentUser().setName(textName.getText().toString());
            MainActivity.fileConnector.setProfileList(this, MainActivity.userList);

            mCallback.onProfileChanged();

            getFragmentManager().popBackStackImmediate();
            getFragmentManager().popBackStackImmediate();
            // TODO : VOIR CE QU'IL FAUT FAIRE ICI AVANT -> getSupportFragmentManager().beginTransaction().remove(this).commit();
        }
    }

    private void updateLabel() {

        String myFormat = "dd/MM/yy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(myFormat, Locale.getDefault());

        textAge.setText(simpleDateFormat.format(myCalendar.getTime()));
    }
}
