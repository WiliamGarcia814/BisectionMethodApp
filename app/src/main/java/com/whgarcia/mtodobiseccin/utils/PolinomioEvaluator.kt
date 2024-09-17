package com.whgarcia.mtodobiseccin.utils

class PolinomioEvaluator {
    // Método principal para evaluar un polinomio con la variable x
    fun evaluarPolinomio(expresion: String, x: Double): Double {
        // Obtener coeficientes y exponentes de la expresión
        val coeficientes = obtenerCoeficientes(expresion)
        val exponentes = obtenerExponentes(expresion)

        // Evaluar el polinomio con los coeficientes y exponentes obtenidos
        return evaluar(coeficientes, exponentes, x)
    }

    // Método para obtener los coeficientes de la expresión
    private fun obtenerCoeficientes(expresion: String): DoubleArray {
        // Dividir la expresión en términos basándose en el signo de suma o resta
        val terminos = expresion.split("(?=[-+])".toRegex()).toTypedArray()

        // Inicializar un arreglo para almacenar los coeficientes
        val coeficientes = DoubleArray(terminos.size)

        // Extraer los coeficientes de cada término
        for (i in terminos.indices) {
            coeficientes[i] = extraerCoeficiente(terminos[i])
        }

        // Regresar el arreglo de los coeficientes
        return coeficientes
    }

    // Método para extraer el coeficiente de un término
    private fun extraerCoeficiente(termino: String): Double {
        // Utilizar expresiones regulares para buscar el coeficiente en el término
        val pattern = Regex("([+-]?\\d*\\.?\\d*)[xX]?\\^?(\\d*)")
        val matcher = pattern.matchEntire(termino)

        return if (matcher != null) {
            val coeficienteStr = matcher.groupValues[1]
            when {
                coeficienteStr == "+" || coeficienteStr.isEmpty() || coeficienteStr == "-" -> {
                    if (coeficienteStr == "-") -1.0 else 1.0
                }
                else -> coeficienteStr.toDouble()
            }
        } else {
            termino.toDouble()
        }
    }

    // Método para obtener los exponentes de la expresión
    private fun obtenerExponentes(expresion: String): IntArray {
        // Dividir la expresión en términos basándose en el signo de suma o resta
        val terminos = expresion.split("(?=[-+])".toRegex()).toTypedArray()

        // Inicializar un arreglo para almacenar los exponentes
        val exponentes = IntArray(terminos.size)

        // Extraer los exponentes de cada término
        for (i in terminos.indices) {
            exponentes[i] = extraerExponente(terminos[i])
        }

        // Regresar el arreglo de exponentes
        return exponentes
    }

    // Método para extraer el exponente de un término
    private fun extraerExponente(termino: String): Int {
        // Utilizar expresiones regulares para buscar el exponente en el término
        val pattern = Regex("([+-]?\\d*\\.?\\d*)[xX]?\\^?(\\d*)")
        val matcher = pattern.matchEntire(termino)

        return if (termino.contains("x", ignoreCase = true) && matcher != null) {
            val exponenteStr = matcher.groupValues[2]
            if (exponenteStr.isEmpty()) 1 else exponenteStr.toInt()
        } else {
            0
        }
    }

    // Método para evaluar el polinomio con los coeficientes y exponentes dados
    private fun evaluar(coeficientes: DoubleArray, exponentes: IntArray, x: Double): Double {
        var resultado = 0.0

        // Sumar los términos del polinomio
        for (i in coeficientes.indices) {
            resultado += coeficientes[i] * Math.pow(x, exponentes[i].toDouble())
        }

        return resultado
    }
}