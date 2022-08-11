package com.lutas.authme.presentation.profile.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lutas.authme.domain.model.Profile
import com.lutas.authme.domain.repository.IUserRepository
import com.lutas.authme.util.Event
import kotlinx.coroutines.launch

class UserProfileViewModel(
    private val repository: IUserRepository
): ViewModel() {
    private val _profile = MutableLiveData<Profile>()
    val profile: LiveData<Profile>
        get() = _profile
    private val _error = MutableLiveData<Event<Throwable>>()
    val error: LiveData<Event<Throwable>>
        get() = _error
    private val _isLoading = MutableLiveData<Event<Boolean>>()
    val isLoading: LiveData<Event<Boolean>>
        get() = _isLoading

    fun getProfile(userName: String) {
        viewModelScope.launch {
            _isLoading.postValue(Event(true))
            try {
                val result = repository.getProfile(userName)
                _profile.postValue(result)
            } catch (throwable: Throwable) {
                _error.postValue(Event(throwable))
            }
            _isLoading.postValue(Event(false))
        }
    }
}