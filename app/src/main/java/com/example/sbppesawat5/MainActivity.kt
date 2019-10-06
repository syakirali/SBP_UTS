package com.example.sbppesawat5

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var planes: MutableList<Plane>
    private lateinit var characteristics: MutableList<Characteristic>
    private val matchedCharacteristic = mutableListOf<Characteristic>()
    private val notMatchedCharacteristic = mutableListOf<Characteristic>()
    private var characteristicPointer = 0
    private var analyzing = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        hideUI()
        this.planes = Utils.getPlanes()
        this.characteristics = Utils.getCharacteristics()
        changeQuestion()

        b_iya.setOnClickListener {
            this.characteristics[this.characteristicPointer].answer = true
            matchedCharacteristic.add(this.characteristics[this.characteristicPointer])
            changeQuestion()
        }

        b_tidak.setOnClickListener {
            this.characteristics[this.characteristicPointer].answer = false
            notMatchedCharacteristic.add(this.characteristics[this.characteristicPointer])
            analyzeAnswer()
            changeQuestion()
        }

    }

    fun analyzeAnswer() {
        this.analyzing = true
        val id = this.planes[0].id
        val res: Boolean?
        when (id) {
            1 -> {
                res = isBoeing()
            }
            2 -> {
                res = isAtr()
            }
            3 -> {
                res = isApache()
            }
            4 -> {
                res = isEuro()
            }
            5 -> {
                res = isYakovlev()
            }
            else -> {
                res = isTupovlev()
            }
        }
        if (res == false)
            popPlane()
        this.analyzing = false
    }

    fun changeQuestion() {
        hideUI()
        if (planes.isEmpty()) {
            tampilHasil()
            return
        }
        val id = this.planes[0].id
        val res: Boolean?
        when (id) {
            1 -> {
                res = isBoeing()
            }
            2 -> {
                res = isAtr()
            }
            3 -> {
                res = isApache()
            }
            4 -> {
                res = isEuro()
            }
            5 -> {
                res = isYakovlev()
            }
            else -> {
                res = isTupovlev()
            }
        }
        when (res) {
            null -> {
                showUI()
            }

            true -> {
                tampilHasil()
            }

            else -> {
                popPlane()
                changeQuestion()
            }
        }
    }

    fun isBoeing(): Boolean? {
        val temp = isPesawatKomersil()
        if (temp != true)
            return temp

        if (characteristicNotAnswered(10)) {
            return null
        }
        if (answerIsFalse(10))
            return false

        if (characteristicNotAnswered(11))
            return null
        return !answerIsFalse(11)
    }

    fun isAtr(): Boolean? {
        val temp = isPesawatKomersil()
        if (temp != true)
            return temp

        if (characteristicNotAnswered(12))
            return null
        if (answerIsFalse(12))
            return false

        if (characteristicNotAnswered(13))
            return null
        return !answerIsFalse(13)
    }

    fun isApache(): Boolean? {
        val temp = isHelikopter()
        if (temp != true)
            return temp

        if (characteristicNotAnswered(14))
            return null
        return !answerIsFalse(14)
    }

    fun isEuro(): Boolean? {
        val temp = isHelikopter()
        if (temp != true)
            return temp

        if (characteristicNotAnswered(15))
            return null
        return !answerIsFalse(15)
    }

    fun isYakovlev(): Boolean? {
        val temp = isPesawatTempur()
        if (temp != true)
            return temp

        if (characteristicNotAnswered(16))
            return null
        if (answerIsFalse(16))
            return false

        if (characteristicNotAnswered(17))
            return null
        if (answerIsFalse(17))
            return false

        if (characteristicNotAnswered(18))
            return null

        return !answerIsFalse(18)
    }

    fun isTupovlev(): Boolean? {
        val temp = isPesawatTempur()
        if (temp != true)
            return temp

        if (characteristicNotAnswered(19))
            return null
        if (answerIsFalse(19))
            return false

        if (characteristicNotAnswered(20))
            return null

        return !answerIsFalse(20)
    }

    fun isAlatTransportasi(): Boolean? {
        if (characteristicNotAnswered(0))
            return null
        else if (answerIsFalse(0)) {
            return false
        }
        return true
    }

    fun isPesawatKomersil(): Boolean? {
        val temp = isAlatTransportasi()
        if (temp != true) {
            return temp
        }
        if (characteristicNotAnswered(5))
            return null
        if (answerIsFalse(5)) {
            if (characteristicNotAnswered(6))
                return null
            if (answerIsFalse(6)) {
                return false
            }
            if (characteristicNotAnswered(7))
                return null
            if (answerIsFalse(7)) {
                return false
            }
            return true
        }
        return true
    }

    fun isPesawatTempur(): Boolean? {
        if (characteristicNotAnswered(1))
            return null
        if (answerIsFalse(1)) {
            if (characteristicNotAnswered(2))
                return null
            if (answerIsFalse(2))
                return false
            if (characteristicNotAnswered(4))
                return null
            return !answerIsFalse(4)
        }
        return true
    }

    fun isHelikopter(): Boolean? {
        val temp = isAlatTransportasi()
        if (temp != true)
            return temp

        if (characteristicNotAnswered(8))
            return null
        if (answerIsFalse(8))
            return false

        if (characteristicNotAnswered(9))
            return null
        return !answerIsFalse(9)
    }

    fun answerIsFalse(i:Int): Boolean {
        if (this.notMatchedCharacteristic.contains(this.characteristics[i])) {
            return true
        }
        if (this.matchedCharacteristic.contains(this.characteristics[i])) {
            return false
        }
        return (characteristics[i].answer == false)
    }

    fun characteristicNotAnswered(i:Int): Boolean {
        if (this.notMatchedCharacteristic.contains(this.characteristics[i])) {
            return false
        }
        if (this.matchedCharacteristic.contains(this.characteristics[i])) {
            return false
        }
        if (characteristics[i].answer == null) {
            if (!this.analyzing) {
                this.characteristicPointer = i
                tv_pertanyaan.text = characteristics[i].text
            }
            return true
        }
        return false
    }

    fun popPlane() {
        if (planes.isNotEmpty()) {
            for (c in this.characteristics) {
                c.answer = null
            }
            planes.removeAt(0)
        }
    }

    fun tampilHasil() {
        val hasilIntent = Intent(
            this@MainActivity,
            HasilActivity::class.java
        )
        var psw: Plane? = null
        if (this.planes.isNotEmpty()) {
            psw = this.planes.first()
        }
        hasilIntent.putExtra("pesawat", psw)
        startActivity(hasilIntent)
    }


    fun disableButtons() {
        b_iya.isClickable = false
        b_tidak.isClickable = false
    }

    fun enableButtons() {
        b_iya.isClickable = true
        b_tidak.isClickable = true
    }

    fun showUI() {
        enableButtons()
        progressBar.visibility = View.INVISIBLE
        tv_pertanyaan.visibility = View.VISIBLE
        b_iya.visibility = View.VISIBLE
        b_tidak.visibility = View.VISIBLE
    }

    fun hideUI() {
        disableButtons()
        tv_pertanyaan.visibility = View.INVISIBLE
        b_iya.visibility = View.INVISIBLE
        b_tidak.visibility = View.INVISIBLE
        progressBar.visibility = View.VISIBLE
    }
}
