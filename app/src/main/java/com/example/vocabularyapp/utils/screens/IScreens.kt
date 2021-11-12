package com.example.vocabularyapp.utils.screens

import com.github.terrakok.cicerone.Screen

interface IScreens {
    fun main(): Screen
    fun history(): Screen
    fun details(): Screen
}