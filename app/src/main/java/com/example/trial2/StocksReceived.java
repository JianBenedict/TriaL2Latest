package com.example.trial2;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class StocksReceived extends AppCompatActivity {

    RecyclerView recyclerView;
    DBHelperStock DB;
    ArrayList<String> receive_id, supplier_name, prod_name, order_quantity, price;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stocks_received);

        recyclerView = findViewById(R.id.recycle_stock);
        DB = new DBHelperStock(this);

        receive_id = new ArrayList<>();
        supplier_name = new ArrayList<>();
        prod_name = new ArrayList<>();
        order_quantity = new ArrayList<>();
        price = new ArrayList<>();

        showData();

        customAdapter = new CustomAdapter(this, receive_id, supplier_name, prod_name, order_quantity, price);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Set the click listener for the adapter
        customAdapter.setOnItemClickListener(new CustomAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Cursor cursor = DB.readAllDatastock();
                if (cursor.moveToPosition(position)) {
                    String receive_id = cursor.getString(0);
                    String receive_supplier_name = cursor.getString(1);
                    String receive_prod = cursor.getString(2);
                    String receive_stockQty = cursor.getString(3);
                    String receive_price = cursor.getString(4);

                    Intent intent = new Intent(StocksReceived.this, ReceiveItemProfile.class);

                    intent.putExtra("receive_id",receive_id);
                    intent.putExtra("rcv_supplier_name",receive_supplier_name );
                    intent.putExtra("rcv_prod", receive_prod);
                    intent.putExtra("rcv_stockQty", receive_stockQty);
                    intent.putExtra("rcv_price", receive_price);

                    startActivity(intent);
                }
            }
        });


        FloatingActionButton fab = findViewById(R.id.fab_btn_rcv);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openCartStock();
            }
        });
    }

    private void openCartStock() {
        Intent intent = new Intent(StocksReceived.this, CartStock.class);
        startActivity(intent);
    }

    void showData() {
        Cursor cursor = DB.readAllDatastock();
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                receive_id.add(cursor.getString(0));
                supplier_name.add(cursor.getString(1));
                prod_name.add(cursor.getString(2));
                order_quantity.add(cursor.getString(3));
                price.add(cursor.getString(4));
            }
        }
    }

    public static class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

        private Context context;
        private ArrayList<String> receive_id, supplier_name, prod_name, order_quantity, price;
        private OnItemClickListener itemClickListener;

        CustomAdapter(Context context, ArrayList<String> receive_id, ArrayList<String> supplier_name, ArrayList<String> prod_name, ArrayList<String> order_quantity, ArrayList<String> price) {
            this.context = context;
            this.receive_id = receive_id;
            this.supplier_name = supplier_name;
            this.prod_name = prod_name;
            this.order_quantity = order_quantity;
            this.price = price;
        }

        public interface OnItemClickListener {
            void onItemClick(int position);
        }

        public void setOnItemClickListener(OnItemClickListener listener) {
            this.itemClickListener = listener;
        }

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(context);
            View view = inflater.inflate(R.layout.receive_stocks_layout, parent, false);
            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            holder.rcv_supplier_name.setText(String.valueOf(supplier_name.get(position)));
            holder.rcv_prod.setText(String.valueOf(prod_name.get(position)));
            holder.rcv_stockQty.setText(String.valueOf(order_quantity.get(position)));
            holder.rcv_price.setText(String.valueOf(price.get(position)));
        }

        @Override
        public int getItemCount() {
            return receive_id.size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {
            TextView rcv_prod, rcv_supplier_name, rcv_stockQty, rcv_price;

            public MyViewHolder(@NonNull View itemView) {
                super(itemView);
                rcv_supplier_name = itemView.findViewById(R.id.rcv_supplier_name);
                rcv_prod = itemView.findViewById(R.id.rcv_prod);
                rcv_stockQty = itemView.findViewById(R.id.rcv_stockQty);
                rcv_price = itemView.findViewById(R.id.rcv_price);

                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (itemClickListener != null) {
                            int position = getAdapterPosition();
                            if (position != RecyclerView.NO_POSITION) {
                                itemClickListener.onItemClick(position);
                            }
                        }
                    }
                });
            }
        }
    }
}