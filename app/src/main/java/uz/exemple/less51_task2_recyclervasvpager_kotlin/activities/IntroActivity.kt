package uz.exemple.less51_task2_recyclervasvpager_kotlin.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.MarginLayoutParams
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import uz.exemple.less51_task2_recyclervasvpager_kotlin.MainActivity
import uz.exemple.less51_task2_recyclervasvpager_kotlin.R
import uz.exemple.less51_task2_recyclervasvpager_kotlin.adapters.IntroAdapter
import uz.exemple.less51_task2_recyclervasvpager_kotlin.models.IntroModel

class IntroActivity : AppCompatActivity() {
    private lateinit var context: Context
    private lateinit var recyclerView: RecyclerView
    private lateinit var tabLayout: TabLayout
    lateinit var linearLayoutManager: LinearLayoutManager
    lateinit var introModels: List<IntroModel>
    lateinit var tv_skip: TextView
    lateinit var btn_start: Button

    private var isUserScrolling = false
    private val isListGoingUp = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)
        initViews()
        val introModels: List<IntroModel> = getIntros()
        refreshAdapter(introModels)
    }

    fun initViews(){
        recyclerView = findViewById(R.id.rv_intro)
        tabLayout = findViewById(R.id.tab_layout)
        btn_start = findViewById(R.id.btn_start)
        tv_skip = findViewById(R.id.tv_skip)

        linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.layoutManager = linearLayoutManager


        val snapHelper: SnapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(recyclerView)
        initTabs(tabLayout)

        addtabListener(tabLayout,recyclerView)
        addRVSlistener(recyclerView,tabLayout,linearLayoutManager)


        tv_skip.setOnClickListener {
            recyclerView.smoothScrollToPosition(2)
            val tab = tabLayout.getTabAt(2)
            tab!!.select()
            btnOn()
        }
        btn_start.setOnClickListener { openMainActivity() }


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

    fun initTabs(tabLayout: TabLayout){
        val tabs = tabLayout.getChildAt(0) as ViewGroup

        for (i in 0..2) {
            tabLayout.addTab(tabLayout.newTab())
            val v = tabs.getChildAt(i)
            val params = v.layoutParams as MarginLayoutParams
            params.rightMargin = 25
        }
    }

    fun addtabListener(tabLayout:TabLayout,recyclerView:RecyclerView){
        tabLayout.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                isUserScrolling = false
                val position = tab.position
                if (position == 0) {
                    recyclerView.smoothScrollToPosition(0)
                    btnOf()
                } else if (position == 1) {
                    recyclerView.smoothScrollToPosition(1)
                    btnOf()
                } else if (position == 2) {
                    recyclerView.smoothScrollToPosition(2)
                    btnOn()
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })
    }
    fun addRVSlistener(recyclerView:RecyclerView,tabLayout:TabLayout,linearLayoutManager:LinearLayoutManager){
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                if (newState == RecyclerView.SCROLL_STATE_DRAGGING) {
                    isUserScrolling = true
                }
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val itemPosition = linearLayoutManager.findFirstVisibleItemPosition()
                if (isUserScrolling) {
                    if (itemPosition == 0) { //  item position of uses
                        val tab = tabLayout.getTabAt(0)
                        tab!!.select()
                        btnOf()
                    } else if (itemPosition == 1) { //  item position of side effects
                        val tab = tabLayout.getTabAt(1)
                        tab!!.select()
                        btnOf()
                    } else if (itemPosition == 2) { //  item position of how it works
                        val tab = tabLayout.getTabAt(2)
                        tab!!.select()
                        btnOn()
                    }
                }
            }
        })
    }

    fun btnOn() {
        tv_skip.visibility = View.GONE
        btn_start.visibility = View.VISIBLE
    }

    fun btnOf() {
        tv_skip.visibility = View.VISIBLE
        btn_start.visibility = View.GONE
    }

    fun openMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    // Musor
    /*recyclerView.setOnScrollListener(object : RecyclerView.OnScrollListener() {
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
        })*/
}