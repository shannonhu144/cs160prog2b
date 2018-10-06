package com.example.shann.proj2;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.location.FusedLocationProviderClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class MainActivity extends AppCompatActivity implements LocationListener {
    public static final String ZIP_CODE = "com.example.shann.proj2.ZIPCODE";
    public static final String REPRESENTATIVES = "com.example.shann.proj2.REPRESENTATIVES";
    public static final String SENATORS = "com.example.shann.proj2.SENATORS";
    public static final String REPRESENTATIVE = "com.example.shann.proj2.REPRESENTATIVE";
    public static final String MESSAGE = "com.example.shann.proj2.MESSAGE";
    public static final String PICTURE = "com.example.shann.proj2.PICTURE";
    private String starter = "https://api.geocod.io/v1.3/geocode?";
    private static final String GEO_API_KEY = "53fbb44bb014f4401febf041b5420bb5e24fb4b";
    private static final String PUBLICA_API_KEY = "1Yl8ZrWXYt65S7Q1z0fbM7UtDpN2G9mDrkhTy2qF";
    private static List<Integer> zipcodes = generateZipCodes();
    private FusedLocationProviderClient mFusedLocationClient;
    private RequestQueue requestQueue;
    private static MainActivity mInstance;
    private LocationManager mLocationManager;
    private ProgressBar spinner1;
    private TextView loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mInstance = this;
        mLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        spinner1 = (ProgressBar) findViewById(R.id.progressBar1);
        spinner1.setVisibility(View.INVISIBLE);
        loading = (TextView) findViewById(R.id.Loading) ;
        loading.setVisibility(View.INVISIBLE);
        findViewById(R.id.button1).setVisibility(View.VISIBLE);
        findViewById(R.id.button2).setVisibility(View.VISIBLE);
        findViewById(R.id.button3).setVisibility(View.VISIBLE);
        findViewById(R.id.zipCode).setVisibility(View.VISIBLE);

        Button b1 = (Button) findViewById(R.id.button1);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                findRepCurrLoc(view);
            }
        });
    }
    public static List<Integer> rangeClosed(int start, int end) {
        List<Integer> temp = new ArrayList<>();
        for (int i = start; i <= end; i++) {
            temp.add(i);
        }
        return temp;
    }

    public static List<Integer> generateZipCodes() {
        List<Integer> result = new ArrayList<>();
        result.addAll(rangeClosed(99501, 99950));
        result.addAll(rangeClosed(35004, 36925));
        result.addAll(rangeClosed(71601, 72959));
        result.add(75502);
        result.addAll(rangeClosed(85001, 86556));
        result.addAll(rangeClosed(90001, 96162));
        result.addAll(rangeClosed(80001, 81658));
        result.addAll(rangeClosed(6001, 6389));
        result.addAll(rangeClosed(6401, 6928));
        //result.addAll(rangeClosed(20001, 20039));
        //result.addAll(rangeClosed(20042, 20599));
        //result.add(20799);
        result.addAll(rangeClosed(19701, 19980));
        result.addAll(rangeClosed(32004, 34997));
        result.addAll(rangeClosed(30001, 31999));
        result.add(39901);
        result.addAll(rangeClosed(96701, 96898));
        result.addAll(rangeClosed(50001, 52809));
        result.addAll(rangeClosed(68119, 68120));
        result.addAll(rangeClosed(83201, 83876));
        result.addAll(rangeClosed(60001, 62999));
        result.addAll(rangeClosed(46001, 47997));
        result.addAll(rangeClosed(66002, 67954));
        result.addAll(rangeClosed(40003, 42788));
        result.addAll(rangeClosed(70001, 71232));
        result.addAll(rangeClosed(71234, 71497));
        result.addAll(rangeClosed(1001, 2791));
        result.addAll(rangeClosed(5501, 5544));
        result.addAll(rangeClosed(20331, 20331));
        result.addAll(rangeClosed(20335, 20797));
        result.addAll(rangeClosed(20812, 21930));
        result.addAll(rangeClosed(3901, 4992));
        result.addAll(rangeClosed(48001, 49971));
        result.addAll(rangeClosed(55001, 56763));
        result.addAll(rangeClosed(63001, 65899));
        result.addAll(rangeClosed(38601, 39776));
        result.addAll(rangeClosed(71233, 71233));
        result.addAll(rangeClosed(59001, 59937));
        result.addAll(rangeClosed(27006, 28909));
        result.addAll(rangeClosed(58001, 58856));
        result.addAll(rangeClosed(68001, 68118));
        result.addAll(rangeClosed(68122, 69367));
        result.addAll(rangeClosed(3031, 3897));
        result.addAll(rangeClosed(7001, 8989));
        result.addAll(rangeClosed(87001, 88441));
        result.addAll(rangeClosed(88901, 89883));
        result.addAll(rangeClosed(6390, 6390));
        result.addAll(rangeClosed(10001, 14975));
        result.addAll(rangeClosed(43001, 45999));
        result.addAll(rangeClosed(73001, 73199));
        result.addAll(rangeClosed(73401, 74966));
        result.addAll(rangeClosed(97001, 97920));
        result.addAll(rangeClosed(15001, 19640));
        result.addAll(rangeClosed(2801, 2940));
        result.addAll(rangeClosed(29001, 29948));
        result.addAll(rangeClosed(57001, 57799));
        result.addAll(rangeClosed(37010, 38589));
        result.addAll(rangeClosed(73301, 73301));
        result.addAll(rangeClosed(75001, 75501));
        result.addAll(rangeClosed(75503, 79999));
        result.addAll(rangeClosed(88510, 88589));
        result.addAll(rangeClosed(84001, 84784));
        result.addAll(rangeClosed(20101, 20198));
        result.addAll(rangeClosed(20598, 20598));
        result.addAll(rangeClosed(22001, 24658));
        result.addAll(rangeClosed(5001, 5495));
        result.addAll(rangeClosed(5601, 5907));
        result.addAll(rangeClosed(98001, 99403));
        result.addAll(rangeClosed(53001, 54990));
        result.addAll(rangeClosed(24701, 26886));
        result.addAll(rangeClosed(82001, 83128));
        return result;
    }

    public void findReps(final View view, String url, final int numDistricts) {
        final Intent intent = new Intent(this, CongressionalView.class);
        final ArrayList<Representative> reps = new ArrayList<>();
        final ArrayList<Representative> sens = new ArrayList<>();
        final JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        //Success Callback
                        System.out.println(response);
                        try {
                            JSONArray results = response.getJSONArray("results");
                            if (results.length() == 0) {
                                if (numDistricts != -1) {
                                    findRepRandLoc(view);
                                }
                                else {
                                    // show some error message for invalid zipcode
                                }
                            } else {
                                int numReps = 0;
                                int numSenators = 0;
                                JSONObject resultsObject = results.getJSONObject(0);
                                JSONObject fields = resultsObject.getJSONObject("fields");
                                JSONArray districts = (JSONArray) fields.get("congressional_districts");
                                String msgCopy = "Using address: " + resultsObject.getString("formatted_address");
                                int maxDistricts = districts.length();
                                if (numDistricts != -1) {
                                    maxDistricts = numDistricts;
                                }
                                for (int i = 0; i < maxDistricts; i++) {
                                    JSONObject districtsObject = districts.getJSONObject(i);
                                    JSONArray legislators = (JSONArray) districtsObject.get("current_legislators");
                                    for (int j = 0; j < legislators.length(); j++) {
                                        String p;
                                        String id;
                                        Representative rep = new Representative();
                                        JSONObject rep1 = legislators.getJSONObject(j);
                                        JSONObject rep1Bio = rep1.getJSONObject("bio");
                                        String rep1Name = rep1Bio.getString("first_name") + " " + rep1Bio.getString("last_name");
                                        String rep1Party = rep1Bio.getString("party");
                                        String type;
                                        if (rep1.getString("type").equals("representative")) {
                                            type = " Representative";
                                        } else {
                                            type = " Senator";
                                        }
                                        rep.setName(rep1Name);
                                        if (!sens.contains(rep)) {
                                            rep.setParty(rep1Party + type);
                                            JSONObject rep1Contact = rep1.getJSONObject("contact");
                                            String rep1Site = rep1Contact.getString("url");
                                            rep.setWebsite(rep1Site);
                                            String rep1Email = rep1Contact.getString("contact_form");
                                            rep.setEmail(rep1Email);
                                            id = rep1.getJSONObject("references").getString("bioguide_id");
                                            rep.setId(id);
                                            rep.setPicture("http://bioguide.congress.gov/bioguide/photo/" + id.charAt(0) + "/" + id + ".jpg");
                                            if (type.equals(" Senator")) {
                                                sens.add(rep);
                                            } else {
                                                reps.add(rep);
                                            }
                                        }
                                    }
                                }
                                findRepCommittees(reps, sens, 0, intent, msgCopy);
                            }
                        } catch (JSONException e) {
                            // Show some error message for invalid location
                            hideProgressBar();
                            TextView tv = findViewById(R.id.error);
                            tv.setText("Error: could not find data");
                            tv.setVisibility(View.VISIBLE);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Failure Callback
                        hideProgressBar();
                        findViewById(R.id.error).setVisibility(View.VISIBLE);
                        TextView tv = findViewById(R.id.error);
                        tv.setText("Error: invalid location");
                    }
                }) {
            /** Passing some request headers* */
            @Override
            public HashMap<String, String> getHeaders() throws AuthFailureError {
                HashMap headers = new HashMap();
                headers.put("Content-Type", "application/json");
                headers.put("apiKey", GEO_API_KEY);
                return headers;
            }
        };
        MainActivity.getInstance().addToRequestQueue(jsonObjReq, "headerRequest");
    }
    public void findRepCommittees(ArrayList<Representative> reps, ArrayList<Representative> sens, int i, Intent intent, final String msg) {
        if (i < reps.size()) {
            final Representative rep = reps.get(i);
            final int indexCopy = i;
            final ArrayList<Representative> repsPointer = reps;
            final ArrayList<Representative> sensPointer = sens;
            final Intent intentCopy = intent;
            String url1 = "https://api.propublica.org/congress/v1/members/" + rep.getId() + ".json";
            JsonObjectRequest jsonObjReq1 = new JsonObjectRequest(Request.Method.GET,
                    url1, null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            //Success Callback
                            try {
                                JSONArray results = response.getJSONArray("results");
                                JSONObject member = results.getJSONObject(0);
                                JSONObject role = member.getJSONArray("roles").getJSONObject(0);
                                JSONArray committees = role.getJSONArray("committees");
                                ArrayList<String> coms = new ArrayList<>();
                                for (int j = 0; j < committees.length(); j++) {
                                    JSONObject com = committees.getJSONObject(j);
                                    coms.add(com.getString("name"));
                                }
                                rep.setCommittees(coms);
                                findRepCommittees(repsPointer, sensPointer, indexCopy + 1, intentCopy, msg);
                            } catch (JSONException e) {
                                hideProgressBar();
                                TextView tv = findViewById(R.id.error);
                                tv.setText("Error: could not find data");
                                tv.setVisibility(View.VISIBLE);
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            //Failure Callback
                            hideProgressBar();
                            TextView tv = findViewById(R.id.error);
                            tv.setText("Error: invalid location");
                            tv.setVisibility(View.VISIBLE);
                        }
                    }) {
                /** Passing some request headers* */
                @Override
                public HashMap getHeaders() throws AuthFailureError {
                    HashMap headers = new HashMap();
                    headers.put("Content-Type", "application/json");
                    headers.put("X-API-Key", PUBLICA_API_KEY);
                    return headers;
                }
            };
            MainActivity.getInstance().addToRequestQueue(jsonObjReq1, "headerRequest");
        } else {
            findRepBills(reps, sens, 0, intent, msg);
        }
    }

    public void findRepBills(ArrayList<Representative> reps, ArrayList<Representative> sens, int i, Intent intent, final String msg) {
        if (i < reps.size()) {
            final Representative rep = reps.get(i);
            final int indexCopy = i;
            final ArrayList<Representative> repsPointer = reps;
            final ArrayList<Representative> sensPointer = sens;
            final Intent intentCopy = intent;
            String url2 = "https://api.propublica.org/congress/v1/members/" + rep.getId() + "/bills/introduced.json";
            JsonObjectRequest jsonObjReq2 = new JsonObjectRequest(Request.Method.GET,
                    url2, null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            //Success Callback
                            try {
                                JSONArray results = response.getJSONArray("results");
                                JSONObject member = results.getJSONObject(0);
                                JSONArray bills = member.getJSONArray("bills");
                                ArrayList<Bill> temp = new ArrayList<>();
                                for (int j = 0; j < min(bills.length(), 10); j++) {
                                    JSONObject bill = bills.getJSONObject(j);
                                    temp.add(new Bill(bill.getString("title"), bill.getString("introduced_date"), bill.getString("number"), bill.getString("congressdotgov_url")));
                                }
                                rep.setBills(temp);
                                findRepBills(repsPointer, sensPointer, indexCopy + 1, intentCopy, msg);
                            } catch (JSONException e) {
                                hideProgressBar();
                                TextView tv = findViewById(R.id.error);
                                tv.setText("Error: could not find data");
                                tv.setVisibility(View.VISIBLE);
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            //Failure Callback
                            hideProgressBar();
                            TextView tv = findViewById(R.id.error);
                            tv.setText("Error: invalid location");
                            tv.setVisibility(View.VISIBLE);
                        }
                    }) {
                /** Passing some request headers* */
                @Override
                public HashMap getHeaders() throws AuthFailureError {
                    HashMap headers = new HashMap();
                    headers.put("Content-Type", "application/json");
                    headers.put("X-API-Key", PUBLICA_API_KEY);
                    return headers;
                }
            };
            MainActivity.getInstance().addToRequestQueue(jsonObjReq2, "headerRequest");
        } else {
            findSenCommittees(reps, sens, 0, intent, msg);
        }
    }

    public void findSenCommittees(ArrayList<Representative> reps, ArrayList<Representative> sens, int i, Intent intent, final String msg) {
        final ArrayList<ArrayList<String>> senCommittees = new ArrayList<>();
        if (i < sens.size()) {
            final Representative sen = sens.get(i);
            final int indexCopy = i;
            final ArrayList<Representative> repsPointer = reps;
            final ArrayList<Representative> sensPointer = sens;
            final Intent intentCopy = intent;
            String url1 = "https://api.propublica.org/congress/v1/members/" + sen.getId() + ".json";
            JsonObjectRequest jsonObjReq1 = new JsonObjectRequest(Request.Method.GET,
                    url1, null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            //Success Callback
                            try {
                                JSONArray results = response.getJSONArray("results");
                                JSONObject member = results.getJSONObject(0);
                                JSONObject role = member.getJSONArray("roles").getJSONObject(0);
                                JSONArray committees = role.getJSONArray("committees");
                                ArrayList<String> coms = new ArrayList<>();
                                for (int j = 0; j < committees.length(); j++) {
                                    JSONObject com = committees.getJSONObject(j);
                                    coms.add(com.getString("name"));
                                }
                                sen.setCommittees(coms);
                                findSenCommittees(repsPointer, sensPointer, indexCopy + 1, intentCopy, msg);
                            } catch (JSONException e) {
                                hideProgressBar();
                                TextView tv = findViewById(R.id.error);
                                tv.setText("Error: could not find data");
                                tv.setVisibility(View.VISIBLE);
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            //Failure Callback
                            hideProgressBar();
                            TextView tv = findViewById(R.id.error);
                            tv.setText("Error: invalid location");
                            tv.setVisibility(View.VISIBLE);
                        }
                    }) {
                /**
                 * Passing some request headers*
                 */
                @Override
                public HashMap getHeaders() throws AuthFailureError {
                    HashMap headers = new HashMap();
                    headers.put("Content-Type", "application/json");
                    headers.put("X-API-Key", PUBLICA_API_KEY);
                    return headers;
                }
            };
            MainActivity.getInstance().addToRequestQueue(jsonObjReq1, "headerRequest");
        } else {
            findSenBills(reps, sens, 0, intent, msg);
        }
    }
    public void findSenBills(ArrayList<Representative> reps, ArrayList<Representative> sens, int i, Intent intent, final String msg) {
        if (i < sens.size()) {
            final Representative sen = sens.get(i);
            final int indexCopy = i;
            final ArrayList<Representative> repsPointer = reps;
            final ArrayList<Representative> sensPointer = sens;
            final Intent intentCopy = intent;
            String url2 = "https://api.propublica.org/congress/v1/members/" + sen.getId() + "/bills/cosponsored.json";

            JsonObjectRequest jsonObjReq2 = new JsonObjectRequest(Request.Method.GET,
                    url2, null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            //Success Callback
                            try {
                                JSONArray results = response.getJSONArray("results");
                                JSONObject member = results.getJSONObject(0);
                                JSONArray bills = member.getJSONArray("bills");
                                ArrayList<Bill> temp = new ArrayList<>();
                                for (int j = 0; j < min(bills.length(), 10); j++) {
                                    JSONObject bill = bills.getJSONObject(j);
                                    temp.add(new Bill(bill.getString("title"), bill.getString("introduced_date"), bill.getString("number"), bill.getString("congressdotgov_url")));
                                }
                                sen.setBills(temp);
                                findSenBills(repsPointer, sensPointer, indexCopy + 1, intentCopy, msg);
                            } catch (JSONException e) {
                                hideProgressBar();
                                TextView tv = findViewById(R.id.error);
                                tv.setText("Error: could not find data");
                                tv.setVisibility(View.VISIBLE);
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            //Failure Callback
                            hideProgressBar();
                            TextView tv = findViewById(R.id.error);
                            tv.setText("Error: invalid location");
                            tv.setVisibility(View.VISIBLE);
                        }
                    }) {
                /** Passing some request headers* */
                @Override
                public HashMap getHeaders() throws AuthFailureError {
                    HashMap headers = new HashMap();
                    headers.put("Content-Type", "application/json");
                    headers.put("X-API-Key", PUBLICA_API_KEY);
                    return headers;
                }
            };
            MainActivity.getInstance().addToRequestQueue(jsonObjReq2, "headerRequest");
        } else {
            // Adding the request to the queue along with a unique string tag
            loading.setVisibility(View.INVISIBLE);
            spinner1.setVisibility(View.INVISIBLE);
            TextView tv = findViewById(R.id.error);
            tv.setVisibility(View.INVISIBLE);

            intent.putExtra(SENATORS, sens);
            intent.putExtra(REPRESENTATIVES, reps);
            intent.putExtra(MESSAGE, msg);
            startActivity(intent);
        }
    }
    public void findRepZipCode(View view) {
        /** Gets the zipcode from the box, then checks to make sure it is a valid zip code, then */
        EditText editText = (EditText) findViewById(R.id.zipCode);
        String message = editText.getText().toString();
        if (!message.equals("") && zipcodes.contains(Integer.parseInt(message))) {
            String url = starter + "postal_code=" + message + "&fields=cd" + "&api_key=" + GEO_API_KEY;
            showProgressBar("Finding the\n representatives for zipcode " + message);
            findReps(view, url, -1);
        } else {
            findViewById(R.id.error).setVisibility(View.VISIBLE);
            TextView tv = findViewById(R.id.error);
            tv.setText("Please enter a valid 5-digit zipcode");
        }
    }

    public void findRepCurrLoc(View view) {
        final View view1 = view;
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            return;
        }else{
            Location location = mLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if(location != null) {
                showProgressBar("Finding the\n representatives for your current location");
                double lat = location.getLatitude();
                double longi = location.getLongitude();
                String url = "https://api.geocod.io/v1.3/reverse?q=" + lat + "," + longi + "&fields=cd" + "&api_key=" + GEO_API_KEY;
                findReps(view1, url, -1);
            } else {
                mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
            }
        }
    }

    public void findRepRandLoc(View view) {
        /** Generates an array with the congressional districts, then selects a random index*/
        showProgressBar("Finding the\n representatives for a random location");
        Random rand = new Random();
        int index = rand.nextInt(zipcodes.size());
        String url = starter + "postal_code=" + zipcodes.get(index) + "&fields=cd" + "&api_key=" + GEO_API_KEY;
        findReps(view, url, 1);
    }

    public static synchronized MainActivity getInstance() {
        return mInstance;
    }

    /*
    Create a getRequestQueue() method to return the instance of
    RequestQueue.This kind of implementation ensures that
    the variable is instatiated only once and the same
    instance is used throughout the application
    */
    public RequestQueue getRequestQueue() {
        if (requestQueue == null)
            requestQueue = Volley.newRequestQueue(getApplicationContext());
        return requestQueue;
    }

    /*
    public method to add the Request to the the single
    instance of RequestQueue created above.Setting a tag to every
    request helps in grouping them. Tags act as identifier
    for requests and can be used while cancelling them
    */
    public void addToRequestQueue(Request request, String tag) {
        request.setTag(tag);
        getRequestQueue().add(request);
    }

    /*
    Cancel all the requests matching with the given tag
    */
    public void cancelAllRequests(String tag) {
        getRequestQueue().cancelAll(tag);
    }
    public void onProviderDisabled(String arg0) {}
    public void onProviderEnabled(String arg0) {}
    public void onStatusChanged(String arg0, int arg1, Bundle arg2) {}
    public void onLocationChanged(Location location) {
        if (location != null) {
            mLocationManager.removeUpdates(this);
        }
    }
    public void showProgressBar(String s) {
        findViewById(R.id.button1).setVisibility(View.GONE);
        findViewById(R.id.button2).setVisibility(View.GONE);
        findViewById(R.id.button3).setVisibility(View.GONE);
        findViewById(R.id.zipCode).setVisibility(View.GONE);
        findViewById(R.id.error).setVisibility(View.GONE);
        loading.setVisibility(View.VISIBLE);
        spinner1.setVisibility(View.VISIBLE);
        loading.setText(s);
    }
    public void hideProgressBar() {
        findViewById(R.id.button1).setVisibility(View.VISIBLE);
        findViewById(R.id.button2).setVisibility(View.VISIBLE);
        findViewById(R.id.button3).setVisibility(View.VISIBLE);
        findViewById(R.id.zipCode).setVisibility(View.VISIBLE);
        loading.setVisibility(View.INVISIBLE);
        spinner1.setVisibility(View.INVISIBLE);
    }
}