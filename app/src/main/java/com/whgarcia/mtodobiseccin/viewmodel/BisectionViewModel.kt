package com.whgarcia.mtodobiseccin.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.whgarcia.mtodobiseccin.model.BisecctionModel
import com.whgarcia.mtodobiseccin.utils.PolinomioEvaluator

class BisectionViewModel : ViewModel() {
    var state by mutableStateOf(BisecctionModel())
        private set

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

    fun calculateBisection(){
        try {
            // Crear una instancia de la clase para EvaluarExpresion
            val polinomioEvaluator = PolinomioEvaluator()

            var x0 = state.x0.toDouble()
            var x1 = state.x1.toDouble()
            var x = (x0 + x1) / 2
            var fx = polinomioEvaluator.evaluarPolinomio(state.function, x)

            // Iterar para realizar el método de bisección
            while (Math.abs(fx) > state.tolerance.toDouble()) {
                val xAnterior = x

                // Actualizar los extremos del intervalo
                if (fx * polinomioEvaluator.evaluarPolinomio(state.function, x0) < 0) {
                    x1 = x
                } else {
                    x0 = x
                }

                // Calcular el nuevo valor de x
                x = (x0 + x1) / 2
                fx = polinomioEvaluator.evaluarPolinomio(state.function, x)

                // Verificar la convergencia del punto final
                if (state.type_tolerance == 0 &&
                    convergenciaPuntoFinal(x, xAnterior, state.tolerance.toDouble())
                ) {
                    break
                }

                // Verificar la convergencia de la función
                if (state.type_tolerance == 1 &&
                    convergenciaFuncion(fx, state.tolerance.toDouble())
                ) {
                    break
                }
            }

            // Guardar el resultado en el estado
            state = state.copy(bisectioResult = x)

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
}