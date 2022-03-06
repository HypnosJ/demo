package com.jacky.demohomework.ui.login

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.jacky.demohomework.data.model.LoginReq
import com.jacky.demohomework.data.model.LoginRes
import com.jacky.demohomework.data.repository.MainRepository
import kotlinx.coroutines.*

class LoginViewModel constructor(private val mainRepository: MainRepository) : ViewModel() {
    private val TAG = this.javaClass.simpleName
    private var job: Job? = null
    val loginInfo = MutableLiveData<LoginRes>()
    val errorMessage = MutableLiveData<String>()

    fun doLogin(userName: String, password: String) {
        job = CoroutineScope(Dispatchers.IO).launch {
            val requestBody = LoginReq(userName, password)
            val response = mainRepository.doLogin(requestBody)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    loginInfo.postValue(response.body())
                    Log.d(TAG, "loginRes = " + Gson().toJson(response.body()))
                } else {
                    onError("Error : ${response.message()} ")
                }
            }
        }
    }

    private fun onError(message: String) {
        Log.d(TAG, message)
        errorMessage.postValue(message)
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}