package com.example.agecalculator

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import java.util.Calendar
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.textfield.TextInputEditText
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Locale


class MainActivity : AppCompatActivity() {

    private lateinit var editTextBirthdate: TextInputEditText
    private lateinit var buttonCalculateAge: Button
    private lateinit var textViewResult: TextView
    private lateinit var userGreeting: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTextBirthdate = findViewById(R.id.editTextBirthdate)
        buttonCalculateAge = findViewById(R.id.buttonCalculateAge)
        textViewResult = findViewById(R.id.textViewResult)
        userGreeting = findViewById(R.id.user_greeting)

        editTextBirthdate.setOnClickListener {
            showDatePicker()
        }

        buttonCalculateAge.setOnClickListener {
            calculateAge()
        }
    }

    private fun showDatePicker() {
        val builder = MaterialDatePicker.Builder.datePicker()
        val picker = builder.build()

        picker.addOnPositiveButtonClickListener { selectedDate ->
            val calendar = Calendar.getInstance()
            calendar.timeInMillis = selectedDate
            val formattedDate = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(calendar.time)
            editTextBirthdate.setText(formattedDate)
            Log.d("DatePicker", "Selected Date: $formattedDate")
        }

        picker.show(supportFragmentManager, picker.toString())
    }

    private fun calculateAge() {
        val birthdateString = editTextBirthdate.text.toString()
        val userGreeting = findViewById<TextView>(R.id.user_greeting)

        if (birthdateString.isNotEmpty()) {
            try {
                val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                val birthdate = sdf.parse(birthdateString)
                val currentDate = Calendar.getInstance().time

                val diffInMilliseconds = currentDate.time - birthdate!!.time
                val age = java.util.concurrent.TimeUnit.MILLISECONDS.toDays(diffInMilliseconds) / 365

               // textViewResult.text = "Your age is $age years."
                textViewResult.text = "Congratulations! You're $age years 🎉"

                userGreeting.visibility = View.VISIBLE // Ensure the greeting is visible



            } catch (e: ParseException) {
                Log.e("CalculateAge", "Error parsing date: ${e.message}")
                textViewResult.text = "Error calculating age."
                userGreeting.visibility = View.GONE
            }
        } else {
            textViewResult.text = "Please select a valid birthdate."
            userGreeting.visibility = View.GONE
        }
    }
}
