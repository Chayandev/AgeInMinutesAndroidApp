package com.example. ageinminimutes

import com.example.ageinminimutes.R
import java.text.SimpleDateFormat


import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.DatePicker
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import java.util.*

class MainActivity : AppCompatActivity() {
    private var tvslectedDate:TextView?=null
    private var result:TextView?=null
    private var img1:ImageView?=null
    private var img2:ImageView?=null
    private var img3:ImageView?=null
    private var age_comment:TextView?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btnDatePicker=findViewById<Button>(R.id.BtnDatePiker)
        result=findViewById(R.id.result)
        tvslectedDate=findViewById(R.id.date)
        img1=findViewById(R.id.image1)
        img2=findViewById(R.id.image2)
        img3=findViewById(R.id.image3)
        age_comment=findViewById(R.id.age_coment)
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
                    "Selected DOB is $selecteDayOfMonth.${selecteMonth+1}.$selecteYear",Toast.LENGTH_SHORT).show()

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

                         //image and comment
                         when(diffreceInMinutes){
                            in 0 until 1576800  -> {
                                img1?.setImageResource(R.drawable.bab)
                                img2?.setImageResource(R.drawable.baby)
                                img1?.visibility= View.VISIBLE
                                img2?.visibility= View.VISIBLE
                                img3?.visibility= View.INVISIBLE
                                age_comment?.text="Baby"
                            }
                             in 1576800 until  8935200->{
                                 img1?.setImageResource(R.drawable.children)
                                 img2?.setImageResource(R.drawable.children__1_)
                                 img1?.visibility= View.VISIBLE
                                 img2?.visibility= View.VISIBLE
                                 img3?.visibility= View.INVISIBLE
                                 age_comment?.text="Children"
                             }
                             in 8935200 until 16293600 ->{
                                 img1?.visibility= View.INVISIBLE
                                 img2?.visibility= View.INVISIBLE
                                 img3?.setImageResource(R.drawable.lover)
                                 img3?.visibility= View.VISIBLE
                                 age_comment?.text="Young Adult"
                             }
                             in 16293600 until 24177600->{
                                 img1?.visibility= View.INVISIBLE
                                 img2?.visibility= View.INVISIBLE
                                 img3?.setImageResource(R.drawable.parents)
                                 img3?.visibility= View.VISIBLE
                                 age_comment?.text="Middle Aged Adult"
                             }
                             else->{
                                 img1?.setImageResource(R.drawable.olde)
                                 img2?.setImageResource(R.drawable.newold)
                                 img1?.visibility= View.VISIBLE
                                 img2?.visibility= View.VISIBLE
                                 img3?.visibility= View.INVISIBLE
                                 age_comment?.text="Old Aged Adult"
                             }
                         }
                     }
                 }
            },
            year,month,day)
        dpd.datePicker.maxDate=System.currentTimeMillis()-86400000
        dpd.show()

    }
}