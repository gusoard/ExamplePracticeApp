package personal.gusorivera.examplepracticeapp.volleyexample

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import kotlinx.android.synthetic.main.activity_volley_tab_example.*
import personal.gusorivera.examplepracticeapp.R
import personal.gusorivera.examplepracticeapp.volleyexample.Fragments.FragmentJsonArray
import personal.gusorivera.examplepracticeapp.volleyexample.Fragments.FragmentJsonObject
import personal.gusorivera.examplepracticeapp.volleyexample.Fragments.FragmentJsonString
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class VolleyTabExample : AppCompatActivity() {

    var fragmentList : HashMap<String, Fragment> = hashMapOf()
    var titleList : ArrayList<String> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_volley_tab_example)
        setupFragments()
        setupViewPager()
    }

    private fun setupFragments() {
        fragmentList["JsonString"] = FragmentJsonString.newInstance()
        fragmentList["JsonArray"] = FragmentJsonArray.newInstance()
        fragmentList["JsonObject"] = FragmentJsonObject.newInstance()
    }

    private fun setupViewPager(){
        var adapter = SectionsPagerAdapter(supportFragmentManager)
        volleyViewPager.adapter = adapter
        volleyViewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(volleyTabLayout))
        volleyTabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                volleyViewPager.currentItem = tab!!.position
            }
        })

        for (k in fragmentList.keys){
            volleyTabLayout.addTab(volleyTabLayout.newTab().setText(k))
            adapter.addFragment(fragmentList[k]!!, k)
        }

        adapter.notifyDataSetChanged()
    }
}


