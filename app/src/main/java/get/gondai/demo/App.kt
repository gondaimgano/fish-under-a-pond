package get.gondai.demo

import android.app.Application
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.content.Context
import android.util.Log
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley
import get.gondai.demo.models.DeliveryItem

import get.gondai.demo.util.DeliveryUtil


class App:Application() {



    companion object {
     var currentItem:DeliveryItem= DeliveryItem()
     lateinit var networkStatus: MutableLiveData<String>
        var currentOffset:Long=0
    }

}