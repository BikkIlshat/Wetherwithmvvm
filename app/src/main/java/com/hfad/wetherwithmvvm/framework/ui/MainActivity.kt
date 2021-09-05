package com.hfad.wetherwithmvvm.framework.ui


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.hfad.wetherwithmvvm.R
import com.hfad.wetherwithmvvm.framework.ui.contacts.ContactsFragment
import com.hfad.wetherwithmvvm.framework.ui.history.HistoryFragment
import com.hfad.wetherwithmvvm.framework.ui.list_of_cities.ListOfCitiesFragmentBinding

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, ListOfCitiesFragmentBinding.newInstance())
                .commitNow()
        }

    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.menu_history -> {
                openFragment(HistoryFragment.newInstance())
                true
            }
            R.id.menu_contacts -> {
                openFragment(ContactsFragment.newInstance())
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun openFragment(fragment: Fragment) {
        supportFragmentManager.apply {
            beginTransaction()
                .add(R.id.container, fragment)
                .addToBackStack("")
                .commitAllowingStateLoss()
        }
    }
}