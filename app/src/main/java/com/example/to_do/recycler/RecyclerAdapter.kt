package com.example.to_do.recycler

import android.content.Context
import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.to_do.R
import com.example.to_do.recycler.Task.Companion.image1
import com.example.to_do.recycler.Task.Companion.image2
import kotlinx.coroutines.currentCoroutineContext

class RecyclerAdapter(val list: MutableList<Task.CreatedTask>,
                      val obj : OnCheckBoxClicked
) : RecyclerView.Adapter<RecyclerAdapter.CustomViewHolder>() {
    inner class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
         init {
           val check  = itemView.findViewById<CheckBox>(R.id.checkBox)
             check.setOnCheckedChangeListener { compoundButton, b ->
                 obj.onClick(b,itemView,list,adapterPosition)
             }

            itemView.setOnLongClickListener {
                obj.onLongClick(list,adapterPosition)
            }
         }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.recycler_layout,
            parent,
            false
        )
        return CustomViewHolder(view)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val textView = holder.itemView.findViewById<TextView>(R.id.task)
        val image = holder.itemView.findViewById<ImageView>(R.id.imageView)
        val checkBox = holder.itemView.findViewById<CheckBox>(R.id.checkBox)
        //image view is set on basis of priority only ya
        if(list[position].priority==1)
            image.setImageResource(image1)
        else
            image.setImageResource(image2)

        if(list[position].state==true){
            holder.itemView.setBackgroundResource(R.drawable.frame_recycler)
            checkBox.isChecked = true
        }else{
            holder.itemView.setBackgroundResource(R.drawable.false_case)
            checkBox.isChecked = false
        }

        textView.text = list[position].task
    }
    override fun getItemCount(): Int {
       return list.size
    }
}

interface OnCheckBoxClicked{
    //interface function that will change the color on cliking of the
    fun onClick(r:Boolean,view:View,list:MutableList<Task.CreatedTask>,pos:Int)
    fun onLongClick(list:MutableList<Task.CreatedTask>,pos:Int):Boolean
}