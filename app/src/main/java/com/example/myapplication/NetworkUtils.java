package com.example.myapplication;
import android.util.Base64;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.*;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NetworkUtils {
    private static final String BASE_URL = "https://evaluation-technique.lundimatin.biz/api";
    private static final MediaType JSON = MediaType.get("application/json; charset=utf-8");
    private OkHttpClient client = new OkHttpClient();

    // Method to perform authentication
    public void authenticateUser(String username, String password, Callback callback) {
        JSONObject json = new JSONObject();
        try {
            json.put("username", username);
            json.put("password", password);
            json.put("code_application", "webservice_externe");
            json.put("code_version", "1");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        RequestBody body = RequestBody.create(json.toString(), JSON);
        Request request = new Request.Builder()
                .url(BASE_URL + "/auth")
                .post(body)
                .build();

        client.newCall(request).enqueue(callback);
    }

    public void getClients(String token, Callback callback) {
        Request request = new Request.Builder()
                .url(BASE_URL + "/clients")
                .addHeader("Authorization", "Basic " + getBasicToken(token))
                .get()
                .build();

        client.newCall(request).enqueue(callback);
    }

    public void getClientDetails(String token, String clientId, Callback callback) {
        Request request = new Request.Builder()
                .url(BASE_URL + "/clients/" + clientId)
                .addHeader("Authorization", "Basic " + getBasicToken(token))
                .get()
                .build();

        client.newCall(request).enqueue(callback);
    }

    public String getBasicToken(String token) {
        return Base64.encodeToString(token.getBytes(), Base64.DEFAULT);
    }

    public List<Client> parseClientsFromJson(String jsonString) {
        List<Client> clients = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(jsonString);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                int id = jsonObject.getInt("id");
                String nom = jsonObject.getString("nom");
                String email = jsonObject.optString("email", "");
                String tel = jsonObject.optString("tel", "");
                String adresse = jsonObject.optString("adresse", "");
                String code_postal = jsonObject.optString("code_postal", "");
                String ville = jsonObject.optString("ville", "");
                String date_creation = jsonObject.optString("date_creation", "");

                Client client = new Client(id, nom, date_creation, email, tel, adresse, code_postal, ville);
                clients.add(client);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return clients;
    }

}
