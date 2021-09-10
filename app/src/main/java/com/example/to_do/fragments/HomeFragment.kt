package com.example.to_do.fragments
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.to_do.R
import com.example.to_do.databinding.FragmentHomeBinding
import com.example.to_do.recycler.OnCheckBoxClicked
import com.example.to_do.recycler.RecyclerAdapter
import com.example.to_do.recycler.Task
import com.example.to_do.view.models.fragment.HomeViewModel

//Fragment creation and logic bhind it


class HomeFragment : Fragment(),OnCheckBoxClicked {
    //here we create the binding property it in able us to use the view in the layout
    private val viewModel : HomeViewModel by activityViewModels()
    private var _binding: FragmentHomeBinding? = null
    private val binding get()  = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

       _binding = FragmentHomeBinding.inflate(inflater,
           container,
           false
       )

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        //here we do all our work
        updateRecycler()


        //now the observer is getin trigred
        viewModel.listOfTask.observe(viewLifecycleOwner,{ newList->
            updateRecycler()
        })



        binding.floatingActionButton.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToTaskFragment()
            findNavController().navigate(action)
        }


        arguments?.let {
            val str_task = it.getString("task"," ")
            val priority  = it.getInt("priority",-1)

            if(str_task!=" ")
            viewModel.addTask(Task.CreatedTask(str_task!!, priority,false))
        }
    }

    //used many time in the whole process
    private fun updateRecycler() {
        binding.recyclerView.adapter = RecyclerAdapter(viewModel.listOfTask.value!!,this)
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClick(r: Boolean, view: View,list: MutableList<Task.CreatedTask>,pos:Int) {
        if(r==true) {
            view.background = resources.getDrawable(R.drawable.frame_recycler)
            list[pos].state = true
        }

        else{
            view.background = resources.getDrawable(R.drawable.false_case)
            list[pos].state = false
        }

    }

    override fun onLongClick(list: MutableList<Task.CreatedTask>, pos: Int):Boolean {
       viewModel.deleteTask(pos)
        return true
    }

}