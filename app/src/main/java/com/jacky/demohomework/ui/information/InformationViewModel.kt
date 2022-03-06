package com.jacky.demohomework.ui.information

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.jacky.demohomework.data.model.InformationRes
import com.jacky.demohomework.data.model.LoginRes
import com.jacky.demohomework.data.model.UpdateUserTimeZoneReq
import com.jacky.demohomework.data.repository.MainRepository
import kotlinx.coroutines.*
import java.net.URL

class InformationViewModel constructor(private val mainRepository: MainRepository) : ViewModel() {
    private val TAG = this.javaClass.simpleName
    private var job: Job? = null
    val loginInfo = MutableLiveData<LoginRes>()
    val informationList = MutableLiveData<InformationRes>()
    val errorMessage = MutableLiveData<String>()

    fun getInformationList() {
        CoroutineScope(Dispatchers.IO).launch {
            val json = URL("https://tcgbusfs.blob.core.windows.net/dotapp/news.json").readText()
            Log.d(TAG, json)
            informationList.postValue(Gson().fromJson(json, InformationRes::class.java))
        }
    }

    fun updateUserTimeZone(timezoneInt: Int, timeZoneStr: String) {
        job = CoroutineScope(Dispatchers.IO).launch {
            val requestBody = UpdateUserTimeZoneReq(timezoneInt, timeZoneStr)
            val response = mainRepository.doUpdateUserTimeZone(
                loginInfo.value!!.sessionToken,
                loginInfo.value!!.objectId,
                requestBody
            )
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    Log.d(TAG, "update user time zone success")
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