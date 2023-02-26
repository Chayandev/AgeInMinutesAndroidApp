package com.example.ageinminimutes

import com.example.ageinminimutes.R
import java.text.SimpleDateFormat


import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import android.widget.Toast
import java.util.*

class MainActivity : AppCompatActivity() {
    private var tvslectedDate:TextView?=null
    private var result:TextView?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btnDatePicker=findViewById<Button>(R.id.BtnDatePiker)
        result=findViewById(R.id.result)
        tvslectedDate=findViewById(R.id.date)
        btnDatePicker.setOnClickListener{
          clickDatePicker()
        }

    }
    fun clickDatePicker(){
        val myCalendar= Calendar.getInstance()
        val year=myCalendar.get(Calendar.YEAR)
        val month=myCalendar.get(Calendar.MONTH)
        val day=myCalendar.get(Calendar.DAY_OF_MONTH)
       val dpd= DatePickerDialog(this,
            DatePickerDialog.OnDateSetListener{ _ /*or view*/,selecteYear,selecteMonth,selecteDayOfMonth->
                Toast.makeText(this,
                    "DatePicker works",Toast.LENGTH_SHORT).show()

                val selectedDate="$selecteDayOfMonth/${selecteMonth+1}/$selecteYear"
                tvslectedDate?.text =selectedDate

                val sdf=SimpleDateFormat("dd/MM/yyyy",Locale.ENGLISH)
                val theDate=sdf.parse(selectedDate)
                 theDate?.let {
                     val selectedDateInMinutes=theDate.time/60000

                     val currDate=sdf.parse(sdf.format(System.currentTimeMillis()))
                     val currentDateInminutes=currDate.time/60000
                     currDate?.let {
                         val diffreceInMinutes=currentDateInminutes-selectedDateInMinutes
                         result?.text=diffreceInMinutes.toString()
                     }
                 }
            },
            year,month,day)
        dpd.datePicker.maxDate=System.currentTimeMillis()-86400000
        dpd.show()

    }
}