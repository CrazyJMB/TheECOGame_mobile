package com.dds.theecogame.domain.userRestrictions

import android.content.Context
import com.dds.theecogame.R

class UserRestrictions(val context: Context) {

    private var error: String = String()

    fun getError(): String {
        return error
    }

    /*
    * TRUE --> Valid
    * FALSE --> No valid
    * */
    fun checkUsername(username: String): Boolean {
        //check principal
        if (username.length > 20) {
            error = context.resources.getString(R.string.username_format_error)
            return false
        }
        //check con llamada a api

        //return mensaje error
        return true
    }

    /*
    * Restricciones email:
    * - Una o más letras, números, puntos, guiones bajos, signos de porcentaje o signos de suma o resta.
    * - Seguido de un símbolo de arroba @.
    * - Seguido de una o más letras, números o guiones bajos.
    * - Seguido de un punto ..
    * - Seguido de dos o más letras.
    * */
    fun checkEmail(email: String): Boolean {
        //check principal
        val patron = Regex("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")

        if (email.isNullOrEmpty()) return true

        if (!patron.matches(email)) {
            error = context.resources.getString(R.string.email_format_error)
            return false
        }
        //check con llamada a api

        //return mensaje error
        return true
    }

    /*
    * Restricciones password:
    * - Debe tener al menos 8 caracteres de longitud.
    * - Debe contener al menos una letra minúscula y una letra mayúscula.
    * - Debe contener al menos un número.
    * */
    fun checkPassword(password: String): Boolean {
        //check principal
        val minuscula = Regex("(?=.*[a-z])")
        val mayuscula = Regex("(?=.*[A-Z])")
        val numero = Regex("(?=.*\\d)")

        if (password.length < 8) {
            error = context.resources.getString(R.string.username_format_error)
            return false
        }
        //check con llamada a api

        //return mensaje error
        return true
    }
}