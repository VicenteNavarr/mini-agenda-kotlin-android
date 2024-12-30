package com.vnavpal901.miniagenda

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.vnavpal901.miniagenda.MainActivity
import com.vnavpal901.miniagenda.R

/*Esta clase es para que inicie con una 'pantalla de carga'
* llama al layout activity_splash para mostrarse(hay un logo y un texto).
* Para que se muestre este activity primero, he cambiado el orden de 'carga'
* en el manifest.
* -------------------------------------------------------------------
* Controlo el tiempo que quiero que se muestre en pantalla desde aqui.*/


class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // Configura un retraso de 2 segundos antes de iniciar la actividad principal
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, 2000) //  2 segundos
    }
}
