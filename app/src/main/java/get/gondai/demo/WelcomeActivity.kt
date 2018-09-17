package get.gondai.demo

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import get.gondai.demo.components.main.MainActivity
import get.gondai.demo.util.DeliveryUtil
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit


class WelcomeActivity : AppCompatActivity() {
lateinit var _disposable:Disposable
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
        _disposable=   Observable.fromCallable {

        }
                .subscribeOn(Schedulers.newThread())
                .delay(DeliveryUtil.DELAY,TimeUnit.SECONDS)
                .subscribe{
                    startActivity(Intent(this,MainActivity::class.java))
                    finish()
                }



    }

    override fun onStop() {
        super.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
       _disposable.dispose()
    }
}
