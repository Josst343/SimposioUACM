package uacm.edu.simp.view.ui.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController

import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_schedule.*
import uacm.edu.simp.R

import uacm.edu.simp.model.Conference
import uacm.edu.simp.view.adapter.ScheduleAdapter
import uacm.edu.simp.view.adapter.ScheduleListener
import uacm.edu.simp.viewmodel.ScheduleViewModel
import java.util.*


class ScheduleFragment:Fragment(), ScheduleListener {
    private lateinit var  scheduleAdapter:ScheduleAdapter
    private lateinit var viewModel: ScheduleViewModel



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_schedule, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel= ViewModelProviders.of(this).get(ScheduleViewModel::class.java)
        viewModel.refresh()

        scheduleAdapter = ScheduleAdapter(this)

        rvSchedule.apply {
            layoutManager = LinearLayoutManager(view.context,LinearLayoutManager.VERTICAL,false)
            adapter = scheduleAdapter
        }
        observeViewModel()

    }

    fun observeViewModel(){
        viewModel.listSchedule.observe(viewLifecycleOwner,Observer<List<Conference>>{schedule ->
            scheduleAdapter.updateData(schedule)

        })
        viewModel.isLoading.observe(viewLifecycleOwner, Observer<Boolean> {
            if(it != null)
                rlBaseSchedule.visibility = View.INVISIBLE
        })
    }

    override fun onConferenceClicked(conference: Conference, position: Int) {

      //  var bundle = bundleOf("conference" to conference)
        val bundle = bundleOf("conference" to conference)
       findNavController().navigate(R.id.scheduleDetailFragmentDialog,bundle)
    }




}