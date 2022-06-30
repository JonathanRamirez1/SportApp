package com.cursosandroidant.sports.retrofit

/****
 * Project: Sports
 * From: com.cursosandroidant.sports.retrofit
 * Created by Alain Nicolás Tello on 01/10/21 at 9:30
 * All rights reserved 2021.
 *
 * All my Udemy Courses:
 * https://www.udemy.com/user/alain-nicolas-tello/
 * Web: www.alainnicolastello.com
 ***/
data class WeatherEntity(val base: String,
                         val main: Main,
                         val sys: Sys,
                         val id: Int,
                         val name: String)
