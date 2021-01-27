package uacm.edu.simp.view.adapter


import android.os.Bundle
import uacm.edu.simp.model.Conference

interface ScheduleListener {
    fun onConferenceClicked(conference: Conference, position: Int)

}