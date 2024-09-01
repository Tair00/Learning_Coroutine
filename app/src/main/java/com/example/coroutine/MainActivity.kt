package com.example.coroutine

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.example.compositionapp.R
import com.example.compositionapp.databinding.ActivityMainBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private val handler = object: Handler(){
       override fun handleMessage(msg: Message){
           super.handleMessage(msg)
           println("HANDLE_MSG $msg")
       }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.buttonLoad.setOnClickListener {
            lifecycleScope.launch {
                loadData()
            }
        }
        handler.sendMessage(Message.obtain(handler,0,17))
    }

    private fun loadData() {
        binding.progress.isVisible = true
        binding.buttonLoad.isEnabled = false
       val city =  loadCity()
            binding.tvLocation.text = city
           val temp = loadTemperature(city)
                binding.tvTemperature.text = temp.toString()
                binding.progress.isVisible = false
                binding.buttonLoad.isEnabled = true


    }

    private suspend fun loadCity(): String {
       delay(5000)
        return "Moscow"
    }

    private suspend fun loadTemperature(city: String) : Int {

               Toast.makeText(
                   this,
                   getString(R.string.loading_temperature_toast, city),
                   Toast.LENGTH_SHORT
               ).show()

               delay(5000)

                   return 17
               }


    }
