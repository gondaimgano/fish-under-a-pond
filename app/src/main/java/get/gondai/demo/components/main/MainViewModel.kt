package get.gondai.demo.components.main

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import get.gondai.demo.App
import get.gondai.demo.network.DeliveryBoundaryCallback
import get.gondai.demo.db.DeliveryDatabase
import get.gondai.demo.models.DeliveryItem
import get.gondai.demo.util.DeliveryUtil

class MainViewModel(app:Application):AndroidViewModel(app){

    var deliverLineUp:LiveData<PagedList<DeliveryItem>>
    var callback: DeliveryBoundaryCallback
init {

    App.networkStatus= MutableLiveData()
    callback= DeliveryBoundaryCallback(app.applicationContext)
    val config= PagedList.Config.Builder()
            .setPageSize(DeliveryUtil.OFFSET)
            .setInitialLoadSizeHint(5)
            .setEnablePlaceholders(true)
            .setPrefetchDistance(DeliveryUtil.OFFSET)
            .build()

    deliverLineUp=LivePagedListBuilder(DeliveryDatabase
            .getInstance(app.applicationContext)
            ?.manageDeliveryDAO()
            ?.findAll()!!,config)
            .setBoundaryCallback(callback)
            .build()
}
fun removeObservers(){
    deliverLineUp.removeObserver {  }
    App.networkStatus.removeObserver {  }
}
}