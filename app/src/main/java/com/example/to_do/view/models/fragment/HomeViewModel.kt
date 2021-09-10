package com.example.to_do.view.models.fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.to_do.recycler.Task

class HomeViewModel : ViewModel() {
    //live data for the task
    //I might have to keep the list outside and add the the data to reflect the data changes


    private var _listOfTask  =  MutableLiveData<MutableList<Task.CreatedTask>>()
    public val listOfTask:LiveData<MutableList<Task.CreatedTask>> get()  = _listOfTask

   //using init block providing the initial values
    init {
        _listOfTask.value = mutableListOf()
    }
    //to add the element in the list
    fun addTask(task:Task.CreatedTask)
    {
        _listOfTask.value?.add(0,task)
        //observer will get trigred only if we reassigne the value of list in
        _listOfTask.value = _listOfTask.value
    }
    //delete the element from the list
    fun deleteTask(pos:Int){
        _listOfTask.value?.removeAt(pos)

        _listOfTask.value = _listOfTask.value
    }

}