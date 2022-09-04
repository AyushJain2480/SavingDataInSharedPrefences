package com.example.saving_data_in_shared_preferences

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private lateinit var btnSave : Button
    private lateinit var btnLoad : Button
    private lateinit var edName : EditText
    private lateinit var edAge : EditText
    private lateinit var checkBox : CheckBox

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnSave = findViewById(R.id.btnSave)
        btnLoad = findViewById(R.id.btnLoad)
        edName = findViewById(R.id.ed_Name)
        edAge = findViewById(R.id.edAge)
        checkBox = findViewById(R.id.checkBox)

        val sharedPref = getSharedPreferences("myPref", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()

        btnSave.setOnClickListener{

            Toast.makeText(this,"data is saved",Toast.LENGTH_LONG).show()

            val name = edName.text.toString()
            val age = edAge.text.toString().toInt()
            val isAdult = checkBox.isChecked

            editor.apply {
                putString("name",name)
                putInt("age",age)
                putBoolean("isAdult",isAdult)
                apply()
            }
        }

        btnLoad.setOnClickListener{

            Toast.makeText(this,"data is loaded",Toast.LENGTH_LONG).show()

         val name = sharedPref.getString("name",null)
         val age = sharedPref.getInt("age",0)
         val isAdult = sharedPref.getBoolean("isAdult",false)

            edName.setText(name)
            edAge.setText(age.toString())
            checkBox.isChecked = isAdult
        }
    }
}