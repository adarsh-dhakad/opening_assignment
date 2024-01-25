package com.opening.network.data.repository

import com.opening.network.data.api.ApiService

class DashBoardRepository(
    private val apiService: ApiService
) {
    suspend fun getData() = apiService.getData()
}