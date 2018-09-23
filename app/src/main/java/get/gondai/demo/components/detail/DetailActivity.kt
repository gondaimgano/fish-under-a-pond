package get.gondai.demo.components.detail

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.bumptech.glide.Glide

import com.mapbox.mapboxsdk.Mapbox
import com.mapbox.mapboxsdk.annotations.MarkerOptions
import com.mapbox.mapboxsdk.camera.CameraPosition
import com.mapbox.mapboxsdk.camera.CameraUpdate
import com.mapbox.mapboxsdk.geometry.LatLng
import com.mapbox.mapboxsdk.maps.MapboxMap
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback
import get.gondai.demo.App
import get.gondai.demo.R
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity(), OnMapReadyCallback {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


          val a =this.application as App

        Mapbox.getInstance(this, this.getString(R.string.access_token))

        setContentView(R.layout.activity_detail)


        textdetail1.apply {
            text=a.newItem.description
        }
        Glide.with(this).load(a.newItem.imageUrl).into(imagedetail1)
        mapView1.getMapAsync(this)
    }



    override fun onStop() {
        super.onStop()
        mapView1.onStop()
    }

    override fun onResume() {
        super.onResume()
        mapView1.onResume()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView1.onLowMemory()

    }

    override fun onDestroy() {
        super.onDestroy()
        mapView1.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        mapView1.onSaveInstanceState(outState!!)


    }



    override fun onMapReady(p0: MapboxMap) {

val latlng_=LatLng((this.application as App).newItem.location.lat,(this.application as App).newItem.location.lng)
               p0.addMarker(
                       MarkerOptions()
                               .position(latlng_)
                               .title("Go here")
               )

        val cameraPosition = CameraPosition.Builder()
                .target(latlng_)
                .zoom(15.0).build()

        p0.cameraPosition=cameraPosition



    }
}
