package com.opening.network.data.api

import com.opening.network.model.DashBoardResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("dashboardNew")
    suspend fun getData(): Response<DashBoardResponse>
}