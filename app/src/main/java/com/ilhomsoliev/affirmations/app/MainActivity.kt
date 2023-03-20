package com.ilhomsoliev.affirmations.app

import android.app.*
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.runtime.rememberCoroutineScope
import androidx.work.WorkManager
import com.ilhomsoliev.affirmations.app.theme.AffirmationsTheme
import com.ilhomsoliev.affirmations.data.local.AffirmationDao
import com.ilhomsoliev.affirmations.notifications.*
import com.ilhomsoliev.affirmations.notifications.Notification
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var dao: AffirmationDao

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        createNotificationChannel(this)
        setContent {
            val coroutineScope = rememberCoroutineScope()
            AffirmationsTheme {
                Navigation(onSetAlarm = {startTime: Int ->
                    coroutineScope.launch {
                        scheduleNotification(dao, startTime)
                    }
                }, onCancelAlarm = {
                    cancelAlarm()
                })
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    suspend fun scheduleNotification(
        dao: AffirmationDao,
        hours: Int,
    ) {
        cancelAlarm()
        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val calendar = Calendar.getInstance()
        val intent =
            Intent(this, Notification::class.java)
        val affirmation = dao.getRandomAffirmation()
        val title = affirmation.content
        val message = dao.getCategoryById(affirmation.categoryId)?.name
        intent.putExtra(titleExtra, title)
        intent.putExtra(messageExtra, message)

        val pendingIntent = PendingIntent.getBroadcast(
            this,
            notificationID,
            intent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )
        calendar[Calendar.HOUR_OF_DAY] = hours
        calendar[Calendar.MINUTE] = 0
        calendar[Calendar.SECOND] = 0
        calendar[Calendar.MILLISECOND] = 0
        val time = calendar.timeInMillis
        alarmManager.setInexactRepeating(
            AlarmManager.RTC_WAKEUP,
            time,
            AlarmManager.INTERVAL_DAY,
            pendingIntent
        )
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun cancelAlarm() {
        val intent =
            Intent(this, Notification::class.java)
        val pendingIntent = PendingIntent.getBroadcast(
            this,
            notificationID,
            intent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )
        val alarmManager =
            this.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager.cancel(pendingIntent)
    }
}
