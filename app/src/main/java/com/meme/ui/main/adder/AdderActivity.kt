package com.meme.ui.main.adder

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import com.bumptech.glide.Glide
import com.meme.R

class AdderActivity : AppCompatActivity() {
    private val presenter = AdderPresenter(this)
    private lateinit var headerET: EditText
    private lateinit var mainET: EditText
    private lateinit var pictureIV: ImageView
    private lateinit var closeBtn: ImageButton
    private lateinit var createBtn: Button
    private lateinit var pictureBtn: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adder)
        setSupportActionBar(findViewById(R.id.adder_toolbar))

        initViews()
        setListeners()
    }

    private fun initViews() {
        headerET = findViewById(R.id.adder_header_et)
        mainET = findViewById(R.id.adder_main_et)
        pictureIV = findViewById(R.id.adder_image_container)
        closeBtn = findViewById(R.id.adder_close_btn)
        createBtn = findViewById(R.id.adder_add_btn)
        pictureBtn = findViewById(R.id.adder_image_btn)
    }

    private fun setListeners() {
        headerET.addTextChangedListener {
            presenter.onHeaderTextEdited(it)
        }

        mainET.addTextChangedListener {
            presenter.onMainTextEdited(it)
        }

        pictureBtn.setOnClickListener {
            presenter.onPictureBtnClicked()
        }

        closeBtn.setOnClickListener {
            presenter.onCloseBtnClickListener()
        }

        createBtn.setOnClickListener {
            presenter.onAddBtnClicked()
        }
    }

    fun showTextExcessError() {
        Toast.makeText(
            applicationContext,
            R.string.adder_excess_error,
            Toast.LENGTH_SHORT
        ).show()
    }


    fun hideCreateBtn() {
        createBtn.isEnabled = false
        createBtn.alpha = 0.3f
    }

    fun showCreateBtn() {
        createBtn.isEnabled = true
        createBtn.alpha = 1f
    }

    fun hidePictureBtn() {
        pictureBtn.isEnabled = false
        pictureBtn.alpha = 0.5f

    }

    fun showPictureBtn() {
        pictureBtn.isEnabled = true
        pictureBtn.alpha = 1f
    }

    fun addPicture(url: String) {
        Glide.with(this).load(url).into(pictureIV)
    }

    fun getMainText(): String =
        mainET.text.toString()

    fun getHeaderText(): String =
        headerET.text.toString()
}