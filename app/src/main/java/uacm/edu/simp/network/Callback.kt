package uacm.edu.simp.network

import java.lang.Exception

interface Callback<T> {
    //Resultado exitoso
    fun onSuccess(result:T?)
    //Resultado fallido
    fun onFailed(exception: Exception)
}