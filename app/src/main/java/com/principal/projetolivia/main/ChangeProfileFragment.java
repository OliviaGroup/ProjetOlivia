package com.principal.projetolivia.main;


import android.app.DatePickerDialog;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.principal.projetolivia.R;
import com.principal.projetolivia.com.principal.projetolivia.util.User;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChangeProfileFragment extends Fragment {

    private EditText textName;
    private EditText textAge;
    private Calendar myCalendar = Calendar.getInstance();
    RelativeLayout buttonValidation;


    public ChangeProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView =  inflater.inflate(R.layout.fragment_change_profile, container, false);

        textName = (EditText) rootView.findViewById(R.id.textNameChangeProfile);
        textName.setText(MainActivity.getCurrentUser().getName());

        textAge = (EditText) rootView.findViewById(R.id.textAgeChangeProfile);
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
                    DatePickerDialog datePickerDialog = new DatePickerDialog(rootView.getContext(), date, MainActivity.getCurrentUser().getDateOfBirth().get(Calendar.YEAR), MainActivity.getCurrentUser().getDateOfBirth().get(Calendar.MONTH), MainActivity.getCurrentUser().getDateOfBirth().get(Calendar.DAY_OF_MONTH));

                    DatePicker datePicker = datePickerDialog.getDatePicker();

                    datePicker.setMaxDate(Calendar.getInstance().getTimeInMillis());
                    //datePicker.setCalendarViewShown(false);

                    datePickerDialog.show();
                }
                return true; // return is important...
            }
        });

        buttonValidation = (RelativeLayout) rootView.findViewById(R.id.buttonValidationChangeProfile);
        buttonValidation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate();
            }
        });

        return rootView;
    }


    private void validate(){
        if (textName.getText().toString().matches("") || textAge.getText().toString().matches("")) {

            if (textName.getText().toString().matches("")) {
                Toast.makeText(getContext(), getContext().getString(R.string.nameError), Toast.LENGTH_LONG).show();
            }
            if (textAge.getText().toString().matches("")) {
                Toast.makeText(getContext(), getContext().getString(R.string.ageError), Toast.LENGTH_LONG).show();
            }

            return;
        } else {
            MainActivity.getCurrentUser().setDateOfBirth(myCalendar);
            MainActivity.getCurrentUser().setName(textName.getText().toString());

            getFragmentManager().popBackStackImmediate();
            getFragmentManager().popBackStackImmediate();
            getActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();
        }
    }

    private void updateLabel() {

        String myFormat = "dd/MM/yy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(myFormat, Locale.getDefault());

        textAge.setText(simpleDateFormat.format(myCalendar.getTime()));
    }
}
