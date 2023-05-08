package com.dds.theecogame.domain.userRestrictions

import android.content.Context
import android.provider.ContactsContract.CommonDataKinds.Email
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import com.dds.theecogame.R
import com.dds.theecogame.common.Resource
import com.dds.theecogame.data.repository.UserRepositoryImpl
import com.dds.theecogame.domain.repository.UserRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking

class UserRestrictions(val context: Context) {

    private var error: String = String()
    private var check: Boolean = true
    private val userRepository: UserRepository = UserRepositoryImpl()

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
            check = false
        }
        //check con llamada a api
        runBlocking {
            userRepository.checkUsername(username).collect {
                when (it) {
                    is Resource.Loading -> {}
                    is Resource.Success -> {
                        check = true
                    }
                    is Resource.Error -> {
                        error = context.resources.getString(R.string.username_db_error)
                        //error = it.message.toString()
                        check = false
                    }
                }
            }
        }

        return check
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
        runBlocking {
            userRepository.checkEmail(email).collect {
                when (it) {
                    is Resource.Loading -> {}
                    is Resource.Success -> {
                        check = true
                    }
                    is Resource.Error -> {
                        error = context.resources.getString(R.string.email_db_error)
                        //error = it.message.toString()
                        check = false
                    }
                }
            }
        }

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
        //check con llamada a api NO es necesaria

        return true
    }

}