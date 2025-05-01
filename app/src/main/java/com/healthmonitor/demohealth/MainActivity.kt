package com.healthmonitor.demohealth

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.healthmonitor.demohealth.Model.Order
import com.healthmonitor.demohealth.OrderHistoryActivity
import com.healthmonitor.demohealth.R

class MainActivity : AppCompatActivity() {
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor
    private val gson = Gson()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)

        sharedPreferences = getSharedPreferences("orders", MODE_PRIVATE)
        editor = sharedPreferences.edit()

        val typeSpinner = findViewById<Spinner>(R.id.typeSpinner)
        val quantityInput = findViewById<EditText>(R.id.quantityInput)
        val info1Input = findViewById<EditText>(R.id.info1Input)
        val info2Input = findViewById<EditText>(R.id.info2Input)
        val info3Input = findViewById<EditText>(R.id.info3Input)
        val submitButton = findViewById<Button>(R.id.submitButton)
        val viewOrdersButton = findViewById<Button>(R.id.viewOrdersButton)

        ArrayAdapter.createFromResource(
            this,
            R.array.food_types,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            typeSpinner.adapter = adapter
        }

        submitButton.setOnClickListener {
            val order = Order(
                type = typeSpinner.selectedItem.toString(),
                quantity = quantityInput.text.toString().toIntOrNull() ?: 1,
                info1 = info1Input.text.toString(),
                info2 = info2Input.text.toString(),
                info3 = info3Input.text.toString()
            )

            saveOrder(order)
            Toast.makeText(this, "Order Placed!", Toast.LENGTH_SHORT).show()
            clearForm()
        }

        viewOrdersButton.setOnClickListener {
            startActivity(Intent(this, OrderHistoryActivity::class.java))
        }
    }

    private fun saveOrder(order: Order) {
        val existingOrders = sharedPreferences.getString("order_list", "[]")
        val type = object : TypeToken<ArrayList<Order>>() {}.type
        val orders = gson.fromJson<ArrayList<Order>>(existingOrders, type)
        orders.add(order)
        editor.putString("order_list", gson.toJson(orders))
        editor.apply()
    }

    private fun clearForm() {
        findViewById<EditText>(R.id.quantityInput).text.clear()
        findViewById<EditText>(R.id.info1Input).text.clear()
        findViewById<EditText>(R.id.info2Input).text.clear()
        findViewById<EditText>(R.id.info3Input).text.clear()
    }
}