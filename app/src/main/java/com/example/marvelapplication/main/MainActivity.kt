package com.example.marvelapplication.main

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.marvelapplication.main.home.HomeFragment
import com.example.marvelapplication.R
import com.example.marvelapplication.data.repository.MarvelDataSource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.NonCancellable.cancel


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        initView()
    }


    private fun initView() {
        val homeFragment = HomeFragment()
        replaceFragment(homeFragment)

    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.frameLayout, fragment, "DiscoverFragment")
        }.commit()
    }

    fun loadFragment(fragment: Fragment?, tag: String): Boolean {
        if (fragment != null) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.frameLayout, fragment, tag)
                .addToBackStack(tag)
                .commit()
            return true
        }
        return false
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount==0) {
            val builder = AlertDialog.Builder(this)
                .setTitle(getString(R.string.application_be_closed))
                .setMessage(getString(R.string.do_you_want_to_close))
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton(getString(R.string.close)) { dialogInterface, _ ->
                    dialogInterface.cancel()
                    android.os.Process.killProcess(android.os.Process.myPid())
                }
                .setNegativeButton(getString(R.string.cancell)) { dialogInterface, _ ->
                    dialogInterface.cancel()
                }

            val alertDialog: AlertDialog = builder.create()
            alertDialog.show()
        }
        else{
            supportFragmentManager.popBackStackImmediate()
        }
    }
}