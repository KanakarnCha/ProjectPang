package com.example.applicationelderpathtime

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private lateinit var navHostFragment: NavHostFragment
    private lateinit var btnNavigationView: BottomNavigationView
    lateinit var toolbar: androidx.appcompat.widget.Toolbar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        controllerNavigationBar()
        hideNavigationBar()
    }

    private fun  controllerNavigationBar(){
        btnNavigationView = findViewById(R.id.btnNavigationView)
        toolbar = findViewById(R.id.toolBar)
        navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        btnNavigationView.setupWithNavController(navController)
        var appBarConfiguration = AppBarConfiguration(navController.graph)
        appBarConfiguration = AppBarConfiguration.Builder(
            // กำหนด Fragment ที่ไม่ต้องการให้แสดงปุ่ม Back
            R.id.loginFragment,
            R.id.homeFragment
        ).build()
        toolbar.setupWithNavController(navController,appBarConfiguration)
    }


    fun hideNavigationBar(){
        navController.addOnDestinationChangedListener{_,destination,_->
            if (destination.id == R.id.homeFragment||destination.id == R.id.profileFragment){
                btnNavigationView.visibility = View.VISIBLE
            }else{
                btnNavigationView.visibility = View.GONE
            }
        }
    }


}