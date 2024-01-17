package com.example.myapplication_homework.model

data class AdviceResponse(val slip: Slip)

data class Slip(val advice: String, val author: String)
