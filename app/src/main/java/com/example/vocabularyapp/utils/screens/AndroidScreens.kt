package com.example.vocabularyapp.utils.screens

import com.example.vocabularyapp.view.WordListFragment
import com.github.terrakok.cicerone.Screen
import com.github.terrakok.cicerone.androidx.FragmentScreen

class AndroidScreens: IScreens {
    override fun main(): Screen = FragmentScreen { WordListFragment.newInstance() }

    override fun history(): Screen {
        TODO("Not yet implemented")
    }

    override fun details(): Screen {
        TODO("Not yet implemented")
    }
}