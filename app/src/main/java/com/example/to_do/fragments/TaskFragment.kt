package com.example.to_do.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.to_do.R
import com.example.to_do.databinding.FragmentTaskBinding


class TaskFragment : Fragment() {
   private var _binding : FragmentTaskBinding? = null
   private val binding  get() = _binding!!

   override fun onCreateView(
      inflater: LayoutInflater,
      container: ViewGroup?,
      savedInstanceState: Bundle?
   ): View? {
      _binding = FragmentTaskBinding.inflate(inflater,
                     container,
                    false)

         return binding.root
   }


   override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
      //code goes here
      binding.button.setOnClickListener {
            if(binding.priority.text.toString().length==0||binding.task.text.toString().length==0){
               Toast.makeText(requireContext(), "Task or Priority is empty", Toast.LENGTH_SHORT).show()
            }
            else{
               val action = TaskFragmentDirections.actionTaskFragmentToHomeFragment(binding.task.text.toString(),binding.priority.text.toString().toInt())
               findNavController().navigate(action)
            }


      }
   }

   override fun onDestroyView() {
      super.onDestroyView()
      _binding = null
   }


}