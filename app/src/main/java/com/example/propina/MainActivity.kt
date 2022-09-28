package com.example.propina

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import com.example.propina.databinding.ActivityMainBinding
import java.text.NumberFormat
import kotlin.math.ceil

//Variable para probar el log
private const val TAG = "MainActivity"
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        displayTip(0.0)
        binding.calculate.setOnClickListener { calcularPropina() }
        binding.costOfServiceEditText.setOnKeyListener { view, keyCode, _/*keyEvent */ ->
            handleKeyEvent(
                view,
                keyCode
            )
        }

        /* funciones para pruebas
        division()
        logging() */
    }

    private fun calcularPropina() {
        //Get cost of service
        val stringInTextField = binding.costOfServiceEditText.text.toString()
        val cost = stringInTextField.toDoubleOrNull()
        if (cost == null || cost == 0.0) {
            displayTip(0.0)
            return
        }

        //Get percentage de propina a partir del radioButton seleccionado
        val tipPercentage = when (binding.tipOptions.checkedRadioButtonId) {
            R.id.option_twenty_percent -> 0.20
            R.id.option_eighteen_percent -> 0.18
            else -> 0.15
        }

        //Calculo de propina
        var tip = cost * tipPercentage

        //calculo de redondeo de la propina, si fue seleccionado
        if (binding.roundUpSwitch.isChecked) {
            tip = ceil(tip) //ceil -> redondea la propina
        }

        displayTip(tip)

    }

    //Formateador de moneda
    private fun displayTip(propina: Double) {
        val formattedTip = NumberFormat.getCurrencyInstance().format(propina)
        binding.tipResult.text = getString(R.string.tip_amount, formattedTip)
    }

    //Evento de escucha de claves para ocultar teclado cuando se presiona enter
    private fun handleKeyEvent(view: View, keyCode: Int): Boolean {
        if (keyCode == KeyEvent.KEYCODE_ENTER) {
            // Hide the keyboard
            val inputMethodManager =
                getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
            return true
        }
        return false
    }

    //Funcion para probar errores de consola y hacer debug
    private fun division() {
        val numerator = 60
        var denominador = 4
        repeat(5) {
            if (denominador != 0) {
                println(numerator / denominador)
            }
            denominador--
        }
    }

    //Función para probar distintos logs en consola
    private fun logging() {
        Log.e(TAG, "ERROR: a serious error like an app crash")
        Log.w(TAG, "WARN: Warns about potential for serious error")
        Log.i(TAG, "INFO: Reporting technical information")
        Log.d(TAG, "DEBUG: reporting technical information, useful for debugging")
        Log.v(TAG, "VERBOSE: more verbose than DEBUG logs")
    }
}