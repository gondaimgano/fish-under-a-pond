package get.gondai.demo.network

import android.arch.paging.PagedList
import android.content.Context
import android.util.Log
import android.widget.Toast
import com.android.volley.*
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.GsonBuilder
import get.gondai.demo.App
import get.gondai.demo.R
import get.gondai.demo.db.DeliveryDatabase
import get.gondai.demo.models.DeliveryItem
import get.gondai.demo.util.DeliveryUtil
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

/*
*
* @params _ctx Context to be used when accessing database and accessing the base_url string in strings.xml
*
* **/
class DeliveryBoundaryCallback(var _ctx:Context):PagedList.BoundaryCallback<DeliveryItem>() {
lateinit var _app:App
    override fun onItemAtEndLoaded(itemAtEnd: DeliveryItem) {
        super.onItemAtEndLoaded(itemAtEnd)
      _app = _ctx as App
        requestNetwork(itemAtEnd.id) //continue with the rest from the network
    }

    override fun onZeroItemsLoaded() {
        super.onZeroItemsLoaded()
        _app = _ctx as App
        _app.newOffset.offset=0 //set first record
        requestNetwork(_app.newOffset.offset)
    }

    fun requestNetwork(_currentPositon:Long)
    {

        _app.newOffset.offset=_currentPositon



          Volley.newRequestQueue(_ctx).add(StringRequest(_ctx.getString(R.string.base_url) + "?offset=${_app.newOffset.offset}&limit=${DeliveryUtil.LIMIT}",
                  Response.Listener {

                      //user @see GsonBuilder convert to from json to array objects of DeliveryItem
                      val deliveries = GsonBuilder().create().fromJson(it, Array<DeliveryItem>::class.java)
                      saveOffline(deliveries.toList())
                      _app.newOffset.offset = _app.newOffset.offset + DeliveryUtil.OFFSET

                  },
                  Response.ErrorListener {

                    //when error send a message display progressbar and retry...
                      _app.newStatus.postValue("Trying to connect...")
                      requestNetwork(_currentPositon)






                  })

          )


    }

    fun saveOffline(arr:List<DeliveryItem>){
        Observable.fromCallable {
            DeliveryDatabase.getInstance(_ctx)
                    ?.manageDeliveryDAO()
                    ?.insertAll(arr)
        }.observeOn(Schedulers.newThread())
                .subscribeOn(Schedulers.io())
                .blockingSubscribe()
    }
}