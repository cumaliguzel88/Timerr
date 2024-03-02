package com.cumaliguzel.timer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import com.cumaliguzel.timer.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    var number=0
    var runnable : Runnable = Runnable {}//boş bir runnable objesi
    var handler : Handler= Handler(Looper.getMainLooper())//handler objesi ruanle için yardımcı bir sınıf
    var  minus=0
    var hours=0
    override fun onCreate(savedInstanceState: Bundle?) {
        binding= ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.btnStop.isEnabled=false
        binding.btnPause.isEnabled=false

    }
    fun start(view: View){
        number=0
        runnable= object  : Runnable{
            override fun run() {
                number++
                binding.textSayac.text="Time:${number}"
               if (number==3){
                   number=0
                   minus++
                  binding.textMinute.text="Minute:${minus}"
               }
                if(minus==3){
                    minus=0
                    hours++
                    binding.textHour.text="Hours: ${hours}"
                }
                handler.postDelayed(runnable,1000)

            }
        }
        handler.post(runnable)
        binding.btnStart.isEnabled=false
        binding.btnPause.isEnabled=true
        binding.btnStop.isEnabled=true

    }
    fun pause(view: View){
      handler.removeCallbacks(runnable)
        binding.btnStart.isEnabled=true
        binding.btnPause.isEnabled=false
    }
    fun stop(view: View){
        handler.removeCallbacks(runnable)
        binding.textSayac.text="Time:0"
        binding.textMinute.text="Minute:0"
        binding.textHour.text="Hours:0"
        binding.btnStop.isEnabled=false
        binding.btnStop.isEnabled=false


    }
}