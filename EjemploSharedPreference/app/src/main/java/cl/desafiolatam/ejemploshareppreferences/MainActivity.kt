package cl.desafiolatam.ejemploshareppreferences

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    companion object {
        private const val baseClave = "EjemploSP"
        const val claveNumEnt = baseClave + "NumEnt"
        const val claveTexto = baseClave + "Texto"
        const val claveSwitch = baseClave + "Switch"
        const val claveNumDec = baseClave + "NumDec"
    }
    lateinit var sharedPreferences: SharedPreferences

    lateinit var numEntInput: EditText
    lateinit var numEntValue:TextView
    lateinit var textoInput:EditText
    lateinit var textoValue: TextView
    lateinit var switch: Switch
    lateinit var numDecInput:EditText
    lateinit var numDecValue:TextView
    lateinit var borrar: Button
    lateinit var guardar:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val fileName = "cl.desafiolatam.ejemplosharedpreferences"
        sharedPreferences = getSharedPreferences(fileName, Context.MODE_PRIVATE)
        initViews()
        leerDesdeSharedPreferences()
    }
    private fun initViews() {
        numEntInput = findViewById(R.id.numero_input)
        numEntValue = findViewById(R.id.num_ent_valor)
        textoInput = findViewById(R.id.texto_input)
        textoValue = findViewById(R.id.text_valor)
        switch = findViewById(R.id.switch_input)
        numDecInput = findViewById(R.id.num_dec_input)
        numDecValue = findViewById(R.id.num_dec_valor)
        borrar = findViewById(R.id.borrar)
        guardar = findViewById(R.id.guardar)

        borrar.setOnClickListener(object: View.OnClickListener {
            override fun onClick(p0: View?) {
                borrar()
            }
        })
        guardar.setOnClickListener(object: View.OnClickListener {
            override fun onClick(p0: View?) {
                guardar()
            }
        })
    }
    private fun leerDesdeSharedPreferences() {
        numEntValue.text = sharedPreferences.getInt(claveNumEnt, 0).toString()
        textoValue.text = sharedPreferences.getString(claveTexto, "")
        val checked = sharedPreferences.getBoolean(claveSwitch, false)
        switch.isChecked = checked
        switch.isSelected = checked
        numDecValue.text = sharedPreferences.getFloat(claveNumDec,0.0f).toString()
    }
    private fun guardar() {
        if (numEntInput.text!!.isNotEmpty()) {
            val numEnt = numEntInput.text.toString().toInt()
            sharedPreferences.edit().putInt(claveNumEnt, numEnt).apply()
        }
        if (textoInput.text!!.isNotEmpty()) {
            sharedPreferences.edit()
                .putString(claveTexto, textoInput.text.toString())
                .apply()
        }
        val checked = switch.isChecked
        sharedPreferences.edit().putBoolean(claveSwitch, checked).apply()
        if (numDecInput.text!!.isNotEmpty()) {
            val numDec = numDecInput.text.toString().toFloat()
            sharedPreferences.edit().putFloat(claveNumDec, numDec).apply()
        }
    }
    private fun borrar() {
        sharedPreferences.edit().remove(claveNumEnt).apply()
        sharedPreferences.edit().remove(claveTexto).apply()
        sharedPreferences.edit().remove(claveSwitch).apply()
        sharedPreferences.edit().remove(claveNumDec).apply()
    }

}