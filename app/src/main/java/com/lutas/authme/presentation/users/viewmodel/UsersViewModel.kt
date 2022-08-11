package com.lutas.authme.presentation.users.viewmodel

import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.lutas.authme.domain.model.User
import com.lutas.authme.domain.repository.IUserRepository
import com.lutas.authme.util.Event
import kotlinx.coroutines.launch

class UsersViewModel(
    private val repository: IUserRepository
): ViewModel() {
    fun getUsersLiveData(): LiveData<PagingData<User>> {
        return repository.getUsersPagingStream().cachedIn(viewModelScope)
    }
}