package com.healthmonitor.demohealth.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.healthmonitor.demohealth.Model.Order
import com.healthmonitor.demohealth.R
import java.text.SimpleDateFormat

class OrderAdapter(private val orders: List<Order>) : RecyclerView.Adapter<OrderAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val type: TextView = view.findViewById(R.id.orderType)
        val details: TextView = view.findViewById(R.id.orderDetails)
        val date: TextView = view.findViewById(R.id.orderDate)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.order_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val order = orders[position]
        val sdf = SimpleDateFormat("dd MMM yyyy HH:mm")

        holder.type.text = order.type
        holder.date.text = sdf.format(order.timestamp)
        holder.details.text = "Quantity: ${order.quantity}\n" +
                "Instructions: ${order.info1}\n" +
                "Cooking: ${order.info2}\n" +
                "Allergies: ${order.info3}"
    }

    override fun getItemCount() = orders.size
}