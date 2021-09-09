package com.hfad.wetherwithmvvm.framework.ui


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.hfad.wetherwithmvvm.R
import com.hfad.wetherwithmvvm.framework.ui.contacts.ContactsFragment
import com.hfad.wetherwithmvvm.framework.ui.history.HistoryFragment
import com.hfad.wetherwithmvvm.framework.ui.list_of_cities.ListOfCitiesFragment
import com.hfad.wetherwithmvvm.framework.ui.maps.MapsFragment

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, ListOfCitiesFragment.newInstance())
                .commitNow()
        }

        val string = intent.extras?.getString("custom", "no data") ?: "no data"
        Toast.makeText(this, string, Toast.LENGTH_SHORT).show()

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
            R.id.menu_google_maps -> {
                openFragment(MapsFragment.newInstance())
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