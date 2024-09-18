package com.whgarcia.mtodobiseccin.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.whgarcia.mtodobiseccin.model.BisectionModel
import com.whgarcia.mtodobiseccin.utils.PolinomioEvaluator
import java.util.Locale

class BisectionViewModel : ViewModel() {
    var state by mutableStateOf(BisectionModel())
        private set

    var showBottomSheet by mutableStateOf(false)
        private set

    fun showBottomSheet(show: Boolean){
        showBottomSheet = show
    }

    fun onValue(value: String, text: String){
        when(text){
            "function" -> state = state.copy(function = value)
            "x0" -> state = state.copy(x0 = value)
            "x1" -> state = state.copy(x1 = value)
            "tolerance" -> state = state.copy(tolerance = value)
            "type_tolerance" -> state = state.copy(type_tolerance = value.toInt())
            "precision" -> state = state.copy(precision = value)
        }
    }

    // Método para realizar las validaciones de las entradas
    fun onCalculate(){
        val function = state.function
        val x0 = state.x0.toDoubleOrNull()
        val x1 = state.x1.toDoubleOrNull()
        val tolerance = state.tolerance.toDoubleOrNull()

        if (function.isEmpty()){
            state = state.copy(showAlert = true, alertMessage = "Function value isn´t empty")
            return
        }else if(isFunction(function)){
            state = state.copy(showAlert = true, alertMessage = "Invalid function value")
            return
        }

        if (x0 == null) {
            state = state.copy(showAlert = true, alertMessage = "Invalid x0 value")
            return
        }

        if (x1 == null) {
            state = state.copy(showAlert = true, alertMessage = "Invalid x1 value")
            return
        }

        if (tolerance == null) {
            state = state.copy(showAlert = true, alertMessage = "Tolerance value is empty")
            return
        }else if (tolerance <= 0) {
            state = state.copy(showAlert = true, alertMessage = "Invalid tolerance value")
            return
        }

        if (evaluateFunction(function, x0, x1)){
            performCalculation(function, x0, x1, tolerance, state.type_tolerance, state.precision.toInt())
        }else{
            state = state.copy(showAlert = true, alertMessage = "There is no root between the interval of X0 and X1")
            return
        }
    }

    // Método para calcular el resultado de la bisección
    private fun performCalculation(function: String, _x0: Double, _x1: Double, tolerance: Double, type_tolerance: Int, precicion: Int){
        try {
            // Crear una instancia de la clase para EvaluarExpresion
            val polinomioEvaluator = PolinomioEvaluator()

            var x0 = _x0
            var x1 = _x1
            var x = (x0 + x1) / 2
            var fx = polinomioEvaluator.evaluarPolinomio(function, x)

            // Iterar para realizar el método de bisección
            while (Math.abs(fx) > tolerance) {
                val xAnterior = x

                // Actualizar los extremos del intervalo
                if (fx * polinomioEvaluator.evaluarPolinomio(function, x0) < 0) {
                    x1 = x
                } else {
                    x0 = x
                }

                // Calcular el nuevo valor de x
                x = (x0 + x1) / 2
                fx = polinomioEvaluator.evaluarPolinomio(function, x)

                // Verificar la convergencia del punto final
                if (type_tolerance == 0 &&
                    convergenciaPuntoFinal(x, xAnterior, tolerance)
                ) {
                    break
                }

                // Verificar la convergencia de la función
                if (type_tolerance == 1 &&
                    convergenciaFuncion(fx, tolerance)
                ) {
                    break
                }
            }

            // Guardar el resultado en el estado
            val xStr = String.format(Locale.US, "%.${precicion}f", x)
            state = state.copy(bisectioResult = xStr.toDouble())
            showBottomSheet(true)
        } catch (e: Exception) {
            // Manejar errores de formato o cálculo
            Log.e("BisectionViewModel",e.message.toString())
        }
    }

    // Método para verificar la convergencia del punto final
    private fun convergenciaPuntoFinal(x: Double, xAnterior: Double, tolerancia: Double): Boolean {
        return Math.abs(x - xAnterior) < tolerancia
    }

    // Método para verificar la convergencia de la función
    private fun convergenciaFuncion(fx: Double, tolerancia: Double): Boolean {
        return Math.abs(fx) < tolerancia
    }

    // Método para validar si la función se puede evaluar
    private fun isFunction(function: String): Boolean{
        try {
            val polinomioEvaluator = PolinomioEvaluator()
            polinomioEvaluator.evaluarPolinomio(function, 0.0)
            return false
        }catch (e: Exception){
            return true
        }
    }

    // Método para evaluar si el producto de x0 y x1 es menor que 0, para saber si existe una raiz en ese intervalo
    private fun evaluateFunction(function: String, x0: Double, x1: Double): Boolean{
        val polinomioEvaluator = PolinomioEvaluator()
        val eva_x0 = polinomioEvaluator.evaluarPolinomio(function, x0)
        val eva_x1 = polinomioEvaluator.evaluarPolinomio(function, x1)
        return if (eva_x0 * eva_x1 < 0) true else false
    }

    // Método para cerrar la alerta
    fun dismissAlert() {
        state = state.copy(showAlert = false)
    }
}