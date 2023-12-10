package com.example.trial2;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;



public class TransactionHistory extends AppCompatActivity {
    private RecyclerView recyclerView;
    private TransactionAdapter adapter;
    private List<TransactionItem> transactionList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.transaction_recycle);

        // Retrieve data from Intent
        String receivedSupplierName = getIntent().getStringExtra("receive_supplyname");
        String receivedProductName = getIntent().getStringExtra("receive_prod_name");
        String receivedQuantity = getIntent().getStringExtra("receive_quantity");
        String receivedPrice = getIntent().getStringExtra("listPrice_prof");

        // Assuming you have a RecyclerView with the ID "recyclerView" in your layout
        recyclerView = findViewById(R.id.recyclerView);

        // Initialize the list of transactions
        transactionList = new ArrayList<>();
        transactionList.add(new TransactionItem("Supplier", receivedSupplierName));
        transactionList.add(new TransactionItem("Product", receivedProductName));
        transactionList.add(new TransactionItem("Quantity", receivedQuantity));
        transactionList.add(new TransactionItem("Price", receivedPrice));

        // Set up the RecyclerView
        adapter = new TransactionAdapter(transactionList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    private void addTransaction(String value, String label) {
        TransactionItem newTransaction = new TransactionItem(label, value);
        transactionList.add(newTransaction);
        adapter.notifyDataSetChanged();
    }
}