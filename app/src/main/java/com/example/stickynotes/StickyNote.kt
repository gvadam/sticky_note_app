package com.example.stickynotes

import android.content.Context
import java.io.*
import java.lang.StringBuilder

class StickyNote {
    // this function will return
    // the content from the text
    // file(if any)
    fun getStick(context: Context): String {

        // get the file from path
        val fileEvents = File(context.filesDir.path + "/gfg.txt")

        // create a StringBuilder to store the text from file
        val text = StringBuilder()
        try {
            // use the BufferedReader
            // to Read the file
            // efficiently
            val br = BufferedReader(FileReader(fileEvents))
            var line: String?

            // read a single line at a time
            // append newline character after each line
            while (br.readLine().also { line = it } != null) {
                text.append(line)
                text.append('\n')
            }

            // close the BufferedReader
            br.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }

        // finally return the
        // string i.e. the retrieved data
        // from file
        return text.toString()
    }

    // this function saves the new
    // content in the file if it
    // exists or will create a new one
    fun setStick(textToBeSaved: String, context: Context) {

        // create the FileOutputStream
        // to efficiently write the file
        var fos: FileOutputStream? = null
        try {
            // get the file from storage
            fos = context.applicationContext.openFileOutput("gfg.txt", Context.MODE_PRIVATE)

            // write to the file at once
            fos.write(textToBeSaved.toByteArray())
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            if (fos != null) {
                try {
                    fos.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        }
    }
}