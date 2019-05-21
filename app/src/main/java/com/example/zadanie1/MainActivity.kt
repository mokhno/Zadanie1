package com.example.zadanie1

import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import android.R.id.edit
import android.content.Context
import android.content.SharedPreferences.Editor


class MainActivity : AppCompatActivity() {
    var count: Int = 0
    lateinit var sPref: SharedPreferences
    val SAVED_TEXT: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadText()
        buttonPlus.setOnClickListener(View.OnClickListener {
            if (count < 10) {
                count++
                textViewCount.setText(count.toString())
            }

            if (count == 10)
                Toast.makeText(this, "Достигнкто максимальное значение", Toast.LENGTH_SHORT).show()
        })
        buttonMinus.setOnClickListener(View.OnClickListener {
            if (count > 0) {

                count--
                textViewCount.setText(count.toString())
            }


            if (count == 0)
                Toast.makeText(this, "Достигнкто минимальное значение", Toast.LENGTH_SHORT).show()
        })

    }

    override fun onDestroy() {
        super.onDestroy()
        saveText()
    }

    fun saveText() {
        sPref = getPreferences(Context.MODE_PRIVATE)
        val ed = sPref.edit()
        ed.putString(SAVED_TEXT, textViewCount.getText().toString())
        ed.commit()
        Toast.makeText(this, "Text saved", Toast.LENGTH_SHORT).show()
    }

    fun loadText() {
        sPref = getPreferences(Context.MODE_PRIVATE)
        val savedText = sPref.getString(SAVED_TEXT, "")
        count = savedText.toInt()
        textViewCount.setText(savedText)
        Toast.makeText(this, "Text loaded", Toast.LENGTH_SHORT).show()
    }
}
