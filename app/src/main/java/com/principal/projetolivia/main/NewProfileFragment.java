package com.principal.projetolivia.main;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.gc.materialdesign.views.ButtonFlat;
import com.gc.materialdesign.views.ButtonRectangle;
import com.principal.projetolivia.R;
import com.principal.projetolivia.com.principal.projetolivia.util.User;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * A placeholder fragment containing a simple view.
 */
public class NewProfileFragment extends Fragment {

    private EditText textName;
    private EditText textAge;
    private Calendar myCalendar = Calendar.getInstance();

    private ButtonRectangle buttonValidation;
    private ButtonFlat buttonChangeDate;

    public NewProfileFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_new_profile, container, false);

        textName = (EditText) rootView.findViewById(R.id.textNameProfile);
        textAge = (EditText) rootView.findViewById(R.id.textAgeProfile);
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
                    DatePickerDialog datePickerDialog = new DatePickerDialog(rootView.getContext(), date, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH));

                    DatePicker datePicker = datePickerDialog.getDatePicker();

                    datePicker.setMaxDate(Calendar.getInstance().getTimeInMillis());
                    //datePicker.setCalendarViewShown(false);

                    datePickerDialog.show();
                }
                return true; // return is important...
            }
        });

//        buttonChangeDate = (ButtonFlat) rootView.findViewById(R.id.buttonChangeDate);
//
//        buttonChangeDate.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                DatePickerDialog datePickerDialog = new DatePickerDialog(rootView.getContext(), date, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH));
//
//                DatePicker datePicker = datePickerDialog.getDatePicker();
//
//                datePicker.setMaxDate(Calendar.getInstance().getTimeInMillis());
//                //datePicker.setCalendarViewShown(false);
//
//                datePickerDialog.show();
//            }
//        });

        buttonValidation = (ButtonRectangle) rootView.findViewById(R.id.buttonValidation);
        buttonValidation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (textName.getText().toString().matches("") || textAge.getText().toString().matches("")) {

                    if (textName.getText().toString().matches("")){
                        Toast.makeText(getContext(), getContext().getString(R.string.nameError), Toast.LENGTH_LONG).show();
                    }
                    if (textAge.getText().toString().matches("")){
                        Toast.makeText(getContext(), getContext().getString(R.string.ageError), Toast.LENGTH_LONG).show();
                    }

                    return;
                } else {
                    User newUser = new User(textName.getText().toString(), myCalendar);
                    MainActivity.userList.add(newUser);

                    View view = getActivity().getCurrentFocus();
                    if (view != null) {
                        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(getActivity().INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                    }

                    FragmentTransaction ft = getFragmentManager().beginTransaction().replace(R.id.container, new MainActivityFragment());
                    ft.addToBackStack(getTag());
                    ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                    ft.commit();
                }
            }
        });

        return rootView;
    }

    private void updateLabel() {

        String myFormat = "dd/MM/yy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(myFormat, Locale.getDefault());

        textAge.setText(simpleDateFormat.format(myCalendar.getTime()));
    }
}
