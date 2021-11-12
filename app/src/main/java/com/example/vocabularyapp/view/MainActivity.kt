package com.example.vocabularyapp.view

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.marginStart
import androidx.core.view.marginTop
import androidx.fragment.app.Fragment
import com.example.vocabularyapp.AppState
import com.example.vocabularyapp.R
import com.example.vocabularyapp.model.DataModel
import com.example.vocabularyapp.utils.screens.IScreens
import com.example.vocabularyapp.viewModel.WordListViewModel
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.AppNavigator
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
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
                val editText = layoutInflater.inflate(R.layout.edit_text_search, null)
                alertDialog
                    .setTitle("Поиск")
                    .setMessage("Введите слово для поиска")
                    .setView(editText)
                    .setPositiveButton("OK") { _, _ ->
                        //получить текст и запросить в БД
                        val text = editText.findViewById<EditText>(R.id.edit_query).text.toString()
                        replaceFragment(R.id.main_activity_container, DetailsFragment.newInstance(text))
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

    private fun initToolbar() {
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
    }
}