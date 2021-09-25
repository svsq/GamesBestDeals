package tk.svsq.gamesbestdeals

import android.app.Application
import com.mocklets.pluto.Pluto
import com.mocklets.pluto.PlutoLog
import com.mocklets.pluto.modules.exceptions.ANRException
import com.mocklets.pluto.modules.exceptions.ANRListener
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        initPluto()
    }

    private fun initPluto() {
        Pluto.initialize(this)
        Pluto.setANRListener(object : ANRListener {
            override fun onAppNotResponding(exception: ANRException) {
                exception.printStackTrace()
                PlutoLog.e("anr-exception", exception.threadStateMap)
            }
        })
    }

}