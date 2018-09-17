package get.gondai.demo.db

import android.arch.paging.DataSource
import android.arch.persistence.room.*
import android.content.Context
import get.gondai.demo.models.DeliveryItem



@Dao
interface DeliveryItemDAO{
    @Query("Select * from deliveryitem")
    fun findAll():DataSource.Factory<Int,DeliveryItem>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun insertAll(items:List<DeliveryItem>)
}

@Database(entities = arrayOf(DeliveryItem::class),version = 1)
abstract class DeliveryDatabase:RoomDatabase(){

   abstract  fun manageDeliveryDAO():DeliveryItemDAO

    companion object {
        private  var INSTANCE:DeliveryDatabase?=null
        fun getInstance(ctx: Context):DeliveryDatabase?{

            if(INSTANCE==null)
                synchronized(DeliveryDatabase::class){
                    INSTANCE=  Room.databaseBuilder(ctx.getApplicationContext(),
                            DeliveryDatabase::class.java, "delivery_database")
                            .build()
                }


            return INSTANCE
        }

        fun destroy() {
            INSTANCE=null
        }

    }
}