package com.example.bai34_todolist

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AlertDialog
import com.example.bai34_todolist.databinding.ActivityMainBinding

private lateinit var binding: ActivityMainBinding

class MainActivity : AppCompatActivity() {
    var itemList = ArrayList<String>()
    var fileHelper = FileHelper()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        itemList = fileHelper.readData(this)
        var adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            android.R.id.text1,
            itemList
        )

        binding.lvWork.adapter = adapter

        binding.btnAdd.setOnClickListener {
            var itemName = binding.edtInput.text.toString()
            itemList.add(itemName)
            binding.edtInput.setText("")
            fileHelper.writeData(itemList, applicationContext)
            adapter.notifyDataSetChanged()
        }

        //Xoa cong viec sau khi da hoan thanh
        binding.lvWork.setOnItemClickListener { parent, view, position, id ->
            var alert = AlertDialog.Builder(this)
            alert.setTitle("Delete")
            alert.setMessage("Are you sure you want to delete?")
            alert.setCancelable(true)
            alert.setPositiveButton("Yes", DialogInterface.OnClickListener { dialog, which ->
                itemList.removeAt(position)
                adapter.notifyDataSetChanged()
                //Ghi lai danh sach moi
                fileHelper.writeData(itemList, applicationContext)
            })
            alert.setNegativeButton("No", DialogInterface.OnClickListener { dialog, which ->
                dialog.cancel()
            })
            alert.create()
            alert.show()
        }
    }
}