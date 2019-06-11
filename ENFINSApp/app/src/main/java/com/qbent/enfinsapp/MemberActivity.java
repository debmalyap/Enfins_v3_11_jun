package com.qbent.enfinsapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.EditText;

import com.qbent.enfinsapp.adapter.MemberRecyclerViewAdapter;
import com.qbent.enfinsapp.model.ApiRequest;
import com.qbent.enfinsapp.model.Member;
import com.qbent.enfinsapp.restapi.ApiCallback;
import com.qbent.enfinsapp.restapi.ApiHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MemberActivity extends MainActivity implements ApiCallback {
    EditText Name;
    EditText AadharNo;
    Button Search;
    private RecyclerView recyclerView;
    private List<Member> members = new ArrayList<Member>();
    Boolean isScrolling = false;
    int currentItems, totalItems, scrollOutItems;
    LinearLayoutManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        //inflate your activity layout here!
        @SuppressLint("InflateParams")
        View contentView = inflater.inflate(R.layout.activity_member, null, false);
        drawer.addView(contentView, 0);
        navigationView.setCheckedItem(R.id.nav_member);

        Name = (EditText)findViewById(R.id.searchMemberName);
        AadharNo = (EditText)findViewById(R.id.searchMemberAadhar);
        Search = (Button)findViewById(R.id.searchMember);

        Search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                members.clear();
                populateMembers(0);
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), CollectionPointDetailActivity.class));
            }
        });




        populateMembers(0);

    }

    @Override
    public void onApiRequestStart() throws IOException {

    }

    @Override
    public void onApiRequestComplete(String key, String result) throws IOException {
        try {
            if (key.equals("search-member")) {

                JSONObject resultJson = new JSONObject(result);
                JSONArray jsonArray = new JSONArray(resultJson.getString("result"));
                System.out.println(jsonArray.length());
                for(int i=0;i<jsonArray.length();i++)
                {
                    JSONObject jsonObject = (JSONObject)jsonArray.get(i);
                    Member member = new Member(
                        jsonObject.getString("code"),
                        jsonObject.getString("fullName"),
                        jsonObject.getLong("aadharNo"),
                        jsonObject.getString("dateOfDeath"),
                        jsonObject.getString("guardianName"),
                        jsonObject.getString("collectionPointName"),
                        jsonObject.getString("visitStatus")
                    );
                    members.add(member);
                }



                recyclerView = findViewById(R.id.recyclerViewMembers);
                manager = new LinearLayoutManager(MemberActivity.this);
                recyclerView.setLayoutManager(manager);
                MemberRecyclerViewAdapter mAdapter = new MemberRecyclerViewAdapter(members);
                recyclerView.setAdapter(mAdapter);


                recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                    @Override
                    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                        super.onScrollStateChanged(recyclerView, newState);
                        if(newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL)
                        {
                            isScrolling = true;
                        }
                    }

                    @Override
                    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                        super.onScrolled(recyclerView, dx, dy);
                        currentItems = manager.getChildCount();
                        totalItems = manager.getItemCount();
                        scrollOutItems = manager.findFirstVisibleItemPosition();

                        if(isScrolling && (currentItems + scrollOutItems == totalItems))
                        {
                            isScrolling = false;
                            populateMembers(totalItems);
                        }
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void populateMembers(int totalItems) {
        ApiRequest apiRequest = new ApiRequest("search-member");
        try{
            JSONObject jsonObject = new JSONObject();
            jsonObject.accumulate("fullName", Name.getText().toString());
            String test = AadharNo.getText().toString();
            if(TextUtils.isEmpty(test))
            {
                jsonObject.accumulate("aadharNo", null);
            }
            else
            {
                jsonObject.accumulate("aadharNo", Long.parseLong(AadharNo.getText().toString()));
            }
            jsonObject.accumulate("collectionPointName", "");
            jsonObject.accumulate("limit", 50);
            jsonObject.accumulate("order", "");
            jsonObject.accumulate("page", ((totalItems+50)/50));
            apiRequest.set_t(jsonObject);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        new ApiHandler.PostAsync(MemberActivity.this).execute(apiRequest);
    }
}
