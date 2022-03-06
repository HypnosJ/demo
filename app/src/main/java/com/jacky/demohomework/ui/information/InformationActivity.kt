package com.jacky.demohomework.ui.information

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.google.gson.Gson
import com.jacky.demohomework.R
import com.jacky.demohomework.data.api.RetrofitService
import com.jacky.demohomework.data.model.LoginRes
import com.jacky.demohomework.data.repository.MainRepository

class InformationActivity : AppCompatActivity() {
    private val TAG = this.javaClass.simpleName
    private lateinit var navController: NavController
    private lateinit var vm: InformationViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_information)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        toolbar.title = ""
        setSupportActionBar(toolbar)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.information_nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        val appBarConfiguration = AppBarConfiguration.Builder().build()
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration)

        val retrofitService = RetrofitService
        val mainRepository = MainRepository(retrofitService)
        vm = ViewModelProvider(
            this,
            InformationViewModelFactory(mainRepository)
        )[InformationViewModel::class.java]

        val loginInfo = Gson().fromJson(intent.getStringExtra("loginInfo"), LoginRes::class.java)
        vm.loginInfo.postValue(loginInfo)
    }

    override fun onSupportNavigateUp(): Boolean {
        if (!NavigationUI.navigateUp(navController, null)) {
            finish()
        }
        return true
    }

    override fun onBackPressed() {
        onSupportNavigateUp()
    }
}