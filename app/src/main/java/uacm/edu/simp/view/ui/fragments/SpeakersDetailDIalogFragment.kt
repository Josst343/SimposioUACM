package uacm.edu.simp.view.ui.fragments


import android.graphics.Color

import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.fragment_speakers_detail_d_ialog.*
import kotlinx.android.synthetic.main.fragment_speakers_detail_d_ialog.view.*
import kotlinx.android.synthetic.main.item_speaker.*
import kotlinx.android.synthetic.main.item_speaker.view.*
import uacm.edu.simp.R
import uacm.edu.simp.model.Speaker


class SpeakersDetailDIalogFragment : DialogFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.FullScreenDialogStyle)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_speakers_detail_d_ialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbarSpeaker.navigationIcon = ContextCompat.getDrawable(view.context, R.drawable.ic_close)
        toolbarSpeaker.setTitleTextColor(Color.WHITE)
        toolbarSpeaker.setNavigationOnClickListener {
            dismiss()
        }
        val speaker = arguments?.getSerializable("speaker") as Speaker

        toolbarSpeaker.title = speaker.name

        tvItemSpeakerDetailName.text = speaker.name
        tvItemSpeakerDetalJob.text = speaker.workplace
        tvItemSpeakerJobTitle.text = speaker.jobtitle
        tvItemSpeakerDetailConference.text = speaker.biography

        Glide.with(this)
            .load(speaker.image)
            .apply(RequestOptions.circleCropTransform())
            .into(ivImageSpeaker)

    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
    }
}