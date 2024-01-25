package com.opening.openingassignment.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.opening.network.data.repository.DashBoardRepository
import com.opening.network.data.response.GenericResponse
import com.opening.network.model.DashBoardResponse
import kotlinx.coroutines.launch

class DashBoardViewModel(private val dashBoardRepository: DashBoardRepository): ViewModel() {
    private val _dashBoardData = MutableLiveData<GenericResponse<DashBoardResponse>>()
    val dashBoardData get() =  _dashBoardData

     fun getDashBoardData() {
         viewModelScope.launch {
             try {
                 _dashBoardData.value = GenericResponse.Loading
                 val response = dashBoardRepository.getData()
                 if (response.isSuccessful) {
                     _dashBoardData.value = GenericResponse.Success(response.body()!!)
                 } else {
                     _dashBoardData.value = GenericResponse.Error(response.message())
                 }
             } catch (e: Exception) {
                 _dashBoardData.value = GenericResponse.Error(e.localizedMessage ?: "An error occurred")
             }
         }
    }
}