package com.example.trial2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ReceiveItemProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.receiveitem);

        String cart_id = getIntent().getStringExtra("receive_id");
        String supplier_name = getIntent().getStringExtra("rcv_supplier_name");
        String prod_name = getIntent().getStringExtra("rcv_prod");
        String prod_stock = getIntent().getStringExtra("rcv_stockQty");
        String prod_price = getIntent().getStringExtra("rcv_price");

        // Find TextViews in your layout
        TextView id = findViewById(R.id.receive_id);
        TextView supplier = findViewById(R.id.receive_supplyname);
        TextView product = findViewById(R.id.receive_prod_name);
        TextView stocks = findViewById(R.id.receive_quantity);
        TextView price = findViewById(R.id.listPrice_prof);

        // Set data to TextViews with null checks
        if (id != null && cart_id != null) {
            id.setText(cart_id);
        }

        if (supplier != null && supplier_name != null) {
            supplier.setText(supplier_name);
        }

        if (product != null && prod_name != null) {
            product.setText(prod_name);
        }

        if (stocks != null && prod_stock != null) {
            stocks.setText(prod_stock);
        }

        if (price != null && prod_price != null) {
            price.setText(prod_price);
        }

        // Button and its onClickListener
        Button receiveItemButton = findViewById(R.id.btn_release_item);
        receiveItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Fetch all data needed for release
                String rls_supplier_name = getIntent().getStringExtra("rcv_supplier_name");
                String rls_prod_name = getIntent().getStringExtra("rcv_prod");
                String rls_prod_stock = getIntent().getStringExtra("rcv_stockQty");
                String rls_prod_price = getIntent().getStringExtra("rcv_price");

                // Create an Intent to launch the TransactionHistory activity
                Intent displayDataIntent = new Intent(ReceiveItemProfile.this, TransactionHistory.class);

                // Pass data to the new activity
                displayDataIntent.putExtra("receive_supplyname", rls_supplier_name);
                displayDataIntent.putExtra("receive_prod_name", rls_prod_name);
                displayDataIntent.putExtra("receive_quantity", rls_prod_stock);
                displayDataIntent.putExtra("listPrice_prof", rls_prod_price);

                // Start the new activity
                startActivity(displayDataIntent);
            }

        });
    }
}
