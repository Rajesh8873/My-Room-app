package com.example.myrooms

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.myrooms.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() ,NavigationView.OnNavigationItemSelectedListener {

    private lateinit var fragmentManager: FragmentManager
    private lateinit var  binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        setSupportActionBar(binding.appToolbar)

        val toggle = ActionBarDrawerToggle(this,binding.sideNavigation, binding.appToolbar, R.string.nav_open , R.string.nav_close )
        binding .sideNavigation.addDrawerListener(toggle)
        toggle.syncState()
        binding.sideNavigation.addDrawerListener(toggle)

        binding.sideNav.setNavigationItemSelectedListener(this)

        binding.navigationBottom.background = null
        binding.navigationBottom.setOnItemSelectedListener { item ->
            when(item.itemId){
                R.id.bottom_home -> openFragment(HomeFragment())
                R.id.bottom_search ->openFragment(ExploreFragment())
                R.id.bottom_add ->openFragment(FavoriteFragment())
                R.id.bottom_reels ->openFragment(ChatFragment())
                R.id.bottom_profile ->openFragment(ProfileFragment())
                R.id.bottom_profile ->openFragment(ProfileFragment())

            }
            true
        }

        fragmentManager = supportFragmentManager
        openFragment(HomeFragment())




    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.nav_booking -> openFragment(nav_BookingFragment())
            R.id.nave_notification ->openFragment(nav_notificationFragment())
            R.id.nave_pyment->openFragment(Pyment_methodFragment())
            R.id.nave_setting ->openFragment(nav_Setting_Fragment())
            R.id.nave_privcy ->openFragment(nav_PrivacyFragment())
            R.id.nave_about ->openFragment(nav_AboutFragment())
            R.id.nav_logout -> Toast.makeText(this,"LogOut Successfully",Toast.LENGTH_SHORT).show()


        }

        binding.sideNavigation.closeDrawer(GravityCompat.START)
        return true

    }


    override fun onBackPressed() {
        if(binding.sideNavigation.isDrawerOpen(GravityCompat.START)){
            binding.sideNavigation.closeDrawer(GravityCompat.START)
        }
        else {
            super.getOnBackPressedDispatcher().onBackPressed()
        }

    }


    private fun openFragment(fragment: Fragment){
        val fragmentTransaction:FragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container_view, fragment)
        fragmentTransaction.commit()
    }

}

