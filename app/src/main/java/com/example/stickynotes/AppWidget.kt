package com.example.stickynotes

import android.appwidget.AppWidgetProvider
import android.appwidget.AppWidgetManager
import android.content.Intent
import com.example.stickynotes.MainActivity
import android.app.PendingIntent
import android.content.Context
import android.widget.RemoteViews
import com.example.stickynotes.R

// Implementation of App Widget functionality
class AppWidget : AppWidgetProvider() {
    // Create an Intent to launch activity
    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {

        // Perform this loop procedure for each App Widget
        // that belongs to this provider
        for (appWidgetId in appWidgetIds) {

            // Create an Intent to launch MainActivity
            val launchActivity = Intent(context, MainActivity::class.java)

            // Attaching a Pending Intent
            // to trigger it when
            // application is not alive
            val pendingIntent = PendingIntent.getActivity(context, 0, launchActivity, 0)

            // Get the layout for the App Widget and attach
            // an on-click listener to the button
            val remoteViews = RemoteViews(context.packageName, R.layout.widget_layout)
            remoteViews.setOnClickPendingIntent(R.id.widgetLayout, pendingIntent)

            // Tell the AppWidgetManager to perform an
            // update on the current app widget
            appWidgetManager.updateAppWidget(appWidgetId, remoteViews)
        }
    }
}