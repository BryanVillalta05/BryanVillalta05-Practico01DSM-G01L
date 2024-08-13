package com.example.notascalc

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText
import android.text.InputFilter
import android.text.Spanned

class MainActivity : AppCompatActivity() {
    var nom: TextInputEditText?= null
    var nota1: TextInputEditText?= null
    var nota2: TextInputEditText?= null
    var nota3: TextInputEditText?= null
    var nota4: TextInputEditText?= null
    var nota5: TextInputEditText?= null
    var aprobText: TextView?=null
    var reprobText: TextView?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val calcBtn: Button = findViewById(R.id.calc)
        nom = findViewById(R.id.nombre)
        nota1 = findViewById(R.id.nota1)
        nota2 = findViewById(R.id.nota2)
        nota3 = findViewById(R.id.nota3)
        nota4 = findViewById(R.id.nota4)
        nota5 = findViewById(R.id.nota5)
        setInputFilter(nota1!!, 0.0, 10.0)
        setInputFilter(nota2!!, 0.0, 10.0)
        setInputFilter(nota3!!, 0.0, 10.0)
        setInputFilter(nota4!!, 0.0, 10.0)
        setInputFilter(nota5!!, 0.0, 10.0)
        aprobText = findViewById(R.id.textView)
        reprobText = findViewById(R.id.textView3)
        calcBtn.setOnClickListener{
            var nombre:String = nom?.text.toString()
            var not1:Int? = nota1?.text.toString().toIntOrNull()
            var not2:Int? = nota2?.text.toString().toIntOrNull()
            var not3:Int? = nota3?.text.toString().toIntOrNull()
            var not4:Int? = nota4?.text.toString().toIntOrNull()
            var not5:Int? = nota5?.text.toString().toIntOrNull()

            val promedio: Double = calcular(not1, not2, not3, not4, not5)

            if(promedio >= 6.0){
                reprobText?.visibility = View.INVISIBLE
                aprobText?.text = "$nombre tu promedio es: $promedio, ¡Aprobaste!"
                aprobText?.visibility = View.VISIBLE
            }else{
                aprobText?.visibility = View.INVISIBLE
                reprobText?.text = "$nombre tu promedio es: $promedio, Reprobaste"
                reprobText?.visibility = View.VISIBLE
            }

        }
    }
    fun calcular(not1: Int?, not2: Int?, not3: Int?, not4: Int?, not5: Int?): Double {
        val n1 = (not1 ?: 0) * 0.15
        val n2 = (not2 ?: 0) * 0.15
        val n3 = (not3 ?: 0) * 0.20
        val n4 = (not4 ?: 0) * 0.25
        val n5 = (not5 ?: 0) * 0.25

        val suma = n1 + n2 + n3 + n4 + n5
        return suma
    }

    fun setInputFilter(editText: TextInputEditText, minValue: Double, maxValue: Double) {
        val filter = InputFilter { source, start, end, dest, dstart, dend ->
            try {
                val input = dest.toString().substring(0, dstart) + source + dest.toString().substring(dend)
                val value = input.toDouble()
                if (value in minValue..maxValue) {
                    null
                } else {
                    ""
                }
            } catch (e: NumberFormatException) {
                ""
            }
        }
        editText.filters = arrayOf(filter)
    }


}