package get.gondai.demo.injection

import android.app.Application
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.content.Context
import dagger.Component
import dagger.Module
import dagger.Provides
import get.gondai.demo.App
import get.gondai.demo.models.DeliveryItem
import get.gondai.demo.util.DeliveryUtil
import javax.inject.Singleton


@Module
class GlobalModule{
    @Singleton
    @Provides
    fun produceItem():DeliveryItem=DeliveryItem()
    @Singleton
    @Provides
    fun produceOffset():OffsetItem= OffsetItem(0)

    @Singleton
    @Provides
    fun produceStatus(): MutableLiveData<String>{
        return MutableLiveData<String>()
    }

}
@Singleton
@Component(modules = arrayOf(GlobalModule::class))
interface GlobalShop{
    fun inject(app: App)
}

data class OffsetItem(var offset:Long=0)

