package get.gondai.demo.models

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Example json file from api
 *
 * {"description": "Deliver documents to Andrio",
"imageUrl": "https://www.what-dog.net/Images/faces2/scroll0015.jpg",
"location": {
    "lat": 22.336093,
    "lng": 114.155288,
    "address": "Cheung Sha Wan"
}}**/
@Entity
data class DeliveryItem (
        @PrimaryKey var id:Long,
        @ColumnInfo var description:String,
        @ColumnInfo  var imageUrl:String,
         @Embedded  var location:Location= Location()
){
    constructor():this(id=0,description = "",imageUrl = "",location = Location())
}



