package get.gondai.demo

import android.app.Application
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.content.Context
import android.util.Log
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley
import get.gondai.demo.injection.DaggerGlobalShop
import get.gondai.demo.injection.OffsetItem
import get.gondai.demo.models.DeliveryItem

import get.gondai.demo.util.DeliveryUtil
import javax.inject.Inject


class App:Application() {
@Inject  lateinit var newItem: DeliveryItem
 @Inject lateinit var newOffset:OffsetItem
@Inject lateinit var newStatus:MutableLiveData<String>



    override fun onCreate() {
        super.onCreate()
         DaggerGlobalShop.create().inject(this)
    }
}