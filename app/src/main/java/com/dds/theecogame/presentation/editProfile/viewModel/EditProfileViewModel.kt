package com.dds.theecogame.presentation.editProfile.viewModel

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dds.theecogame.data.repository.UserRepositoryImpl
import com.dds.theecogame.domain.Application
import com.dds.theecogame.domain.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EditProfileViewModel() : ViewModel() {

    private val userRepository: UserRepository = UserRepositoryImpl()

    fun saveImage(imageUri: String) {
        viewModelScope.launch(Dispatchers.IO) {
            userRepository.updateAvatar(
                Application.getUser()!!.id,
                imageUri
            )
        }

    }

}