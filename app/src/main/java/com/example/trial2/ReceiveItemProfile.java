package com.example.trial2;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ReceiveItemProfile extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState){
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
    }

}
