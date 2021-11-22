package com.example.vocabularyapp.utils

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import android.widget.RemoteViews
import androidx.annotation.RequiresApi
import com.example.repository.remote.DoggyApi
import com.example.vocabularyapp.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL

private const val MY_ACTION = "MY_ACTION"

class MyWidget : AppWidgetProvider() {

    private val doggyApi: DoggyApi by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://dog.ceo/api/breeds/image/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofit.create(DoggyApi::class.java)
    }

    private val scope = CoroutineScope(Dispatchers.IO)

    override fun onReceive(context: Context, intent: Intent) {
        super.onReceive(context, intent)
        if (intent.action != MY_ACTION) return

        val appWidgetManager = AppWidgetManager.getInstance(context)
        val componentName = ComponentName(context, MyWidget::class.java)

        RemoteViews(
            context.packageName,
            R.layout.example_appwidget
        ).apply {
            scope.launch {
                val url = doggyApi.getRandomDog().message
                val bitmap = getBitmapFromURL(url)
                setImageViewBitmap(R.id.widget_image_view, bitmap)
                appWidgetManager.updateAppWidget(componentName, this@apply)
            }
        }

    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        super.onUpdate(context, appWidgetManager, appWidgetIds)

        appWidgetIds.forEach { appWidgetId ->

            val intent = Intent(context, MyWidget::class.java).apply {
                action = MY_ACTION
            }
            val pendingIntent = PendingIntent.getBroadcast(context, 0, intent,PendingIntent.FLAG_IMMUTABLE)

            RemoteViews(
                context.packageName,
                R.layout.example_appwidget
            ).apply {
                setOnClickPendingIntent(R.id.widget_image_view, pendingIntent)
                scope.launch {
                    val url = doggyApi.getRandomDog().message
                    val bitmap = getBitmapFromURL(url)
                    setImageViewBitmap(R.id.widget_image_view, bitmap)
                    appWidgetManager.updateAppWidget(appWidgetId, this@apply)
                }
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onAppWidgetOptionsChanged(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetId: Int,
        newOptions: Bundle?
    ) {
        super.onAppWidgetOptionsChanged(context, appWidgetManager, appWidgetId, newOptions)
        val intent = Intent(context, MyWidget::class.java).apply {
            action = MY_ACTION
        }
        val pendingIntent = PendingIntent.getBroadcast(context, 0, intent,PendingIntent.FLAG_IMMUTABLE)

        RemoteViews(
            context.packageName,
            R.layout.example_appwidget
        ).apply {
            setOnClickPendingIntent(R.id.widget_image_view, pendingIntent)
        }
    }

    private fun getBitmapFromURL(src: String?): Bitmap? {
        return try {
            val url = URL(src)
            val connection: HttpURLConnection = url.openConnection() as HttpURLConnection
            connection.doInput = true
            connection.connect()
            val input: InputStream = connection.inputStream
            BitmapFactory.decodeStream(input)
        } catch (e: IOException) {
            println(e.message)
            null
        }
    }

}