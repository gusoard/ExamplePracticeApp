package personal.gusorivera.examplepracticeapp.VolleyJsonExample

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import personal.gusorivera.examplepracticeapp.R

class VolleyTabExample : AppCompatActivity() {


    lateinit var mSectionPageAdapter : SectionsPagerAdapter
    lateinit var mViewPager : ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_volley_tab_example)


    }

    fun setupViewPager(viewPager : ViewPager){
        val adapter : SectionsPagerAdapter = SectionsPagerAdapter(supportFragmentManager)

    }

}


class SectionsPagerAdapter(fm: FragmentManager , private val mFragmentList: ArrayList<Fragment> = arrayListOf() , private val mFragmentTitleList: ArrayList<String> = arrayListOf()) : FragmentPagerAdapter(fm) {

    fun addFragment(fragment : Fragment, fragmentTitle : String){
        mFragmentList.add(fragment)
        mFragmentTitleList.add(fragmentTitle)
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return mFragmentTitleList.get(position)
    }

    override fun getItem(position: Int): Fragment {
        return mFragmentList.get(position)
    }

    override fun getCount(): Int {
        return mFragmentList.size
    }
}