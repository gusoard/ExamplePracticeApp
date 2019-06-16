package personal.gusorivera.examplepracticeapp.VolleyJsonExample

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

class SectionsPagerAdapter(fm: FragmentManager,
                           private val mFragmentList: ArrayList<Fragment> = arrayListOf(),
                           private val mFragmentTitleList: ArrayList<String> = arrayListOf())
    : FragmentPagerAdapter(fm) {

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