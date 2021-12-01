package com.balu.calculator

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private var tvSelectedDate: TextView? = null
    private var minute: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnDatePicker: Button = findViewById(R.id.buttonDatePicker)
        btnDatePicker.setOnClickListener{
            tvSelectedDate = findViewById(R.id.tvselectedate)
            minute = findViewById(R.id.minute)

            DataPicker()
        }
    }

    fun DataPicker(){
        var myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)

        DatePickerDialog(this, { view,SelectedYear,SelectedMonth,DayOfMonth ->
            
                    val selectedDate = "$SelectedYear/${SelectedMonth+1}/$DayOfMonth"
                    tvSelectedDate?.text = selectedDate
                    val sdf  = SimpleDateFormat("dd/MM/yyyy",Locale.ENGLISH)
                    val theDate = sdf.parse(selectedDate)

                    val selectedDateInMinutes = theDate.time / 60000
                    val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                    val  currentDateInMinutes = currentDate.time/60000
                    val differenceInMinutes = currentDateInMinutes - selectedDateInMinutes

                    minute?.setText(differenceInMinutes.toString())

        },
                year,
                month,
                day
            ).show()
    }
}