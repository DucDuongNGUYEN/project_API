package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class ClientsAdapter extends RecyclerView.Adapter<ClientsAdapter.ViewHolder> {
    private List<Client> clients;

    public ClientsAdapter(List<Client> clients) {
        this.clients = clients;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_client, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Client client = clients.get(position);
        holder.nameView.setText(client.getNom());
        holder.initialsView.setText(getInitials(client.getNom(), client));
    }

    private String getInitials(String fullName, Client client) {
        String[] nameParts = client.getNom().split(" ");
        StringBuilder initials = new StringBuilder();

        for (String part : nameParts) {
            if (!part.trim().isEmpty()) {
                initials.append(part.trim().substring(0, 1).toUpperCase());
            }
        }
        return initials.toString();
    }

    @Override
    public int getItemCount() {
        return clients.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView initialsView, nameView;

        public ViewHolder(View view) {
            super(view);
            initialsView = view.findViewById(R.id.initialsView);
            nameView = view.findViewById(R.id.nameView);
        }
    }
}
