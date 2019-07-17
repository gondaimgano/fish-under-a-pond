# fish-under-a-pond
I had fun doing this. Android and Kotlin wow!

This app focuses on retrieving a consuming a json query via a https link.

It simulates a delivery app for a delivery man who will deliver items using the lat and longitude items cosumed via node.js

Backend being AWS Lambda/Node.js  front end - Kotlin

Using the android jetpack library (Room,Paging)

Network used (Volley)

## preview of the power of ViewModels and LiveData

```
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

```

![Delivery App - List Screen](https://github.com/gondaimgano/fish-under-a-pond/blob/master/Screenshot_1537249241.png)
