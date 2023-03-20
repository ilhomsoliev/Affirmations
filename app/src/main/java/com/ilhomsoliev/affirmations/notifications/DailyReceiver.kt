package com.ilhomsoliev.affirmations.notifications

import android.R
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.media.RingtoneManager
import android.net.Uri
import androidx.core.app.NotificationCompat
import com.ilhomsoliev.affirmations.app.MainActivity
import com.ilhomsoliev.affirmations.data.local.AffirmationDao
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class DailyReceiver : BroadcastReceiver() {
    var databaseHelper: AffirmationDao? = null
    override fun onReceive(context: Context, intent: Intent?) {
        var quote: String
        //val `when` = System.currentTimeMillis()
        val notificationManager = context
            .getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val notificationIntent = Intent(context, MainActivity::class.java)
        notificationIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        val pendingIntent = PendingIntent.getActivity(
            context, 0,
            notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT
        )
        val alarmSound: Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)

        // get your quote here
        GlobalScope.launch {
            quote = databaseHelper?.getRandomAffirmation()!!.content
            val mNotifyBuilder: NotificationCompat.Builder = NotificationCompat.Builder(
                context
            ).setSmallIcon(R.mipmap.sym_def_app_icon)
                .setContentTitle("My Quotes")
                .setContentText(quote).setSound(alarmSound)
                //.setAutoCancel(true).setWhen(`when`)
                .setLargeIcon(
                    BitmapFactory.decodeResource(
                        context.getResources(),
                        R.mipmap.sym_def_app_icon
                    )
                )
                .setStyle(NotificationCompat.BigTextStyle().bigText(quote))
                .setContentIntent(pendingIntent)
                .setVibrate(
                    longArrayOf(
                        1000,
                        1000,
                        1000,
                        1000,
                        1000
                    )
                ) // Declair VIBRATOR Permission in AndroidManifest.xml
            notificationManager.notify(5, mNotifyBuilder.build())
        }
    }
}