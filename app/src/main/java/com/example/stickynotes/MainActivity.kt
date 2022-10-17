package com.example.stickynotes


import androidx.appcompat.app.AppCompatActivity
import com.example.stickynotes.StickyNote
import android.os.Bundle
import com.example.stickynotes.R
import android.graphics.Typeface
import android.appwidget.AppWidgetManager
import android.content.ComponentName
import android.graphics.Paint
import android.view.View
import android.widget.*
import com.example.stickynotes.AppWidget

class MainActivity : AppCompatActivity() {
    lateinit var noteEdt: EditText
    lateinit var increaseSizeBtn: Button
    lateinit var decreaseSizeBtn: Button
    lateinit var boldBtn: Button
    lateinit var underLineBtn: Button
    lateinit var italicBtn: Button
    lateinit var sizeTV: TextView
    var note = StickyNote()
    var currentSize = 0f
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        noteEdt = findViewById(R.id.idEdit)
        increaseSizeBtn = findViewById(R.id.idBtnIncreaseSize)
        decreaseSizeBtn = findViewById(R.id.idBtnReduceSize)
        boldBtn = findViewById(R.id.idBtnBold)
        underLineBtn = findViewById(R.id.idBtnUnderline)
        italicBtn = findViewById(R.id.idBtnItalics)
        sizeTV = findViewById(R.id.idTVSize)
        currentSize = noteEdt.getTextSize()
        sizeTV.setText("" + currentSize)
        increaseSizeBtn.setOnClickListener(View.OnClickListener {
            sizeTV.setText("" + currentSize)
            currentSize++
            noteEdt.setTextSize(currentSize)
        })
        decreaseSizeBtn.setOnClickListener(View.OnClickListener {
            sizeTV.setText("" + currentSize)
            currentSize--
            noteEdt.setTextSize(currentSize)
        })
        boldBtn.setOnClickListener(View.OnClickListener {
            italicBtn.setTextColor(resources.getColor(R.color.white))
            italicBtn.setBackgroundColor(resources.getColor(R.color.blue))
            if (noteEdt.getTypeface().isBold) {
                noteEdt.setTypeface(Typeface.DEFAULT)
                boldBtn.setTextColor(resources.getColor(R.color.white))
                boldBtn.setBackgroundColor(resources.getColor(R.color.blue))
            } else {
                boldBtn.setTextColor(resources.getColor(R.color.blue))
                boldBtn.setBackgroundColor(resources.getColor(R.color.white))
                noteEdt.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD))
            }
        })
        underLineBtn.setOnClickListener(View.OnClickListener {
            if (noteEdt.getPaintFlags() == 8) {
                underLineBtn.setTextColor(resources.getColor(R.color.white))
                underLineBtn.setBackgroundColor(resources.getColor(R.color.blue))
                noteEdt.setPaintFlags(noteEdt.getPaintFlags() and Paint.UNDERLINE_TEXT_FLAG.inv())
            } else {
                underLineBtn.setTextColor(resources.getColor(R.color.blue))
                underLineBtn.setBackgroundColor(resources.getColor(R.color.white))
                noteEdt.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG)
            }
        })
        italicBtn.setOnClickListener(View.OnClickListener {
            boldBtn.setTextColor(resources.getColor(R.color.white))
            boldBtn.setBackgroundColor(resources.getColor(R.color.blue))
            if (noteEdt.getTypeface().isItalic) {
                noteEdt.setTypeface(Typeface.DEFAULT)
                italicBtn.setTextColor(resources.getColor(R.color.white))
                italicBtn.setBackgroundColor(resources.getColor(R.color.blue))
            } else {
                italicBtn.setTextColor(resources.getColor(R.color.blue))
                italicBtn.setBackgroundColor(resources.getColor(R.color.blue))
                noteEdt.setTypeface(Typeface.defaultFromStyle(Typeface.ITALIC))
            }
        })
    }

    fun SaveButton(view: View?) {
        note.setStick(noteEdt!!.text.toString(), this)
        updateWidget()
        Toast.makeText(this, "Note has been updated...", Toast.LENGTH_SHORT).show()
    }

    fun updateWidget() {
        val appWidgetManager = AppWidgetManager.getInstance(this)
        val remoteViews = RemoteViews(this.packageName, R.layout.widget_layout)
        val thisWidget = ComponentName(this, AppWidget::class.java)
        remoteViews.setTextViewText(R.id.idTVWidget, noteEdt!!.text.toString())
        appWidgetManager.updateAppWidget(thisWidget, remoteViews)
    }
}