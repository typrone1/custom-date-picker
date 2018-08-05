package com.desai.vatsal.mydynamiccalendar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by HCL on 02-10-2016.
 */
public class MyDynamicCalendar extends LinearLayout implements DateListAdapter.OnItemSelectedListener {

    private Context context;
    private View rootView;

    private RecyclerView recyclerView_dates;
    private TextView tv_month_year, tv_mon, tv_tue, tv_wed, tv_thu, tv_fri, tv_sat, tv_sun;
    private ImageView iv_previous, iv_next;
    private LinearLayout parentLayout, ll_upper_part, ll_blank_space, ll_header_views, ll_week_day_layout, ll_month_view_below_events;

    private OnDateClickListener onDateClickListener;

    private ArrayList<DateModel> dateModelList;
    private DateListAdapter dateListAdapter;
    private SimpleDateFormat sdfMonthYear = new SimpleDateFormat("MMM - yyyy");
    private Calendar calendar = Calendar.getInstance();
    public MyDynamicCalendar(Context context) {
        super(context);

        this.context = context;

        if (!isInEditMode()) {
            init();
        }
    }

    public MyDynamicCalendar(Context context, AttributeSet attrs) {
        super(context, attrs);

        this.context = context;

        if (!isInEditMode()) {
            init();
        }
    }

    private void init() {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        rootView = inflater.inflate(R.layout.my_dynamic_calendar, this, true);

        recyclerView_dates = (RecyclerView) rootView.findViewById(R.id.recyclerView_dates);
        iv_previous = (ImageView) rootView.findViewById(R.id.iv_previous);
        iv_next = (ImageView) rootView.findViewById(R.id.iv_next);
        parentLayout = (LinearLayout) rootView.findViewById(R.id.parentLayout);
        ll_upper_part = (LinearLayout) rootView.findViewById(R.id.ll_upper_part);
        ll_blank_space = (LinearLayout) rootView.findViewById(R.id.ll_blank_space);
        ll_header_views = (LinearLayout) rootView.findViewById(R.id.ll_header_views);
        ll_week_day_layout = (LinearLayout) rootView.findViewById(R.id.ll_week_day_layout);
        ll_month_view_below_events = (LinearLayout) rootView.findViewById(R.id.ll_month_view_below_events);
        tv_month_year = (TextView) rootView.findViewById(R.id.tv_month_year);
        tv_mon = (TextView) rootView.findViewById(R.id.tv_mon);
        tv_tue = (TextView) rootView.findViewById(R.id.tv_tue);
        tv_wed = (TextView) rootView.findViewById(R.id.tv_wed);
        tv_thu = (TextView) rootView.findViewById(R.id.tv_thu);
        tv_fri = (TextView) rootView.findViewById(R.id.tv_fri);
        tv_sat = (TextView) rootView.findViewById(R.id.tv_sat);
        tv_sun = (TextView) rootView.findViewById(R.id.tv_sun);
        actionListeners();
    }

    private void actionListeners() {

        iv_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (AppConstants.isShowMonth) {
                    setMonthView("add");
                }
            }
        });

        iv_previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (AppConstants.isShowMonth) {
                    setMonthView("sub");
                }

            }
        });


    }

    public void setCalendarBackgroundColor(String color) {
        if (!TextUtils.isEmpty(color)) {
            parentLayout.setBackgroundColor(Color.parseColor(color));
        }
    }

    public void setCalendarBackgroundColor(int color) {
        parentLayout.setBackgroundColor(color);
    }

    public void setHeaderBackgroundColor(String color) {
        if (!TextUtils.isEmpty(color)) {
            ll_header_views.setBackgroundColor(Color.parseColor(color));
        }
    }

    public void setHeaderBackgroundColor(int color) {
        ll_header_views.setBackgroundColor(color);
    }

    public void setHeaderTextColor(String color) {
        if (!TextUtils.isEmpty(color)) {
            tv_month_year.setTextColor(Color.parseColor(color));
        }
    }

    public void setHeaderTextColor(int color) {
        tv_month_year.setTextColor(color);
    }

    public void setNextPreviousIndicatorColor(String color) {
        if (!TextUtils.isEmpty(color)) {
            iv_previous.setColorFilter(Color.parseColor(color));
            iv_next.setColorFilter(Color.parseColor(color));
        }
    }

    public void setNextPreviousIndicatorColor(int color) {
        iv_previous.setColorFilter(color);
        iv_next.setColorFilter(color);
    }

    public void isSaturdayOff(boolean b, String layoutcolor, String textcolor) {
        if (b) {
            if (!TextUtils.isEmpty(layoutcolor) && !TextUtils.isEmpty(textcolor)) {
                AppConstants.isSaturdayOff = true;
                AppConstants.strSaturdayOffBackgroundColor = layoutcolor;
                AppConstants.strSaturdayOffTextColor = textcolor;
                tv_sat.setTextColor(Color.parseColor(textcolor));
            }
        }
    }

    public void isSaturdayOff(boolean b, int layoutColor, int textColor) {
        if (b) {
            AppConstants.isSaturdayOff = true;
            AppConstants.saturdayOffBackgroundColor = layoutColor;
            AppConstants.saturdayOffTextColor = textColor;
            tv_sat.setTextColor(textColor);
        }
    }

    public void isSundayOff(boolean b, String layoutcolor, String textcolor) {
        if (b) {
            if (!TextUtils.isEmpty(layoutcolor) && !TextUtils.isEmpty(textcolor)) {
                AppConstants.isSundayOff = true;
                AppConstants.strSundayOffBackgroundColor = layoutcolor;
                AppConstants.strSundayOffTextColor = textcolor;
                tv_sun.setTextColor(Color.parseColor(textcolor));
            }
        }
    }

    public void isSundayOff(boolean b, int layoutColor, int textColor) {
        if (b) {
            AppConstants.isSundayOff = true;
            AppConstants.sundayOffBackgroundColor = layoutColor;
            AppConstants.sundayOffTextColor = textColor;
            tv_sun.setTextColor(textColor);
        }
    }

    public void setExtraDatesOfMonthBackgroundColor(int color) {
        AppConstants.extraDatesBackgroundColor = color;
    }

    public void setExtraDatesOfMonthBackgroundColor(String color) {
        if (!TextUtils.isEmpty(color)) {
            AppConstants.strExtraDatesBackgroundColor = color;
        }
    }

    public void setExtraDatesOfMonthTextColor(int color) {
        AppConstants.extraDatesTextColor = color;
    }

    public void setExtraDatesOfMonthTextColor(String color) {
        if (!TextUtils.isEmpty(color)) {
            AppConstants.strExtraDatesTextColor = color;
        }
    }

    public void setDatesOfMonthBackgroundColor(int color) {
        AppConstants.datesBackgroundColor = color;
    }

    public void setDatesOfMonthBackgroundColor(String color) {
        if (!TextUtils.isEmpty(color)) {
            AppConstants.strDatesBackgroundColor = color;
        }
    }

    public void setDatesOfMonthTextColor(int color) {
        AppConstants.datesTextColor = color;
    }

    public void setDatesOfMonthTextColor(String color) {
        if (!TextUtils.isEmpty(color)) {
            AppConstants.strDatesTextColor = color;
        }
    }

    public void setCurrentDateBackgroundColor(int color) {
        AppConstants.currentDateBackgroundColor = color;
    }

    public void setCurrentDateBackgroundColor(String color) {
        if (!TextUtils.isEmpty(color)) {
            AppConstants.strCurrentDateBackgroundColor = color;
        }
    }

    public void setCurrentDateTextColor(int color) {
        AppConstants.currentDateTextColor = color;
    }

    public void setCurrentDateTextColor(String color) {
        if (!TextUtils.isEmpty(color)) {
            AppConstants.strCurrentDateTextColor = color;
        }
    }
//    -------------------------- All methods of holiday --------------------------

    public void setHolidayCellClickable(boolean b) {
        if (b) {
            AppConstants.isHolidayCellClickable = true;
        }
    }

    public void setHolidayCellBackgroundColor(int color) {
        AppConstants.holidayCellBackgroundColor = color;
    }

    public void setHolidayCellBackgroundColor(String color) {
        if (!TextUtils.isEmpty(color)) {
            AppConstants.strHolidayCellBackgroundColor = color;
        }
    }

    public void setHolidayCellTextColor(int color) {
        AppConstants.holidayCellTextColor = color;
    }

    public void setHolidayCellTextColor(String color) {
        if (!TextUtils.isEmpty(color)) {
            AppConstants.strHolidayCellTextColor = color;
        }
    }

    public void addHoliday(String date) {
        if (!TextUtils.isEmpty(date)) {
            String tmpDate = GlobalMethods.convertDate(date, AppConstants.sdfDate, AppConstants.sdfDate);
            AppConstants.holidayList.add(tmpDate);
        }
    }

//    -------------------------------------------------------------------------


    public void showMonthView() {
        setMonthView("");
    }

    private void setMonthView(String sign) {
        dateModelList = new ArrayList<>();
        dateListAdapter = new DateListAdapter(context, dateModelList);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 7);
        recyclerView_dates.setLayoutManager(gridLayoutManager);
        recyclerView_dates.setAdapter(dateListAdapter);
        AppConstants.isShowMonth = true;
        AppConstants.isShowMonthWithBellowEvents = false;
        AppConstants.isShowWeek = false;
        AppConstants.isShowDay = false;
        AppConstants.isAgenda = false;

        ll_upper_part.setVisibility(View.VISIBLE);
        ll_month_view_below_events.setVisibility(View.GONE);
        ll_blank_space.setVisibility(View.GONE);
        if (sign.equals("add")) {
            AppConstants.main_calendar.set(Calendar.MONTH, (AppConstants.main_calendar.get(Calendar.MONTH) + 1));
        } else if (sign.equals("sub")) {
            AppConstants.main_calendar.set(Calendar.MONTH, (AppConstants.main_calendar.get(Calendar.MONTH) - 1));
        }

        tv_month_year.setText(sdfMonthYear.format(AppConstants.main_calendar.getTime()));

        // set date start of month
        calendar.setTime(AppConstants.main_calendar.getTime());
        calendar.set((Calendar.DAY_OF_MONTH), -1);

        int monthBeginningCell = calendar.get(Calendar.DAY_OF_WEEK) - 1;

        if (monthBeginningCell == -1) {
            calendar.add(Calendar.DAY_OF_MONTH, -6);
        } else if (monthBeginningCell == 0) {
            calendar.add(Calendar.DAY_OF_MONTH, -7);
        } else {
            calendar.add(Calendar.DAY_OF_MONTH, - monthBeginningCell);
        }

        dateModelList.clear();

        while (dateModelList.size() < 42) {
            DateModel model = new DateModel();
            model.setDates(calendar.getTime());
            model.setFlag("month");
            dateModelList.add(model);

            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }

        dateListAdapter.notifyDataSetChanged();
    }


    public void setOnDateClickListener(OnDateClickListener onDateClickListener) {
        this.onDateClickListener = onDateClickListener;
    }

    public void goToCurrentDate() {
        AppConstants.main_calendar = Calendar.getInstance();

        if (AppConstants.isShowMonth) {
            setMonthView("");
        }
    }

    public void refreshCalendar() {
        if (AppConstants.isShowMonth) {
            setMonthView("");
        }
    }

    public void setCalendarDate(int date, int month, int year) {
        AppConstants.main_calendar.set(Calendar.YEAR, year);
        AppConstants.main_calendar.set(Calendar.MONTH, month - 1);
        AppConstants.main_calendar.set(Calendar.DAY_OF_MONTH, date);
        refreshCalendar();
    }
    @Override
    public void onItemSelected(DateModel selectableItem) {
        List<DateModel> selectedItems = dateListAdapter.getSelectedItems();
        Toast.makeText(getContext(),"Count: " + selectedItems.size(),Toast.LENGTH_LONG).show();
    }
}
