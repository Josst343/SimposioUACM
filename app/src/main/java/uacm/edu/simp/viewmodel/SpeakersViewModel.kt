package uacm.edu.simp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import uacm.edu.simp.model.Speaker
import uacm.edu.simp.network.Callback
import uacm.edu.simp.network.FirestoreService
import java.lang.Exception

class SpeakersViewModel: ViewModel() {
    //Extender el ciclo de vida de los fragmentos para que cuando rote la aplicacion no se pieran los estilos que previamente diseñamos
    val firestoreService = FirestoreService()
    val listSpeaker: MutableLiveData<List<Speaker>> = MutableLiveData()
    var isLoading = MutableLiveData<Boolean>()

    fun refresh() {
        getSpeakerFromFirebase()
    }

    fun getSpeakerFromFirebase() {
        firestoreService.getSpeakers(object : Callback<List<Speaker>> {
            override fun onSuccess(result: List<Speaker>?) {
                listSpeaker.postValue(result)
                proccessFinshed()
            }


            override fun onFailed(exception: Exception) {
                proccessFinshed()
            }
        })

    }

    fun proccessFinshed() {
        isLoading.value = true
    }

}