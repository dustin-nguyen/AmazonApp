package com.learn.amazonapp.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.learn.amazonapp.databinding.ActivityIntroBinding
import com.learn.amazonapp.databinding.CustomTabSelectorBinding

import com.learn.amazonapp.view.adapter.IntroViewPageAdapter
import com.learn.amazonapp.view.fragment.intro.Intro1Fragment
import com.learn.amazonapp.view.fragment.intro.Intro2Fragment
import com.learn.amazonapp.view.fragment.intro.Intro3Fragment

class IntroActivity : AppCompatActivity() {
    private lateinit var binding: ActivityIntroBinding
    private lateinit var listOfFragment : List<Fragment>
    private lateinit var  viewPageAdapter: IntroViewPageAdapter

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        return super.onCreateOptionsMenu(menu)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityIntroBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setup()
    }

    private fun setup() {
        setupWebview()
        binding.btnSkip.setOnClickListener {
            val dataIntent = Intent(this@IntroActivity, LoginActivity::class.java)
            startActivity(dataIntent)
        }
    }
    fun setupWebview(){
        listOfFragment= listOf(Intro1Fragment(), Intro2Fragment(), Intro3Fragment())
        viewPageAdapter = IntroViewPageAdapter(listOfFragment,this@IntroActivity)

        with(binding){

            viewPager2.adapter =viewPageAdapter
            TabLayoutMediator(tabLayout,viewPager2){ tab, position ->
                val tabBinding = CustomTabSelectorBinding.inflate(layoutInflater,tabLayout,false)
                tab.customView=tabBinding.root
            }.attach()

            viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
                override fun onPageSelected(position: Int) {
                    updateTabDots(position)
                }
            }
            )
        }
    }
    private fun updateTabDots(selectedPosition: Int) {
        for (i in 0 until binding.tabLayout.tabCount) {
            val tabView = binding.tabLayout.getTabAt(i)?.customView
            val tabBinding = CustomTabSelectorBinding.bind(tabView!!)

            if (i == selectedPosition) {
                tabBinding.imgDotSelected.visibility = View.VISIBLE
                tabBinding.imgDotUnselected.visibility = View.GONE
            } else {
                tabBinding.imgDotSelected.visibility = View.GONE
                tabBinding.imgDotUnselected.visibility = View.VISIBLE
            }
        }
    }
}