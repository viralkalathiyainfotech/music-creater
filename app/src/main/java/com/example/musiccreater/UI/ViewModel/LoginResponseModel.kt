package com.example.musiccreater.UI.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.android.gamecommerce.viewModel.base.BaseViewModel
import com.example.musiccreater.ApiServices.RetrofitClient
import com.example.musiccreater.Model.LoginResponse.LoginResponse
import com.example.musiccreater.Model.UiState
import kotlinx.coroutines.launch

class LoginResponseModel : BaseViewModel() {

    private val _loginState = MutableLiveData<UiState<LoginResponse>>()
    val data: LiveData<UiState<LoginResponse>> get() = _loginState

    private val _googleLoginState = MutableLiveData<UiState<LoginResponse>>()
    val googleData: LiveData<UiState<LoginResponse>> get() = _googleLoginState

    fun login(credentials: Map<String, String>) {
        viewModelScope.launch {
            _loginState.value = UiState.Loading
            try {
                val response = RetrofitClient.apiService.loginUser(credentials)
                if (response.isSuccessful && response.body() != null) {
                    _loginState.value = UiState.Success(response.body()!!)
                } else {
                    val errorMsg = if (response.code() == 401) {
                        "Invalid email or password"
                    } else {
                        response.errorBody()?.string() ?: "Unexpected error"
                    }
                    _loginState.value = UiState.Error(errorMsg)
                }
            } catch (e: Exception) {
                _loginState.value = UiState.Error(e.message ?: "Unknown error")
            }
        }
    }

//    fun googleLogin(credentials: Map<String, String>) {
//        viewModelScope.launch {
//            _googleLoginState.value = UiState.Loading
//            try {
//                val response = RetrofitClient.apiService.googleLogin(credentials)
//                if (response.isSuccessful && response.body() != null) {
//                    _googleLoginState.value = UiState.Success(response.body()!!)
//                } else {
//                    val errorMsg = if (response.code() == 401) {
//                        "Invalid Google account"
//                    } else {
//                        response.errorBody()?.string() ?: "Unexpected error"
//                    }
//                    _googleLoginState.value = UiState.Error(errorMsg)
//                }
//            } catch (e: Exception) {
//                _googleLoginState.value = UiState.Error(e.message ?: "Unknown error")
//            }
//        }
//    }
}