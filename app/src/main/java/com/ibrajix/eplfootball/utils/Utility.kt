package com.ibrajix.eplfootball.utils

import android.content.Context
import android.os.Build
import android.view.View
import androidx.annotation.ColorInt
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import com.ibrajix.eplfootball.R
import java.time.LocalDate
import java.time.Period

object Utility {

    //display SnackBar
    fun displaySnackBar(view: View, s: String, context: Context) {
        Snackbar.make(view, s, Snackbar.LENGTH_LONG)
            .withColor(ContextCompat.getColor(context, R.color.main_red))
            .setTextColor(ContextCompat.getColor(context, R.color.white))
            .show()
    }
    private fun Snackbar.withColor(@ColorInt colorInt: Int): Snackbar {
        this.view.setBackgroundColor(colorInt)
        return this
    }

    //calculate age
    fun getBirthYearAfterDash(string: String) = string.substring(0, string.indexOf('-'))


    @RequiresApi(Build.VERSION_CODES.O)
    fun calculateAge(birthDate: LocalDate?, currentDate: LocalDate?): Int {
        return if (birthDate != null && currentDate != null) {
            Period.between(birthDate, currentDate).years
        } else {
            0
        }
    }


}