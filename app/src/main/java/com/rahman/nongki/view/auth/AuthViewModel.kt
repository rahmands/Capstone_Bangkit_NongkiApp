package com.rahman.nongki.view.auth


import androidx.lifecycle.*
import com.rahman.nongki.model.Repository
import com.rahman.nongki.model.dto.LoginResponse
import com.rahman.nongki.model.dto.RegisterResponse
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class AuthViewModel (val repository: Repository): ViewModel() {


    private var _token = MutableLiveData<String>()
    val token : LiveData<String> = _token

    private val _message = MutableLiveData<String>()
    val message: LiveData<String> = _message

    private var _isButtonEnable = MutableLiveData<Boolean>()
    val isButtonEnable: LiveData<Boolean> get() = _isButtonEnable

    private val _registerResponse = MutableLiveData<RegisterResponse>()
    val registerResponse: LiveData<RegisterResponse> get() = _registerResponse

    private val _loginResponse = MutableLiveData<LoginResponse>()
    val loginResponse: LiveData<LoginResponse> get() = _loginResponse

    fun login(email: String, password: String){
        viewModelScope.launch {
            try {
                val result = repository.login(email, password)
                if (result.error == false){
                    _loginResponse.value = result
                    _message.value = result.message?:"Berhasil"
                } else {
                    _message.value = result.message?:"Gagal"
                }

            } catch (e:Exception) {
                _message.value = e.message?: "Error"
            }
        }
    }

    fun register(username: String, email: String, password: String){
        viewModelScope.launch {
            try {
                val result = repository.register(username, email, password)
                if (result.error == false){
                    _registerResponse.value = result
                    _message.value = result.message?:"Berhasil"
                } else {
                    _message.value = result.message?:"Gagal"
                }

            } catch (e:Exception) {
                _message.value = e.message?: "Error"
            }
        }
    }

    fun logout(){
        TODO("Nanti di akhir")
    }

    val key = repository.token.asLiveData()


    init {
        repository.token.onEach {
            _token.value = it
        }
    }

}
