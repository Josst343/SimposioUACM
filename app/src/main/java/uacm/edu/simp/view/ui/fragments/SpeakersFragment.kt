package uacm.edu.simp.view.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.fragment_speakers.*
import uacm.edu.simp.R
import uacm.edu.simp.model.Speaker
import uacm.edu.simp.view.adapter.SpeakerAdapter
import uacm.edu.simp.view.adapter.SpeakerListener
import uacm.edu.simp.viewmodel.SpeakersViewModel



/**
 * A simple [Fragment] subclass.
 * Use the [SpeakersFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SpeakersFragment : Fragment(),SpeakerListener {
   private lateinit var speakerAdapter: SpeakerAdapter
   private lateinit var viewModel:SpeakersViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_speakers, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel =ViewModelProviders.of(this).get(SpeakersViewModel::class.java)
        viewModel.refresh()

        speakerAdapter = SpeakerAdapter(this)

        rvSpeakers.apply {
            layoutManager=GridLayoutManager(context,2)
            adapter = speakerAdapter
        }
        observeViewModel()
    }
    fun observeViewModel(){
        viewModel.listSpeaker.observe(viewLifecycleOwner,Observer<List<Speaker>>{
            speakers->
            speakers.let{
                speakerAdapter.updateData(speakers)
            }
        })
        viewModel.isLoading.observe(viewLifecycleOwner,Observer<Boolean>{
            if(it != null)
                rlBaseSpeaker.visibility = View.INVISIBLE

        })
    }

    override fun onSpeakerClicked(speaker: Speaker, position: Int) {
        //var bundle = bundleOf("speaker"to speaker)
       var bundle= bundleOf("speaker" to speaker)
      findNavController().navigate(R.id.speakersDetailFragmentDialog,bundle)
    }

}