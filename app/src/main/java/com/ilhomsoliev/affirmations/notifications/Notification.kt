package com.ilhomsoliev.affirmations.notifications

import android.app.*
import android.app.Notification
import android.app.PendingIntent.*
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.activity.ComponentActivity
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.ilhomsoliev.affirmations.R
import com.ilhomsoliev.affirmations.app.MainActivity
import java.util.*


const val notificationID = 1
const val channelID = "channel1"
const val titleExtra = "titleExtra"
const val messageExtra = "messageExtra"

class Notification : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val tapResultIntent = Intent(context, MainActivity::class.java)
        tapResultIntent.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
        val pendingIntent: PendingIntent =
            getActivity(context, 0, tapResultIntent, FLAG_UPDATE_CURRENT or FLAG_IMMUTABLE)

        val notification = NotificationCompat.Builder(context, channelID)
            .setSmallIcon(R.drawable.baseline_tag_faces_24)
            .setContentTitle(intent.getStringExtra(messageExtra))
            .setStyle(NotificationCompat.BigTextStyle().bigText(intent.getStringExtra(titleExtra)))
            .setContentIntent(pendingIntent)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .build()

        val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        manager.notify(notificationID, notification)
    }
}
/*

@RequiresApi(Build.VERSION_CODES.M)
suspend fun scheduleNotification(
    context: Context,
    howMany: Int,
    startTime: Int,
    endTime: Int,
    dao: AffirmationDao,
) {
    val interval = (endTime - startTime) * 60 / howMany
    val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
    val calendar = Calendar.getInstance()
    repeat(howMany) {
        val intent =
            Intent(context, com.ilhomsoliev.affirmations.notifications.Notification::class.java)
        val title = dao.getRandomAffirmation().content
        val message = ""
        intent.putExtra(titleExtra, title)
        intent.putExtra(messageExtra, message)

        val pendingIntent = PendingIntent.getBroadcast(
            context,
            notificationID + (it - 1),
            intent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )

            val timeToSend = (it - 1) * interval + (startTime * 60)
        Log.d("Hello123", (timeToSend / 60).toString() + (timeToSend % 60).toString())
        calendar[Calendar.HOUR_OF_DAY] = timeToSend / 60
        calendar[Calendar.MINUTE] = timeToSend % 60
        calendar[Calendar.SECOND] = 0
        calendar[Calendar.MILLISECOND] = 0
        val time = calendar.timeInMillis

        alarmManager.setInexactRepeating(
            AlarmManager.ELAPSED_REALTIME_WAKEUP,
            time,
            AlarmManager.INTERVAL_DAY,
            pendingIntent
        )
    }
}
*/

@RequiresApi(Build.VERSION_CODES.O)
fun createNotificationChannel(context: Context) {
    val name = "Notification Channel"
    val desc = "A Description of the Channel"
    val importance = NotificationManager.IMPORTANCE_DEFAULT
    val channel = NotificationChannel(channelID, name, importance)
    channel.description = desc
    val notificationManager =
        context.getSystemService(ComponentActivity.NOTIFICATION_SERVICE) as NotificationManager
    notificationManager.createNotificationChannel(channel)
}