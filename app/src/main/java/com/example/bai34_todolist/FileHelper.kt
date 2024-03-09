package com.example.bai34_todolist

import android.content.Context
import java.io.FileInputStream
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.ObjectInputStream
import java.io.ObjectOutputStream

@Suppress("UNCHECKED_CAST")
class FileHelper {
    val FILENAME = "listinfo.dat"

    //Ghi tep
    fun writeData(item: ArrayList<String>, context: Context) {
        val fos: FileOutputStream = context.openFileOutput(FILENAME, Context.MODE_PRIVATE)
        val oos = ObjectOutputStream(fos)
        oos.writeObject(item)
        oos.close()
    }

    //Doc tep
    fun readData(context: Context): ArrayList<String> {
        var itemList: ArrayList<String>
        try {
            val fis: FileInputStream = context.openFileInput(FILENAME)
            val ois = ObjectInputStream(fis)
            itemList = ois.readObject() as ArrayList<String>
            ois.close()
        } catch (e: FileNotFoundException) {
            itemList = ArrayList()
        }

        return itemList
    }
}