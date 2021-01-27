package uacm.edu.simp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import uacm.edu.simp.model.Conference
import uacm.edu.simp.network.Callback
import uacm.edu.simp.network.FirestoreService
import java.lang.Exception

class ScheduleViewModel: ViewModel() {
    //Extender el ciclo de vida de los fragmentos para que cuando rote la aplicacion no se pieran los estilos que previamente diseñamos
    private val firestoreService = FirestoreService()
    val listSchedule: MutableLiveData<List<Conference>> = MutableLiveData()
    var isLoading = MutableLiveData<Boolean>()

    fun refresh() {
        getScheduleFromFirebase()
    }

    fun getScheduleFromFirebase() {
        firestoreService.getSchedule(object : Callback<List<Conference>>{
            override fun onSuccess(result: List<Conference>?) {
                listSchedule.postValue(result)
                processFinished()
            }

            override fun onFailed(exception: Exception) {
                processFinished()

            }
        })

    }

    fun processFinished() {
        isLoading.value = true
    }

}