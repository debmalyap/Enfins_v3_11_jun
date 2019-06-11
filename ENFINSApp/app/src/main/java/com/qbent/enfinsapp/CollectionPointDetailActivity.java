package com.qbent.enfinsapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.qbent.enfinsapp.global.AuthHelper;
import com.qbent.enfinsapp.model.ApiRequest;
import com.qbent.enfinsapp.model.Block;
import com.qbent.enfinsapp.model.CollectionPoint;
import com.qbent.enfinsapp.model.Country;
import com.qbent.enfinsapp.model.District;
import com.qbent.enfinsapp.model.GramPanchayet;
import com.qbent.enfinsapp.model.Municipality;
import com.qbent.enfinsapp.model.State;
import com.qbent.enfinsapp.model.Village;
import com.qbent.enfinsapp.model.Ward;
import com.qbent.enfinsapp.restapi.ApiCallback;
import com.qbent.enfinsapp.restapi.ApiHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CollectionPointDetailActivity extends MainActivity implements ApiCallback {

    //----Developed by Debmalya----//
    Button backButton;
    Button saveButton;

    //----Developed by Debmalya----//
    Spinner collectionDays;
    Spinner countriesSpinner;
    Spinner statesSpinner;
    Spinner distrcitSpinner;
    Spinner municipalitySpinner;
    Spinner wardSpinner;
    Spinner blockSpinner;
    Spinner gramPanchayetSpinner;
    Spinner villageSpinner;

    //----Developed by Debmalya----//
    private List<Country> countryLists;
    private List<State> statesLists;
    private List<District> districtList;
    private List<Municipality> municipalityList;
    private List<Ward> wardList;
    private List<Block> blockList;
    private List<GramPanchayet> gramPanchayetList;
    private List<Village> villageList;

    //----Developed by Debmalya----//
    private String stateId = " ";
    private String districtId = " ";
    private String municipalityId = " ";
    private String wardId = " ";
    private String blockId = " ";
    private String gramPanchayatId = " ";
    private String villageId = " ";

    //----Developed by Debmalya----//
    EditText userNameField;
    EditText formationDateField;
    EditText userAddressField;
    EditText userPincodeField;
    EditText userPlaceField;
    EditText userMobileField;


    //----Developed by Debmalya----//
    HashMap<String, String> spinnerCountriesMap = new HashMap<String, String>();
    HashMap<String, String> spinnerStatesMap = new HashMap<String, String>();
    HashMap<String, String> spinnerDistrictsMap = new HashMap<String, String>();
    HashMap<String, String> spinnerMunicipalitiesMap = new HashMap<String, String>();
    HashMap<String, String> spinnerWardsMap = new HashMap<String, String>();
    HashMap<String, String> spinnerBlocksMap = new HashMap<String, String>();
    HashMap<String, String> spinnerGramPanchayetMap = new HashMap<String, String>();
    HashMap<String, String> spinnerVillageMap = new HashMap<String, String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection_point_detail);

        collectionDays = (Spinner) findViewById(R.id.spinnerCollectionDay);
        countriesSpinner = (Spinner) findViewById(R.id.spinnerCountry);
        statesSpinner = (Spinner) findViewById(R.id.spinnerState);
        distrcitSpinner = (Spinner) findViewById(R.id.spinnerDistrict);
        municipalitySpinner = (Spinner) findViewById(R.id.spinnerMunicipality);
        wardSpinner = (Spinner) findViewById(R.id.spinnerWard);
        blockSpinner = (Spinner) findViewById(R.id.spinnerBlock);
        gramPanchayetSpinner = (Spinner) findViewById(R.id.spinnerGramPanchayat);
        villageSpinner = (Spinner) findViewById(R.id.spinnerVillage);

        countryLists = new ArrayList<Country>();
        statesLists = new ArrayList<State>();
        districtList = new ArrayList<District>();
        municipalityList = new ArrayList<Municipality>();
        wardList = new ArrayList<Ward>();
        blockList = new ArrayList<Block>();
        gramPanchayetList = new ArrayList<GramPanchayet>();
        villageList = new ArrayList<Village>();

        userNameField = findViewById(R.id.editTextName);
        formationDateField = findViewById(R.id.editTextFormationDate);
        userAddressField = findViewById(R.id.editTextAddress);
        userPincodeField = findViewById(R.id.editTextPincode);
        userPlaceField = findViewById(R.id.editTextPlace);
        userMobileField = findViewById(R.id.editTextMobile);

        backButton = (Button) findViewById(R.id.backButton);
        saveButton = (Button) findViewById(R.id.submitButton);

        //----Developed by Debmalya----//
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                ApiRequest apiRequest = new ApiRequest("collection-point/add");
                try{
                    JSONObject jsonObject = new JSONObject();

                    jsonObject.accumulate("name",userNameField.getText().toString());
                    jsonObject.accumulate("formationDate",formationDateField.getText().toString());
                    jsonObject.accumulate("collectionDay",collectionDays.getSelectedItemPosition());
                    jsonObject.accumulate("address",userAddressField.getText().toString());
                    jsonObject.accumulate("pincode",userPincodeField.getText().toString());
                    jsonObject.accumulate("place",userPlaceField.getText().toString());
                    jsonObject.accumulate("mobileNo",userMobileField.getText().toString());
                    jsonObject.accumulate("countryId",(new ArrayList<String>(spinnerCountriesMap.keySet())).get(new ArrayList<String>(spinnerCountriesMap.values()).indexOf(countriesSpinner.getSelectedItem().toString())).toString());
                    jsonObject.accumulate("stateId",(new ArrayList<String>(spinnerStatesMap.keySet())).get(new ArrayList<String>(spinnerStatesMap.values()).indexOf(statesSpinner.getSelectedItem().toString())).toString());
                    jsonObject.accumulate("districtId",(new ArrayList<String>(spinnerDistrictsMap.keySet())).get(new ArrayList<String>(spinnerDistrictsMap.values()).indexOf(distrcitSpinner.getSelectedItem().toString())).toString());
                    jsonObject.accumulate("municipalityId",(new ArrayList<String>(spinnerMunicipalitiesMap.keySet())).get(new ArrayList<String>(spinnerMunicipalitiesMap.values()).indexOf(municipalitySpinner.getSelectedItem().toString())).toString());
                    jsonObject.accumulate("wardId",(new ArrayList<String>(spinnerWardsMap.keySet())).get(new ArrayList<String>(spinnerWardsMap.values()).indexOf(wardSpinner.getSelectedItem().toString())).toString());
                    jsonObject.accumulate("blockId",(new ArrayList<String>(spinnerBlocksMap.keySet())).get(new ArrayList<String>(spinnerBlocksMap.values()).indexOf(blockSpinner.getSelectedItem().toString())).toString());
                    jsonObject.accumulate("gramPanchayatId",(new ArrayList<String>(spinnerGramPanchayetMap.keySet())).get(new ArrayList<String>(spinnerGramPanchayetMap.values()).indexOf(gramPanchayetSpinner.getSelectedItem().toString())).toString());
                    jsonObject.accumulate("villageId",(new ArrayList<String>(spinnerVillageMap.keySet())).get(new ArrayList<String>(spinnerVillageMap.values()).indexOf(villageSpinner.getSelectedItem().toString())).toString());


                    apiRequest.set_t(jsonObject);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
                new ApiHandler.PostAsync(CollectionPointDetailActivity.this).execute(apiRequest);

                //populateCollectionPoints(collectionPointId);
            }
        });

        //-----Developer by Debmalya-------//
        ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(this, R.array.collection_days, android.R.layout.simple_spinner_item);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        collectionDays.setAdapter(arrayAdapter);

        countriesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                String name = countriesSpinner.getSelectedItem().toString();
                List<String> indexes = new ArrayList<String>(spinnerCountriesMap.values());
                int a = indexes.indexOf(name);
                stateId = (new ArrayList<String>(spinnerCountriesMap.keySet())).get(indexes.indexOf(name));
                populateStates(stateId);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                //Yet to be completed//
            }

        });

        //-----Developed by Debmalya-----//
        statesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                String name = statesSpinner.getSelectedItem().toString();
                List<String> indexes = new ArrayList<String>(spinnerStatesMap.values());
                int a = indexes.indexOf(name);
                districtId = (new ArrayList<String>(spinnerStatesMap.keySet())).get(indexes.indexOf(name));
                populateDistricts(districtId);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                //Yet to be completed//
            }
        });

        //-----Developed by Debmalya-----//
        distrcitSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                String name = distrcitSpinner.getSelectedItem().toString();
                List<String> indexes = new ArrayList<String>(spinnerDistrictsMap.values());
                int a = indexes.indexOf(name);
                municipalityId = (new ArrayList<String>(spinnerDistrictsMap.keySet())).get(indexes.indexOf(name));
                populateMunicipalities(municipalityId);

                String name2 = distrcitSpinner.getSelectedItem().toString();
                List<String> indexes2 = new ArrayList<String>(spinnerDistrictsMap.values());
                int b = indexes.indexOf(name2);
                blockId = (new ArrayList<String>(spinnerDistrictsMap.keySet())).get(indexes.indexOf(name));
                populateBlocks(blockId);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                //Yet to be completed//
            }
        });

        //-----Developed by Debmalya-----//
        municipalitySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                String name = municipalitySpinner.getSelectedItem().toString();
                List<String> indexes = new ArrayList<String>(spinnerMunicipalitiesMap.values());
                int a = indexes.indexOf(name);
                wardId = (new ArrayList<String>(spinnerMunicipalitiesMap.keySet())).get(indexes.indexOf(name));
                populateWards(wardId);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                //Yet to be completed//
            }
        });

        //-----Developed by Debmalya-----//
        blockSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                String name = blockSpinner.getSelectedItem().toString();
                List<String> indexes = new ArrayList<String>(spinnerBlocksMap.values());
                int a = indexes.indexOf(name);
                gramPanchayatId = (new ArrayList<String>(spinnerBlocksMap.keySet())).get(indexes.indexOf(name));
                populateGramPanchayets(gramPanchayatId);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                //Yet to be completed//
            }
        });

        //-----Developed by Debmalya-----//
        gramPanchayetSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                String name = gramPanchayetSpinner.getSelectedItem().toString();
                List<String> indexes = new ArrayList<String>(spinnerGramPanchayetMap.values());
                int a = indexes.indexOf(name);
                villageId = (new ArrayList<String>(spinnerGramPanchayetMap.keySet())).get(indexes.indexOf(name));
                populateVillages(villageId);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                //Yet to be completed//
            }
        });

        populateCountries();

    }

    @Override
    public void onApiRequestStart() throws IOException {

    }

    //----Developed by Debmalya----//
    @Override
    public void onApiRequestComplete(String key, String result) throws IOException
    {
        if (key.equals("countries"))
        {
            setCountriesAdapter(result);
        }
        else if(key.contains("states-by-country"))
        {
            setStatesAdapter(result);
        }

        else if(key.contains("districts-by-state"))
        {
            setDistrictsAdapter(result);
        }

        else if(key.contains("municipalities-by-district"))
        {
            setMunicipalitiesAdapter(result);
        }

        else if(key.contains("wards-by-municipality"))
        {
            setWardsAdapter(result);
        }

        else if(key.contains("blocks-by-distric"))
        {
            setBlocksAdapter(result);
        }

        else if(key.contains("panchayats-by-block"))
        {
            setGramPanchayetsAdapter(result);
        }

        else if(key.contains("villages-by-panchayat"))
        {
            setVillagesAdapter(result);
        }

        else if(key.contains("collection-point/add"))
        {
            setCollectionPointsAdapter(result);
        }
    }



    private void populateCountries() {
        new ApiHandler.GetAsync(CollectionPointDetailActivity.this).execute("countries");
    }

    private void populateStates(String stateId) {
        new ApiHandler.GetAsync(CollectionPointDetailActivity.this).execute("states-by-country/{"+ stateId +"}");
    }

    //-----Developed by Debmalya-----//
    private void populateDistricts(String districtId) {
        new ApiHandler.GetAsync(CollectionPointDetailActivity.this).execute("districts-by-state/{"+ districtId +"}");
    }

    //-----Developed by Debmalya-----//
    private void populateMunicipalities(String municipalityId) {
        new ApiHandler.GetAsync(CollectionPointDetailActivity.this).execute("municipalities-by-district/{"+ municipalityId +"}");
    }

    //-----Developed by Debmalya-----//
    private void populateWards(String wardId) {
        new ApiHandler.GetAsync(CollectionPointDetailActivity.this).execute("wards-by-municipality/{"+ wardId +"}");
    }

    //-----Developed by Debmalya-----//
    private void populateBlocks(String blockId) {
        new ApiHandler.GetAsync(CollectionPointDetailActivity.this).execute("blocks-by-distric/{"+ blockId +"}");
    }

    //-----Developed by Debmalya-----//
    private void populateGramPanchayets(String gramPanchayatId) {
        new ApiHandler.GetAsync(CollectionPointDetailActivity.this).execute("panchayats-by-block/{"+ gramPanchayatId +"}");
    }

    //-----Developed by Debmalya-----//
    private void populateVillages(String villageId) {
        new ApiHandler.GetAsync(CollectionPointDetailActivity.this).execute("villages-by-panchayat/{"+ villageId +"}");
    }


    private void setCountriesAdapter(String result) {
        try {
            JSONArray jsonArray = new JSONArray(result);
            for (int i = 0; i < jsonArray.length(); i++) {
                //JSONObject jsonObject = (JSONObject)jsonArray.get(i);
                JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                Country country = new Country(
                        jsonObject.getString("id"),
                        jsonObject.getString("name")
                );
                countryLists.add(country);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        String[] spinnerCountriesArray = new String[countryLists.size()];
        for (int i = 0; i < countryLists.size(); i++)
        {
            spinnerCountriesMap.put(countryLists.get(i).getId(),countryLists.get(i).getName());
            spinnerCountriesArray[i] = countryLists.get(i).getName();
        }



        ArrayAdapter<String> adapter = new ArrayAdapter<String>(CollectionPointDetailActivity.this, android.R.layout.simple_spinner_item,spinnerCountriesArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        countriesSpinner.setAdapter(adapter);

    }

    private void setStatesAdapter(String result)
    {
        try {
            statesLists = new ArrayList<State>();
            JSONArray jsonArray = new JSONArray(result);
            for (int i = 0; i < jsonArray.length(); i++) {
                //JSONObject jsonObject = (JSONObject)jsonArray.get(i);
                JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                State state = new State(
                        jsonObject.getString("id"),
                        jsonObject.getString("name")
                );
                statesLists.add(state);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        String[] spinnerStatesArray = new String[statesLists.size()];
        for (int i = 0; i < statesLists.size(); i++)
        {
            spinnerStatesMap.put(statesLists.get(i).getId(),statesLists.get(i).getName());
            spinnerStatesArray[i] = statesLists.get(i).getName();
        }



        ArrayAdapter<String> adapter = new ArrayAdapter<String>(CollectionPointDetailActivity.this, android.R.layout.simple_spinner_item,spinnerStatesArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        statesSpinner.setAdapter(adapter);
    }

    //-----Developed by Debmalya-----//
    private void setDistrictsAdapter(String result)
    {
        try {
            districtList = new ArrayList<District>();
            JSONArray jsonArray = new JSONArray(result);
            for (int i = 0; i < jsonArray.length(); i++) {
                //JSONObject jsonObject = (JSONObject)jsonArray.get(i);
                JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                District district = new District(
                        jsonObject.getString("id"),
                        jsonObject.getString("name")
                );
                districtList.add(district);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        String[] spinnerDistrictsArray = new String[districtList.size()];
        for (int i = 0; i < districtList.size(); i++)
        {
            spinnerDistrictsMap.put(districtList.get(i).getId(),districtList.get(i).getName());
            spinnerDistrictsArray[i] = districtList.get(i).getName();
        }



        ArrayAdapter<String> adapter = new ArrayAdapter<String>(CollectionPointDetailActivity.this, android.R.layout.simple_spinner_item,spinnerDistrictsArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        distrcitSpinner.setAdapter(adapter);
    }

    //-----Developed by Debmalya-----//
    private void setMunicipalitiesAdapter(String result)
    {
        try {
            municipalityList = new ArrayList<Municipality>();
            JSONArray jsonArray = new JSONArray(result);
            for (int i = 0; i < jsonArray.length(); i++) {
                //JSONObject jsonObject = (JSONObject)jsonArray.get(i);
                JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                Municipality municipality = new Municipality(
                        jsonObject.getString("id"),
                        jsonObject.getString("name")
                );
                municipalityList.add(municipality);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        String[] spinnerMunicipalitiesArray = new String[municipalityList.size()];
        for (int i = 0; i < municipalityList.size(); i++)
        {
            spinnerMunicipalitiesMap.put(municipalityList.get(i).getId(),municipalityList.get(i).getName());
            spinnerMunicipalitiesArray[i] = municipalityList.get(i).getName();
        }



        ArrayAdapter<String> adapter = new ArrayAdapter<String>(CollectionPointDetailActivity.this, android.R.layout.simple_spinner_item,spinnerMunicipalitiesArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        municipalitySpinner.setAdapter(adapter);
    }

    //-----Developed by Debmalya-----//
    private void setWardsAdapter(String result)
    {
        try {
            wardList = new ArrayList<Ward>();
            JSONArray jsonArray = new JSONArray(result);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                Ward ward = new Ward(
                        jsonObject.getString("id"),
                        jsonObject.getString("name")
                );
                wardList.add(ward);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        String[] spinnerWardsArray = new String[wardList.size()];
        for (int i = 0; i < wardList.size(); i++)
        {
            spinnerWardsMap.put(wardList.get(i).getId(),wardList.get(i).getName());
            spinnerWardsArray[i] = wardList.get(i).getName();
        }



        ArrayAdapter<String> adapter = new ArrayAdapter<String>(CollectionPointDetailActivity.this, android.R.layout.simple_spinner_item,spinnerWardsArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        wardSpinner.setAdapter(adapter);
    }

    //-----Developed by Debmalya-----//
    private void setBlocksAdapter(String result)
    {
        try {
            blockList = new ArrayList<Block>();
            JSONArray jsonArray = new JSONArray(result);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                Block block = new Block(
                        jsonObject.getString("id"),
                        jsonObject.getString("name")
                );
                blockList.add(block);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        String[] spinnerBlocksArray = new String[blockList.size()];
        for (int i = 0; i < blockList.size(); i++)
        {
            spinnerBlocksMap.put(blockList.get(i).getId(),blockList.get(i).getName());
            spinnerBlocksArray[i] = blockList.get(i).getName();
        }



        ArrayAdapter<String> adapter = new ArrayAdapter<String>(CollectionPointDetailActivity.this, android.R.layout.simple_spinner_item,spinnerBlocksArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        blockSpinner.setAdapter(adapter);
    }

    //-----Developed by Debmalya-----//
    private void setGramPanchayetsAdapter(String result)
    {
        try {
            gramPanchayetList = new ArrayList<GramPanchayet>();
            JSONArray jsonArray = new JSONArray(result);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                GramPanchayet gramPanchayet = new GramPanchayet(
                        jsonObject.getString("id"),
                        jsonObject.getString("name")
                );
                gramPanchayetList.add(gramPanchayet);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        String[] spinnerGramPanchayetsArray = new String[gramPanchayetList.size()];
        for (int i = 0; i < gramPanchayetList.size(); i++)
        {
            spinnerGramPanchayetMap.put(gramPanchayetList.get(i).getId(),gramPanchayetList.get(i).getName());
            spinnerGramPanchayetsArray[i] = gramPanchayetList.get(i).getName();
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(CollectionPointDetailActivity.this, android.R.layout.simple_spinner_item,spinnerGramPanchayetsArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        gramPanchayetSpinner.setAdapter(adapter);
    }

    //-----Developed by Debmalya-----//
    private void setVillagesAdapter(String result)
    {
        try {
            villageList = new ArrayList<Village>();
            JSONArray jsonArray = new JSONArray(result);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                Village village = new Village(
                        jsonObject.getString("id"),
                        jsonObject.getString("name")
                );
                villageList.add(village);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        String[] spinnerVillagesArray = new String[villageList.size()];
        for (int i = 0; i < villageList.size(); i++)
        {
            spinnerVillageMap.put(villageList.get(i).getId(),villageList.get(i).getName());
            spinnerVillagesArray[i] = villageList.get(i).getName();
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(CollectionPointDetailActivity.this, android.R.layout.simple_spinner_item,spinnerVillagesArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        villageSpinner.setAdapter(adapter);
    }

    //----Developed by Debmalya----//
    private void setCollectionPointsAdapter(String result)
    {
        Toast.makeText(getApplicationContext(),"Saved Successfully", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getApplicationContext(),CollectionPointListActivity.class);
        startActivity(intent);
    }

    //----Developed by Debmalya----//
    public void goBack(View view)
    {
        Intent intent = new Intent(getApplicationContext(),CollectionPointListActivity.class);
        startActivity(intent);
    }
}
