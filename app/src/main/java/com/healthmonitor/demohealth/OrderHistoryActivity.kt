package com.healthmonitor.demohealth

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.healthmonitor.demohealth.Adapter.OrderAdapter
import com.healthmonitor.demohealth.Model.Order

class OrderHistoryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_history)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val sharedPreferences = getSharedPreferences("orders", MODE_PRIVATE)
        val gson = Gson()
        val json = sharedPreferences.getString("order_list", "[]")
        val type = object : TypeToken<ArrayList<Order>>() {}.type
        val orders = gson.fromJson<ArrayList<Order>>(json, type)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = OrderAdapter(orders.reversed())
    }
}