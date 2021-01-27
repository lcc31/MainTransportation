package com.example.maintransportation.client;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ClientJsonParser {

    public static final String TAG_NUME = "nume";
    public static final String TAG_LOC_PLECARE = "locPlecare";
    public static final String TAG_ZI_PLECARE = "ziPlecare";
    public static final String TAG_ORA_PLECARE = "oraPlecare";

    public static List<Client> fromJson(String json) {
        try {
            JSONArray array = new JSONArray(json);
            return readClients(array);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    private static List<Client> readClients(JSONArray array) throws JSONException {
        List<Client> Clients = new ArrayList<>();
        for (int i = 0; i < array.length(); i++) {
            Client Client = readClient(array.getJSONObject(i));
            Clients.add(Client);
        }
        return Clients;
    }

    private static Client readClient(JSONObject object) throws JSONException {
        String numeClient = object.getString(TAG_NUME);
        String locPlecare = object.getString(TAG_LOC_PLECARE);
        String ziPlecare = object.getString(TAG_ZI_PLECARE);
        String oraPlecare = object.getString(TAG_ORA_PLECARE);

        return new Client(numeClient, locPlecare, ziPlecare, oraPlecare);
    }
}
