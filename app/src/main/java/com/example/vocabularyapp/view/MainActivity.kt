package com.example.vocabularyapp.view

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.marginStart
import androidx.core.view.marginTop
import androidx.fragment.app.Fragment
import com.example.vocabularyapp.R
import com.example.vocabularyapp.utils.screens.IScreens
import com.example.vocabularyapp.viewModel.MainViewModel
import com.example.vocabularyapp.viewModel.WordListViewModel
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.AppNavigator
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(){

    private val model: MainViewModel by viewModel()
    private val navigatorHolder by inject<NavigatorHolder>()
    private val navigator = AppNavigator(this, android.R.id.content)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        model.subscribe().observe(this, { })
        initToolbar()
        savedInstanceState ?: replaceFragment(R.id.main_activity_container, WordListFragment.newInstance())
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_search -> {
                val alertDialog = AlertDialog.Builder(this)
                alertDialog
                    .setTitle("Поиск")
                    .setMessage("Введите слово для поиска")
                    .setView(R.layout.edit_text_search)
                    .setPositiveButton("OK") { _, _ ->
                        //получить текст и запросить в БД
                        val query = findViewById<EditText>(R.id.edit_query).text
                        replaceFragment(R.id.main_activity_container, DetailsFragment.newInstance())
                    }
                    .setNegativeButton("Отмена") { dialog, _ ->
                        dialog.dismiss()
                    }
                    .show()
                return true
            }
            R.id.action_history -> {
                replaceFragment(R.id.main_activity_container, HistoryFragment.newInstance())
                return true
            }
        }
        return true
    }

    private fun replaceFragment(layout: Int, fragment: Fragment) {
        supportFragmentManager.beginTransaction().addToBackStack(null).replace(layout, fragment).commit()
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        navigatorHolder.removeNavigator()
    }

    private fun initToolbar() {
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
    }
}