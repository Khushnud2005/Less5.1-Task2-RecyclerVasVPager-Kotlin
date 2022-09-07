package uz.exemple.less51_task2_recyclervasvpager_kotlin.activities

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import uz.exemple.less51_task2_recyclervasvpager_kotlin.R
import uz.exemple.less51_task2_recyclervasvpager_kotlin.adapters.IntroAdapter
import uz.exemple.less51_task2_recyclervasvpager_kotlin.models.IntroModel

class IntroActivity : AppCompatActivity() {
    private lateinit var context: Context
    private lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)
        initViews()
        val introModels: List<IntroModel> = getIntros()
        refreshAdapter(introModels)
    }

    fun initViews(){
        recyclerView = findViewById(R.id.rv_intro)
        recyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        val snapHelper: SnapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(recyclerView)

        recyclerView.setOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
            }

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                val layoutManager = recyclerView.layoutManager as LinearLayoutManager?
                val activePosition = layoutManager!!.findFirstVisibleItemPosition()
                if (activePosition == RecyclerView.NO_POSITION) return
                Log.d("@@@", "Active Position$activePosition")
            }
        })

    }
    private fun refreshAdapter(intros: List<IntroModel>) {
        val adapter = IntroAdapter(intros, this)
        recyclerView.adapter = adapter
    }
    private fun getIntros(): List<IntroModel> {
        val intros: MutableList<IntroModel> = ArrayList()
        intros.add(
            IntroModel(
                "Say Hello to Global Top - Up",
                "Send mobile top-up to more then 500 networks in over 140 countries.",
                "hello_business.json"
            )
        )
        intros.add(
            IntroModel(
                "Safe, Trusted & Fully Secure",
                "Encrypted transactions mean your payments & Privacy and protected.",
                "safe_money.json"
            )
        )
        intros.add(
            IntroModel(
                "Easy To Use",
                "Pick a number, choose an amount, send your Top-up. Simple",
                "easy_use.json"
            )
        )
        return intros
    }
}