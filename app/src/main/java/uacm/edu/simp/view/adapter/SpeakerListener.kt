package uacm.edu.simp.view.adapter

import uacm.edu.simp.model.Speaker

interface SpeakerListener {
    fun onSpeakerClicked(speaker: Speaker,position:Int)
}