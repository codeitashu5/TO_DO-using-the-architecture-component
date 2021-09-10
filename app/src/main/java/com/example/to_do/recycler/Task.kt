package com.example.to_do.recycler

import com.example.to_do.R

class Task{
        data class CreatedTask(val task:String,val priority:Int,var state:Boolean)

        companion object{
        val image1 = R.drawable.ic_priority_high
        val image2 = R.drawable.ic_low_priority
    }
}

