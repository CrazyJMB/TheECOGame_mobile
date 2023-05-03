package com.dds.theecogame.domain.userRestrictions

import android.content.Context
import android.provider.ContactsContract.CommonDataKinds.Email
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import com.dds.theecogame.R

class UserRestrictions(val context: Context) {

    private var error: String = String()
    private lateinit var etUsername: EditText
    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnCreateUser: Button

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

        if (email.isEmpty()) return true

        if (!patron.matches(email)) {
            error = context.resources.getString(R.string.email_format_error)
            return false
        }
        //check con llamada a api

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
        val lowercase = Regex(".*[a-z].*")
        val uppercase = Regex(".*[A-Z].*")
        val number = Regex(".*[\\d].*")

        if (password.isEmpty()) return true
        if (password.length < 8) {
            error = context.resources.getString(R.string.password_length_error)
            return false
        }
        if (!lowercase.matches(password)) {
            error = context.resources.getString(R.string.password_lowercase_error)
            return false
        }
        if (!uppercase.matches(password)) {
            error = context.resources.getString(R.string.password_uppercase_error)
            return false
        }
        if (!number.matches(password)) {
            error = context.resources.getString(R.string.password_number_error)
            return false
        }
        //check con llamada a api

        return true
    }

    /*
    fun sendObjects(
        etUsername: EditText,
        etEmail: EditText,
        etPassword: EditText,
        btnCreateUser: Button
    ) {
        this.etUsername = etUsername
        this.etEmail = etEmail
        this.etPassword = etPassword
        this.btnCreateUser = btnCreateUser
    }
    */

    /*
    //Watcher for Create User button
    val textWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            /*if (!etUsername.text.isNullOrEmpty() &&
                !etEmail.text.isNullOrEmpty() &&
                !etPassword.text.isNullOrEmpty() &&
                checkedUsername &&
                checkedEmail &&
                checkedPassword
            ) {
                btnCreateUser.isEnabled = true
            } else {
                btnCreateUser.isEnabled = false
            }*/

            btnCreateUser.isEnabled = false
            if (etUsername.text.isNullOrEmpty()) return
            if (etEmail.text.isNullOrEmpty()) return
            if (etPassword.text.isNullOrEmpty()) return
            if (!checkUsername(etUsername.toString())) return
            if (!checkEmail(etEmail.toString())) return
            if (!checkPassword(etPassword.toString())) return
            btnCreateUser.isEnabled = true
        }

        override fun afterTextChanged(s: Editable?) {
        }
    }*/
}