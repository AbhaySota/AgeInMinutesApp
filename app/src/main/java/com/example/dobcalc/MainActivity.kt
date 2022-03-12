package com.example.dobcalc

import android.app.DatePickerDialog
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.N)

    private var selectedDateView: TextView? =null
    private var result:TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnCheck : Button = findViewById(R.id.btnDatePicker)
        selectedDateView= findViewById(R.id.selectedDate)
        result = findViewById(R.id.Answer)
        btnCheck.setOnClickListener {
            clickedDatePicker()
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun clickedDatePicker()
    {
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)

       val dpd = DatePickerDialog(this , DatePickerDialog.OnDateSetListener{
            _, selectedYear, selectedMonth, selectedDayOfMonth ->
            //Toast.makeText(this,"YEAR : $selectedYear , MONTH : ${selectedMonth+1} , DAY : $selectedDayOfMonth" , Toast.LENGTH_LONG).show()
            selectedDateView?.text = "$selectedDayOfMonth/${selectedMonth+1}/$selectedYear"

            val sdf=SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)

            val theDate = sdf.parse(selectedDateView?.text.toString())
            theDate?.let{
                val selectedDateInMinutes = theDate.time / 60_000

                val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                currentDate?.let{
                    val currentDateInMinutes = currentDate.time/60_000

                    val ageInMinutes = currentDateInMinutes - selectedDateInMinutes

                    result?.text = ageInMinutes.toString()
                }
            }


        }, year , month , day)

        dpd.show()

        dpd.datePicker.maxDate = System.currentTimeMillis()


        //result.text =
    }
}