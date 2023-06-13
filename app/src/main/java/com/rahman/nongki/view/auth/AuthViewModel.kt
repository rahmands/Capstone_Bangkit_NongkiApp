package com.rahman.nongki.view.auth

import android.os.Parcelable
import android.provider.ContactsContract.CommonDataKinds.Email
import android.widget.CalendarView
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase
import com.rahman.nongki.data.remote.ApiAuthConfig
import com.rahman.nongki.model.dto.LoginResponse
import com.rahman.nongki.model.dto.RegisterResponse
import kotlinx.parcelize.Parcelize
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AuthViewModel: ViewModel() {


    val loginResult: MutableLiveData<AuthResult> = MutableLiveData()
    val registerResult: MutableLiveData<AuthResult> = MutableLiveData()

    private val _isError = MutableLiveData<String>()
    val isError: LiveData<String> = _isError

    private var _isButtonEnable = MutableLiveData<Boolean>()
    val isButtonEnable: LiveData<Boolean> get() = _isButtonEnable

    private val _registerResponse = MutableLiveData<RegisterResponse>()
    val registerResponse: LiveData<RegisterResponse> get() = _registerResponse

    private val _loginResponse = MutableLiveData<LoginResponse>()
    val loginResponse: LiveData<LoginResponse> get() = _loginResponse

    fun login(email: String, password: String){
        val log = ApiAuthConfig.getApiService().login(email, password)
        log.enqueue(object : Callback<LoginResponse>{
            override fun onResponse(
                call: Call<LoginResponse>,
                response: Response<LoginResponse>) {
                if (response.isSuccessful) {
                    _loginResponse.value = response.body()
                } else {
                    when (response.code()) {
                        400 -> _isError.value = "invalid account"
                        401 -> _isError.value = "unauthorized"
                        else -> _isError.value = "error ${response.message()}"
                    }
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                _isError.value = t.message
            }

        })

    }

    fun register(username: String, email: String, password: String){
        val reg = ApiAuthConfig.getApiService().register(username, email, password)
        reg.enqueue(object : Callback<RegisterResponse> {
            override fun onResponse(
                call: Call<RegisterResponse>,
                response: Response<RegisterResponse>
            ) {
                if (response.isSuccessful) {
                    _registerResponse.value = response.body()
                } else {
                    when (response.code()) {
                        400 -> _isError.value = "invalid account"
                        401 -> _isError.value = "unauthorized"
                        else -> _isError.value = "error ${response.message()}"
                    }
                }
            }

            override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                _isError.value = t.message
            }

        })
    }


}
