package com.desai.vatsal.mydynamiccalendarlibrary;
import android.content.DialogInterface;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.desai.vatsal.mydynamiccalendar.MyDynamicCalendar;
import com.desai.vatsal.mydynamiccalendar.OnDateClickListener;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestDialogActivity extends AppCompatActivity implements OnDateClickListener {
    Button btnOpenCalendar;
    EditText edtInputDate;
    MyDynamicCalendar myCalendar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_dialog);
        btnOpenCalendar = (Button) findViewById(R.id.btnOpenCalendar);
        edtInputDate = (EditText) findViewById(R.id.edtInputDate);
        btnOpenCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                final DatePickerDialogFragment newFragment = DatePickerDialogFragment.newInstance();
//                Bundle args = new Bundle();
//                args.putString("date", edtInputDate.getText().toString());
//                newFragment.setArguments(args);
//                newFragment.setmListener(new DatePickerDialogFragment.OnFragmentInteractionListener() {
//                    @Override
//                    public void onDateClickListener(String date) {
//                        edtInputDate.setText(date);
//                        newFragment.dismiss();
//                    }
//                });
//                newFragment.show(getSupportFragmentManager(), "dialog");
                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(TestDialogActivity.this);
//                LayoutInflater inflater = getLayoutInflater();
//                View dialogView = inflater.inflate(R.layout.fragment_date_picker_dialog, null);
                MyDynamicCalendar myDynamicCalendar = new MyDynamicCalendar(TestDialogActivity.this);
                dialogBuilder.setView(myDynamicCalendar);
                dialogBuilder.setCancelable(false);
                dialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                dialogBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(TestDialogActivity.this, "OK", Toast.LENGTH_SHORT).show();
                    }
                });
                myCalendar = myDynamicCalendar;
                String[] selectDate = edtInputDate.getText().toString().split("/");
                int year = 2018;
                if (selectDate.length == 2) {
                    int month = Integer.parseInt(selectDate[1]);
                    int date = Integer.parseInt(selectDate[0]);;
                    myCalendar.setCalendarDate(date, month, year);
                }
                else {
                    myDynamicCalendar.goToCurrentDate();
                }
//                myCalendar = (MyDynamicCalendar) dialogView.findViewById(R.id.myCalendar);
                myCalendar.showMonthView();
                myCalendar.isSaturdayOff(true, "#658745", "#254632");
                myCalendar.isSaturdayOff(true, R.color.white, R.color.red);
                myCalendar.isSundayOff(true, "#658745", "#254632");
                myCalendar.isSundayOff(true, R.color.white, R.color.red);

                myCalendar.setCurrentDateTextColor("#00e600");
                myCalendar.setCurrentDateBackgroundColor("#ea1515");

                myCalendar.setHolidayCellBackgroundColor("#654248");
                myCalendar.setHolidayCellTextColor("#d590bb");

                myCalendar.setHolidayCellClickable(false);
                myCalendar.addHoliday("8-10-2018");
                myCalendar.addHoliday("8-11-2018");
                myCalendar.addHoliday("8-9-2018");
                myCalendar.addHoliday("3-6-2018");
                myCalendar.addHoliday("4-8-2018");
                myCalendar.addHoliday("5-8-2018");
                showMonthView();
                AlertDialog alertDialog = dialogBuilder.create();
                alertDialog.show();
            }
        });
    }
    private void showMonthView() {
        myCalendar.goToCurrentDate();


        myCalendar.showMonthView();

        myCalendar.setOnDateClickListener(new OnDateClickListener() {
            @Override
            public void onClick(Date date) {
                Log.e("date", String.valueOf(date));
                edtInputDate.setText(new SimpleDateFormat("dd/MM").format(date));
            }

            @Override
            public void onLongClick(Date date) {
                Log.e("date", String.valueOf(date));
            }
        });

    }

    @Override
    public void onClick(Date date) {
        edtInputDate.setText("Hello");
    }

    @Override
    public void onLongClick(Date date) {

    }
}
