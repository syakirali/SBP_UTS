package com.example.sbppesawat5

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_hasil.*
import com.example.sbppesawat5.Plane

class HasilActivity : AppCompatActivity() {

    private var pesawat: Plane? = null
    private var resources:Map<Int, Int> = mapOf(
        1 to R.mipmap.pesawat_boeing,
        2 to R.mipmap.pesawat_atr,
        3 to R.mipmap.pesawat_apache,
        4 to R.mipmap.pesawat_eurocopter,
        5 to R.mipmap.pesawat_yakovlev,
        6 to R.mipmap.pesawat_tupolev
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hasil)
        showProgress()
        pesawat = intent.extras?.get("pesawat") as Plane?
        setHasil()
    }

    fun setHasil() {
        if (pesawat == null) {
            tv_pesawat.text = "Pesawat tidak ditemukan !"
            hideProgress()
            iv_pesawat.visibility = View.INVISIBLE
        } else {
            val ir:Int? = resources[pesawat?.id]
            if (ir !== null) {
                iv_pesawat.setImageResource(ir)
            }
            tv_pesawat.text = pesawat!!.name
            hideProgress()
        }

    }

    fun showProgress() {
        pb_hasil.visibility = View.VISIBLE
    }

    fun hideProgress() {
        pb_hasil.visibility = View.INVISIBLE
    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(
            Intent(
                this@HasilActivity,
                MainActivity::class.java
            )
        )
    }
}
