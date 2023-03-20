package com.ilhomsoliev.affirmations.core

import android.content.Context
import android.content.Intent
import androidx.core.content.ContextCompat.startActivity
import com.ilhomsoliev.affirmations.R
import com.ilhomsoliev.affirmations.core.model.Language
import java.text.SimpleDateFormat
import java.util.*

object Helper {
    fun formatDate(timeInMillis: Long): String {
        val dateFormat = SimpleDateFormat("EEE, d MMMM yyyy", Locale.ENGLISH)
        return dateFormat.format(Date(timeInMillis))
    }

    fun shareText(context: Context, text: String) {
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, text)
            type = "text/plain"
        }
        val shareIntent = Intent.createChooser(sendIntent, null)
        context.startActivity(shareIntent)
    }

    fun getLanguages(): List<Language> {
        val languages = mutableListOf<Language>()
        languages.add(Language(id = 1, "Arabic"))
        languages.add(Language(id = 2, "Deutsch"))
        languages.add(Language(id = 3, "English"))
        languages.add(Language(id = 4, "Spanish"))
        languages.add(Language(id = 5, "French"))
        languages.add(Language(id = 6, "Italian"))
        languages.add(Language(id = 7, "Japanese"))
        languages.add(Language(id = 8, "Korean"))
        languages.add(Language(id = 9, "Dutch"))
        languages.add(Language(id = 10, "Portuguese"))
        languages.add(Language(id = 11, "Russian"))
        languages.add(Language(id = 12, "Swedish"))
        languages.add(Language(id = 13, "Thai"))
        languages.add(Language(id = 14, "Turkish"))
        languages.add(Language(id = 15, "Chinese"))
        return languages
    }

    fun getAffirmations(): List<String> = listOf(
        "I forgive myself",
        "I have the power to create change",
        "I love and treasure my body",
        "I am present in every moment",
        "I am worthy of my desires",
        "I give and receive love freely",
        "I speak kindly to myself and others",
        "I am beautiful and strong",
        "I am a magnet for healthy, uplifting, and empowering energy",
        "I choose to be grateful for all that I have",
    ).map { it.trim() }

    fun getBackgroundFromCategoryId(id: Int): Int {
        return when (id) {
            1 -> R.drawable.bg_1
            2 -> R.drawable.bg_2
            3 -> R.drawable.bg_3
            4 -> R.drawable.bg_4
            5 -> R.drawable.bg_5
            6 -> R.drawable.bg_6
            7 -> R.drawable.bg_7
            8 -> R.drawable.bg_8
            9 -> R.drawable.bg_9
            10 -> R.drawable.bg_10
            11 -> R.drawable.bg_11
            12 -> R.drawable.bg_12
            13 -> R.drawable.bg_13
            14 -> R.drawable.bg_14
            15 -> R.drawable.bg_15
            16 -> R.drawable.bg_16
            17 -> R.drawable.bg_17
            18 -> R.drawable.bg_18
            19 -> R.drawable.bg_19
            20 -> R.drawable.bg_20
            21 -> R.drawable.bg_21
            22 -> R.drawable.bg_22
            24 -> R.drawable.bg_24
            25 -> R.drawable.bg_25
            26 -> R.drawable.bg_23
            36 -> R.drawable.bg_26
            27 -> R.drawable.bg_27
            28 -> R.drawable.bg_28
            29 -> R.drawable.bg_29
            30 -> R.drawable.bg_30
            31 -> R.drawable.bg_31
            32 -> R.drawable.bg_32
            33 -> R.drawable.bg_33
            34 -> R.drawable.bg_34
            35 -> R.drawable.bg_35
            else -> {
                R.drawable.bg_25
            }
        }
    }
}