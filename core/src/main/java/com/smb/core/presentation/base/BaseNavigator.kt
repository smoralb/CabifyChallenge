package com.smb.core.presentation.base

import android.content.Context
import android.content.Intent
import androidx.navigation.ActivityNavigator
import androidx.navigation.NavOptions
import com.smb.core.R

interface BaseNavigator {

    fun navigate(context: Context, intent: Intent) {
        ActivityNavigator(context).apply {
            this.navigate(
                this.createDestination()
                    .setIntent(intent),
                null,
                NavOptions.Builder()
                    .setEnterAnim(R.anim.slide_in_left)
                    .setExitAnim(R.anim.slide_out_left)
                    .setPopEnterAnim(R.anim.slide_in_right)
                    .setPopExitAnim(R.anim.slide_out_right)
                    .build(),
                null
            )
        }
    }

    fun navigateClearTop(context: Context, intent: Intent) {
        ActivityNavigator(context).apply {
            this.navigate(
                this.createDestination()
                    .setIntent(intent),
                null,
                NavOptions.Builder()
                    .setEnterAnim(R.anim.slide_in_right)
                    .setExitAnim(R.anim.slide_out_right)
                    .setPopEnterAnim(R.anim.slide_in_right)
                    .setPopExitAnim(R.anim.slide_out_right)
                    .build(),
                ActivityNavigator.Extras.Builder()
                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    .build()
            )
        }
    }
}