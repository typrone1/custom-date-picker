package com.desai.vatsal.mydynamiccalendarlibrary;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.desai.vatsal.mydynamiccalendar.MyDynamicCalendar;
import com.desai.vatsal.mydynamiccalendar.OnDateClickListener;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DatePickerDialogFragment extends DialogFragment {
    public OnFragmentInteractionListener mListener;
    private MyDynamicCalendar myCalendar;

    public DatePickerDialogFragment() {
    }
    public static DatePickerDialogFragment newInstance() {
        DatePickerDialogFragment fragment = new DatePickerDialogFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    private void showMonthView() {

        if (getArguments().getString("date").isEmpty()) {
            myCalendar.goToCurrentDate();
        } else {
            String[] selectDate = getArguments().getString("date").split("/");
            int year = 2018;
            int month = Integer.parseInt(selectDate[1]);
            int date = Integer.parseInt(selectDate[0]);;
            myCalendar.setCalendarDate(date, month, year);
        }

        myCalendar.showMonthView();

        myCalendar.setOnDateClickListener(new OnDateClickListener() {
            @Override
            public void onClick(Date date) {
                Log.e("date", String.valueOf(date));
                mListener.onDateClickListener(new SimpleDateFormat("dd/MM").format(date));
            }

            @Override
            public void onLongClick(Date date) {
                Log.e("date", String.valueOf(date));
            }
        });

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_date_picker_dialog, container, false);
        myCalendar = (MyDynamicCalendar) view.findViewById(R.id.myCalendar);
        myCalendar.showMonthView();
        myCalendar.setOnDateClickListener(new OnDateClickListener() {
            @Override
            public void onClick(Date date) {
                Log.e("date", String.valueOf(date));
            }

            @Override
            public void onLongClick(Date date) {
                Log.e("date", String.valueOf(date));
            }
        });

        showMonthView();
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        void onDateClickListener(String date);
    }

    public OnFragmentInteractionListener getmListener() {
        return mListener;
    }

    public void setmListener(OnFragmentInteractionListener mListener) {
        this.mListener = mListener;
    }
}
