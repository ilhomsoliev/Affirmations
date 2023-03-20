package com.ilhomsoliev.affirmations.widgets

import android.app.PendingIntent
import android.app.PendingIntent.FLAG_IMMUTABLE
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews
import com.ilhomsoliev.affirmations.R
import com.ilhomsoliev.affirmations.app.MainActivity
import com.ilhomsoliev.affirmations.data.local.AffirmationDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class AffirmationWidget : AppWidgetProvider() {
    val updateButton = "updateButton"
    val favoriteButton = "favoriteButton"
    val affirmationIdExtra = "AffirmationId"
    private lateinit var database: AffirmationDatabase

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        database = AffirmationDatabase(context)
        for (appWidgetId in appWidgetIds) {

            val intent = Intent(context, MainActivity::class.java)
            val pendingIntent = PendingIntent.getActivity(context, 0, intent, FLAG_IMMUTABLE)
            val views = RemoteViews(
                context.packageName,
                R.layout.affirmation_widget
            )
            views.setOnClickPendingIntent(
                R.id.appwidget_text,
                pendingIntent
            )
            CoroutineScope(Dispatchers.Main).launch {
                val affirmation = database.getAffirmationDao().getRandomAffirmation()
                val i = Intent(context, AffirmationWidget::class.java)
                i.action = updateButton
                // i.putExtra(affirmationIdExtra, affirmation.id)
                val pi = PendingIntent.getBroadcast(context, 0, i, FLAG_IMMUTABLE)
                views.setOnClickPendingIntent(R.id.update_button, pi)
                val iFav = Intent(context, AffirmationWidget::class.java)
                iFav.action = favoriteButton
                // i.putExtra(affirmationIdExtra, affirmation.id)
                val piFav = PendingIntent.getBroadcast(context, 0, i, FLAG_IMMUTABLE)
                views.setOnClickPendingIntent(R.id.like_button, piFav)

                views.setTextViewText(
                    R.id.appwidget_text,
                    affirmation.content
                )
                appWidgetManager.updateAppWidget(appWidgetId, views)
                /*if (affirmation.isFavorite == 1) {
                    views.setImageViewResource(R.id.like_button, R.drawable.baseline_favorite_24)
                } else {
                    views.setImageViewResource(
                        R.id.like_button,
                        R.drawable.baseline_favorite_border_24
                    )
                }*/
            }
        }
    }

    override fun onEnabled(context: Context) {
        database = AffirmationDatabase(context)
        val widgetManager = AppWidgetManager.getInstance(context.applicationContext)
        widgetManager.updateAppWidget(
            widgetManager.getAppWidgetIds(
                ComponentName(
                    context.applicationContext.packageName,
                    AffirmationWidget::class.java.name
                )
            ),
            RemoteViews(
                context.packageName,
                R.layout.affirmation_widget
            )
        )
    }

    override fun onDisabled(context: Context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        database = AffirmationDatabase(context!!)
        val appWidgetManager = AppWidgetManager.getInstance(context.applicationContext)
        val views = RemoteViews(context.packageName, R.layout.affirmation_widget)
        when (intent?.action) {
            updateButton -> {
                CoroutineScope(Dispatchers.Main).launch {
                    views.setTextViewText(
                        R.id.appwidget_text,
                        database.getAffirmationDao().getRandomAffirmation().content
                    )
                    appWidgetManager.updateAppWidget(
                        appWidgetManager.getAppWidgetIds(
                            ComponentName(
                                context.applicationContext.packageName,
                                AffirmationWidget::class.java.name
                            )
                        ),
                        views
                    )
                }
            }
            favoriteButton -> {
                CoroutineScope(Dispatchers.Main).launch {
                    val affirmationId = intent.getIntExtra(affirmationIdExtra, 0)
                    val affirmation =
                        database.getAffirmationDao().getAffirmationById(affirmationId)!!
                    database.getAffirmationDao().insertAffirmation(
                        affirmation.copy(
                            isFavorite = if (affirmation.isFavorite == 1) 0 else 1
                        )
                    )
                }

            }
        }
        super.onReceive(context, intent)
    }
}