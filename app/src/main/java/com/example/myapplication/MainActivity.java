package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    SearchView searchView;
    private RecyclerView recyclerView;
    private ClientsAdapter adapter;
    private NetworkUtils networkUtils;
    private List<Client> clientsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchView = findViewById(R.id.searchView);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ClientsAdapter(clientsList);
        recyclerView.setAdapter(adapter);
        networkUtils = new NetworkUtils();

        // Authenticate and fetch the list of clients
        networkUtils.authenticateUser("test_api", "api123456", new okhttp3.Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.e("MainActivity", "Error during authentication", e);
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                if (!response.isSuccessful()) {
                    Log.e("MainActivity", "Unexpected code " + response);
                } else {
                    // Parse the response to get the token and then fetch clients
                    String responseBody = response.body().string();
                    String token = networkUtils.getBasicToken(responseBody);

                    // Fetch clients with the token
                    networkUtils.getClients(token, new Callback() {
                        @Override
                        public void onFailure(@NotNull Call call, @NotNull IOException e) {
                            Log.e("MainActivity", "Error fetching clients", e);
                        }

                        @Override
                        public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                            if (!response.isSuccessful()) {
                                Log.e("MainActivity", "Unexpected code " + response);
                            } else {
                                try {
                                    // Assuming the response body contains a list of clients in JSON format
                                    String responseBody = response.body().string();
                                    // Parse the JSON response to a list of Client objects
                                    List<Client> clients = networkUtils.parseClientsFromJson(responseBody);
                                    // Update the list of clients in the adapter
                                    runOnUiThread(() -> {
                                        clientsList.clear();
                                        clientsList.addAll(clients);
                                        adapter.notifyDataSetChanged();
                                    });
                                } catch (IOException e) {
                                    Log.e("MainActivity", "Error parsing response", e);
                                }
                            }
                        }
                    });
                }
            }
        });
    }
}
