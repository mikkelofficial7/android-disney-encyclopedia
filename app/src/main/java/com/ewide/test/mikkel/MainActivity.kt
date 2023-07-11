package com.ewide.test.mikkel

import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.ewide.test.mikkel.base.BaseActivity
import com.ewide.test.mikkel.databinding.ActivityMainBinding
import com.ewide.test.mikkel.fragment.MainPageFragment

class MainActivity : BaseActivity<ActivityMainBinding>() {
    override fun bindToolbar(): Toolbar? = null

    override fun enableBackButton(): Boolean = false

    override fun getUiBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onFirstLaunch(savedInstanceState: Bundle?) {
        changeFragment(MainPageFragment())
    }

    override fun initUiListener() {
    }

    override fun onBackPressed() {
        if(supportFragmentManager.backStackEntryCount > 0){
            supportFragmentManager.popBackStack()
        } else {
            super.onBackPressed()
        }
    }

    fun changeFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .addToBackStack(fragment.tag)
            .add(R.id.frameLayout, fragment)
            .commit()
    }
}