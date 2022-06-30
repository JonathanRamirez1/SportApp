package com.cursosandroidant.sports.retrofit

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/****
 * Project: Sports
 * From: com.cursosandroidant.sports.retrofit
 * Created by Alain Nicolás Tello on 01/10/21 at 9:32
 * All rights reserved 2021.
 *
 * All my Udemy Courses:
 * https://www.udemy.com/user/alain-nicolas-tello/
 * Web: www.alainnicolastello.com
 ***/
interface WeatherService {
    @GET("data/2.5/weather")
    suspend fun getWeatherById(
        @Query("id") lon: Long,
        @Query("units") units: String?,
        @Query("appid") appid: String?): WeatherEntity
}