package com.jacky.demohomework.ui.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.google.gson.Gson
import com.jacky.demohomework.data.api.RetrofitService
import com.jacky.demohomework.data.repository.MainRepository
import com.jacky.demohomework.databinding.ActivityLoginBinding
import com.jacky.demohomework.ui.information.InformationActivity

class LoginActivity : AppCompatActivity() {
    private val TAG = this.javaClass.simpleName
    private lateinit var binding: ActivityLoginBinding
    private lateinit var vm: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val retrofitService = RetrofitService
        val mainRepository = MainRepository(retrofitService)
        vm = ViewModelProvider(
            this,
            LoginViewModelFactory(mainRepository)
        )[LoginViewModel::class.java]

        binding.loginLogInBtn.setOnClickListener {
            val username = binding.loginInputEmailEt.text.toString()
            val password = binding.loginInputPasswordEt.text.toString()
            if (username.isNotEmpty()
                && password.isNotEmpty()
                && username.contains("@")
                && username.contains(".")
            ) {
                Log.d(TAG, "username=$username, password=$password")
                vm.doLogin(username, password)
            } else {
                Toast.makeText(
                    this,
                    "Please check email and password",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        vm.loginInfo.observe(this) {
            Log.d(TAG, "login success")
            val intent = Intent(this, InformationActivity::class.java)
            intent.putExtra("loginInfo", Gson().toJson(vm.loginInfo.value))
            startActivity(intent)
            finish()
        }

        vm.errorMessage.observe(this) {
            Toast.makeText(
                this,
                "Login fail, please check email and password",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}