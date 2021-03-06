package rafting.masti.rishikesh.holiday.adventure.bailiwick.com.adventure.ActivityVendor;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import dmax.dialog.SpotsDialog;
import rafting.masti.rishikesh.holiday.adventure.bailiwick.com.adventure.Adapter.CampInventoryListRecyclerAdapter;
import rafting.masti.rishikesh.holiday.adventure.bailiwick.com.adventure.App.AppController1;
import rafting.masti.rishikesh.holiday.adventure.bailiwick.com.adventure.AppUtils.UtilsUrl;
import rafting.masti.rishikesh.holiday.adventure.bailiwick.com.adventure.Modal.CampInventory_Beans;
import rafting.masti.rishikesh.holiday.adventure.bailiwick.com.adventure.R;
import rafting.masti.rishikesh.holiday.adventure.bailiwick.com.adventure.Session.SharedPref;
import rafting.masti.rishikesh.holiday.adventure.bailiwick.com.adventure.Support.CheckConnectivity;
import rafting.masti.rishikesh.holiday.adventure.bailiwick.com.adventure.Support.RootActivity;
import rafting.masti.rishikesh.holiday.adventure.bailiwick.com.adventure.Utils.Itags;

/**
 * Created by Prince on 05-03-2018.
 */

public class ActivityCampInventory extends RootActivity implements CampInventoryListRecyclerAdapter.ItemClickMasterMenuListInterface {
    private Context context;
    SpotsDialog prog;


    Spinner spinner_Select_camp;
    RecyclerView recyclerViewCamp_inventory;
    ArrayList<CampInventory_Beans> camp_inventoryList;
    CampInventoryListRecyclerAdapter campInventoryListRecyclerAdapter;

    private TextView txt_prev_date_schedule, txt_next_date_schedule;
    List<String> campListID = new ArrayList<>();

    List campList;
    ArrayAdapter adpCamp;
    String todaysDate, next_week_date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.camp_inventory);
        context = ActivityCampInventory.this;
        setToolbar();
        createIDS();
        SpinnerClick();
        getCamp(SharedPref.getUserID());
        GetTodaysSeats();
        clickListner();

    }

    private void clickListner() {
        txt_next_date_schedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (camp_inventoryList.size() > 0) {

                    String str_current_date = camp_inventoryList.get(camp_inventoryList.size() - 1).getStr_date();
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
                    Date NextToDate = getDate(str_current_date);
                    Date next7date = getNext7Date(NextToDate);
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    String next_week_date = format.format(next7date);
                    getCampDetail(SharedPref.getUserID(), campListID.get(spinner_Select_camp.getSelectedItemPosition() - 1).toString(), str_current_date, next_week_date);
                } else {
                    Toast.makeText(getApplicationContext(), "There is no Camp Detail", Toast.LENGTH_LONG).show();
                }
            }
        });
        txt_prev_date_schedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (camp_inventoryList.size() > 0) {
                    String str_current_date = camp_inventoryList.get(0).getStr_date();

                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
                    Date NextToDate = getDate(str_current_date);
                    Date Before7date = getPrevious7Date(NextToDate);
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    String next_week_date = format.format(Before7date);
                    getCampDetail(SharedPref.getUserID(), campListID.get(spinner_Select_camp.getSelectedItemPosition() - 1).toString(), next_week_date,str_current_date );


                } else {
                    Toast.makeText(getApplicationContext(), "There is no Camp Detail", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void GetTodaysSeats() {
        Date c = Calendar.getInstance().getTime();
        System.out.println("Current time => " + c);

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        todaysDate = df.format(c);

        Date NextToDate = getDate(todaysDate);
        Date next7date = getNext7Date(NextToDate);

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        next_week_date = format.format(next7date);

    }

    private void getCamp(final String userID) {

        prog.setTitle("Please wait.");
        prog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, UtilsUrl.BASE_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        if (new CheckConnectivity().isConnected(context)) {
                            try {
                                prog.dismiss();
                                Log.e("Response : prince ", response);
                                if (response != null) {
                                    JSONObject jsData = new JSONObject(response);

                                    String status = jsData.getString("status");
                                    if (status.equalsIgnoreCase("1")) {

                                        JSONArray jsCamp = jsData.getJSONArray("camps");
                                        //            List<String> Camp_list = new ArrayList<>();
//                                        raftingPoint_list.add("Select Point");
                                        campListID.clear();
                                        campList.clear();
                                        campList.add("Select Camp");

                                        for (int i = 0; i < jsCamp.length(); i++) {

                                            campList.add(jsCamp.getJSONObject(i).getString("camp_name"));
                                            campListID.add(jsCamp.getJSONObject(i).getString("camp_id"));
                                        }
                                        adpCamp.notifyDataSetChanged();


                                    } else {
                                        String msg = jsData.getString("msg");
                                        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();

                                    }
                                } else {
                                    Toast.makeText(context, "Invalid Response !!!", Toast.LENGTH_LONG).show();

                                }
                            } catch (Exception ex) {
                                ex.printStackTrace();

                            }
                        } else {
                            Toast.makeText(context, "Check Your connetion", Toast.LENGTH_LONG).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                prog.dismiss();
                Toast.makeText(context, error.toString(), Toast.LENGTH_LONG).show();

                Log.e("Error :", error.toString());
            }
        }) {

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> header = new HashMap<String, String>();
                header.put(Itags.Header, "ABC98XYZ53IJ61L");
                // params.put("Accept-Language", "fr");

                return header;
            }

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();

                params.put("action", UtilsUrl.Action_VendorCampListing);
                params.put("vendor_id", userID);

                Log.e("Param Response ", "" + params);
                return params;
            }
        };

        AppController1.getInstance().addToRequestQueue(stringRequest);


    }


    private void SpinnerClick() {
        spinner_Select_camp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {

                } else {
                    String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
                    Log.e("current date", date);
                    Log.e("Date Structure", "Todays Date : " + todaysDate + "  next Week Dates : " + next_week_date);

                    getCampDetail(SharedPref.getUserID(), campListID.get(position - 1).toString(), todaysDate, next_week_date);

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void getCampDetail(final String userID, final String CampId, final String Start_date, final String End_Date) {
        Log.e("on Process", "On Process...");

        prog.setTitle("Please wait.");
        prog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, UtilsUrl.BASE_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        if (new CheckConnectivity().isConnected(context)) {
                            try {
                                prog.dismiss();
                                Log.e("Response : prince ", response);
                                if (response != null) {
                                    JSONObject jsData = new JSONObject(response);

                                    String status = jsData.getString("status");
                                    if (status.equalsIgnoreCase("1")) {

                                        JSONArray jsCampInventory = jsData.getJSONArray("inventory");
                                        camp_inventoryList.clear();
                                        String current_date = Start_date;
                                        Date date, NextToDate = null;

                                        for (int i = 0; i < jsCampInventory.length(); i++) {

                                            String dayOfTheWeek;
                                            if (i == 0) {
                                                //   date = getDate(current_date);
                                                NextToDate = getDate(current_date);
                                                dayOfTheWeek = (String) DateFormat.format("EEEE", NextToDate);

                                            } else {
                                                date = getNextDate(NextToDate);
                                                NextToDate = date;
                                                dayOfTheWeek = (String) DateFormat.format("EEEE", date);
                                            }
                                            Log.e("Day ", dayOfTheWeek);
                                            Log.e("date", "" + NextToDate);
                                            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                                            String str_date = format.format(NextToDate);
                                            camp_inventoryList.add(new CampInventory_Beans(
                                                    jsCampInventory.getJSONObject(i).getString("room_type"),
                                                    "" + str_date, dayOfTheWeek, jsCampInventory.getJSONObject(i).getString("total_rooms")
                                                    , jsCampInventory.getJSONObject(i).getString("available_rooms"),
                                                    jsCampInventory.getJSONObject(i).getString("booked_rooms")));

                                        }
                                        campInventoryListRecyclerAdapter.notifyDataSetChanged();
                                    } else {
                                        String msg = jsData.getString("msg");
                                        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();

                                    }
                                } else {
                                    Toast.makeText(context, "Invalid Response !!!", Toast.LENGTH_LONG).show();

                                }
                            } catch (Exception ex) {
                                ex.printStackTrace();

                            }
                        } else {
                            Toast.makeText(context, "Check Your connetion", Toast.LENGTH_LONG).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                prog.dismiss();
                Toast.makeText(context, error.toString(), Toast.LENGTH_LONG).show();

                Log.e("Error :", error.toString());
            }
        }) {

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> header = new HashMap<String, String>();
                header.put(Itags.Header, "ABC98XYZ53IJ61L");
                // params.put("Accept-Language", "fr");

                return header;
            }

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();

                params.put("action", UtilsUrl.Action_VendorCampDetails);
                params.put("vendor_id", userID);
                params.put("camp_id", CampId);
                params.put("startDate", Start_date);
                params.put("endDate", End_Date);

                Log.e("Param Response ", "" + params);
                return params;
            }
        };

        AppController1.getInstance().addToRequestQueue(stringRequest);


    }

    public static Date getNextDate(Date curDate) {
        Date tomorrow = null;
        try {
            final Date date = curDate;
            final Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.DAY_OF_YEAR, 1);
            tomorrow = calendar.getTime();
            return tomorrow;

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return tomorrow;
    }

    public static Date getNext7Date(Date curDate) {
        Date tomorrow = null;
        try {
            final Date date = curDate;
            final Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.DAY_OF_YEAR, 7);
            tomorrow = calendar.getTime();
            return tomorrow;

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return tomorrow;
    }

    public static Date getPrevious7Date(Date curDate) {
        Date tomorrow = null;
        try {
            final Date date = curDate;
            final Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.DAY_OF_YEAR, -7);
            tomorrow = calendar.getTime();
            return tomorrow;

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return tomorrow;
    }

    private Date getDate(String current_date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = format.parse(current_date);
            System.out.println(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    private void createIDS() {
        prog = new SpotsDialog(context, R.style.Custom);

        spinner_Select_camp = (Spinner) findViewById(R.id.spinner_Select_camp);
        recyclerViewCamp_inventory = (RecyclerView) findViewById(R.id.recyclerview_inventory_camp);

        txt_prev_date_schedule = (TextView) findViewById(R.id.txt_prev_date_schedule);
        txt_next_date_schedule = (TextView) findViewById(R.id.txt_next_date_schedule);
        campList = new ArrayList();
        campList.add("Select Camp");

        adpCamp = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, campList);
        adpCamp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_Select_camp.setAdapter(adpCamp);


        camp_inventoryList = new ArrayList<>();
        campInventoryListRecyclerAdapter = new CampInventoryListRecyclerAdapter(context, camp_inventoryList, this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(context);
        mLayoutManager.setAutoMeasureEnabled(true);
        recyclerViewCamp_inventory.setLayoutManager(mLayoutManager);
        recyclerViewCamp_inventory.setItemAnimator(new DefaultItemAnimator());
        recyclerViewCamp_inventory.setAdapter(campInventoryListRecyclerAdapter);


    }

    @Override
    protected void onResume() {
        spinner_Select_camp.setSelection(0);
        super.onResume();
    }

    private void setToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("INVENTORY");


    }

    @Override
    public void onItemMasterClick(int position) {
        Log.e("i m heree", "i m heree");
        Intent i = new Intent(ActivityCampInventory.this, Activity_camp_Room_control.class);
        i.putExtra("select_date", camp_inventoryList.get(position).getStr_date());
        i.putExtra("camp_ID", campListID.get(spinner_Select_camp.getSelectedItemPosition() - 1).toString());

        i.putExtra("room_typeID", camp_inventoryList.get(position).getStr_id());

        i.putExtra("Available", camp_inventoryList.get(position).getStr_available_room());
        i.putExtra("Booked", camp_inventoryList.get(position).getStr_booked_room());
        i.putExtra("Total", camp_inventoryList.get(position).getStr_total_room());

        startActivity(i);
    }
}
