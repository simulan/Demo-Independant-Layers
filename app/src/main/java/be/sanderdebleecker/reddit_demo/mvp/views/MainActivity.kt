package be.sanderdebleecker.reddit_demo.mvp.views

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.Toast
import be.sanderdebleecker.reddit_demo.R
import be.sanderdebleecker.reddit_demo.mvp.presenters.MainPresenter
import javax.inject.Inject


class MainActivity : AppCompatActivity(), MainView {
    @Inject protected lateinit var mPresenter: MainPresenter


    private lateinit var btnRedditLogin: Button

    //LC
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnRedditLogin = findViewById(R.id.main_btnRedditLogin) as Button
        btnRedditLogin.setOnClickListener { }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        //if (intent != null) mPresenter.redditLoginResult(intent)
    }
    //UI Binds

    //view
    override fun showToast(msg: String) {
        Toast.makeText(this,msg,Toast.LENGTH_LONG).show()
    }

    //OAR Submethods

}
