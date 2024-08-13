package com.example.desafio01ejer02

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var nom: EditText
    private lateinit var salario: EditText
    private lateinit var butnCalculate: Button
    private lateinit var viewResultado: TextView
    private lateinit var txtRenta: TextView
    private lateinit var txtAFP: TextView
    private lateinit var txtISSS: TextView
    private lateinit var txtNeto: TextView
    private lateinit var viewNom: TextView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        nom = findViewById(R.id.txtNom)
        salario = findViewById(R.id.txtSalario)
        butnCalculate = findViewById(R.id.btnCalcular)
        txtRenta = findViewById(R.id.textView5)
        txtAFP = findViewById(R.id.textView6)
        txtISSS = findViewById(R.id.textView7)
        txtNeto = findViewById(R.id.textView8)
        viewNom = findViewById(R.id.txtNombre)
        viewResultado = findViewById(R.id.textView4)

        butnCalculate.setOnClickListener{calculate()}

    }

    private fun calculate(){
        val nomtext = nom.text.toString()
        val saltext = salario.text.toString()

        if (nomtext.isEmpty() || saltext.isEmpty()){
            Toast.makeText(this, "Por favor, ingresa los datos correspondientes!",
                Toast.LENGTH_SHORT).show()
            return
        }else if (saltext.toDouble() == 0.00 ){
            Toast.makeText(this, "El salario base no puede ser de 0.00!",
                Toast.LENGTH_SHORT).show()
            return
        }

        val salarioo = saltext.toDouble()
        var ansRenta = 0.00
        var ansAFP = 0.00
        var ansISS = 0.00
        var ansSneto = 0.00

        if (salarioo >= 0.01 && salarioo <= 472.00 ){
            ansRenta = 0.00
            Toast.makeText(this, "Su salario no aplica para descuento de renta",
                Toast.LENGTH_LONG).show()
        }else if (salarioo >= 472.01 && salarioo <= 895.24){
            ansRenta = (salarioo * 0.10)
        }else if (salarioo >= 895.25 && salarioo <= 2038.10){
            ansRenta = (salarioo * 0.20)
        }else{
            ansRenta = (salarioo * 0.30)
        }
        ansAFP = salarioo * 0.0725
        ansISS = salarioo * 0.03
        ansSneto = salarioo - ansRenta - ansAFP - ansISS

        viewResultado.visibility = View.VISIBLE
        viewNom.visibility = View.VISIBLE
        txtRenta.visibility = View.VISIBLE
        txtAFP.visibility = View.VISIBLE
        txtISSS.visibility = View.VISIBLE
        txtNeto.visibility = View.VISIBLE
        txtRenta.text = "Su porcentaje de renta es: $ansRenta"
        txtAFP.text = "Su porcentaje de AFP es: $ansAFP"
        txtISSS.text = "Su porcentaje de ISSS es: $ansISS"
        txtNeto.text = "Su Salario Neto es: $ansSneto"
        viewNom.text = nomtext
    }
}