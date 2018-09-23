package get.gondai.demo.components.main

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import android.widget.Toast
import get.gondai.demo.App
import get.gondai.demo.R
import get.gondai.demo.components.detail.DetailActivity

import get.gondai.demo.db.DeliveryDatabase
import get.gondai.demo.models.DeliveryItem

import get.gondai.demo.util.DeliveryUtil
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    lateinit var mainVM: MainViewModel
    lateinit var adapter_: DeliveryListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

         val a= this.application as App
        //initialize the adapter
        adapter_ = DeliveryListAdapter{
           a.newItem=it

            if(a.newItem==it)
              startActivity(Intent(this,DetailActivity::class.java))
        }
        //initialize recycler view
        deliveryList.apply {
            adapter = adapter_
            layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
        }
        //initialize the view model
        mainVM = ViewModelProviders.of(this).get(MainViewModel::class.java)
        //serve the list to the recycler view adapter when ready
        mainVM.deliverLineUp.observe(this, Observer {
            adapter_.submitList(it!!)
            progressBar3.visibility=View.GONE

        })

        a.newStatus.observe(this, Observer {
            progressBar3.visibility=View.VISIBLE
        Toast.makeText(this,it,Toast.LENGTH_SHORT).show()
        })


    }

    override fun onDestroy() {
        super.onDestroy()
        DeliveryDatabase.destroy()
        mainVM.removeObservers()

    }


}


