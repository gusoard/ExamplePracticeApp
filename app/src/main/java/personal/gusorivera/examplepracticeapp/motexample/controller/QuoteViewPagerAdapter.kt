package personal.gusorivera.examplepracticeapp.motexample.controller

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import personal.gusorivera.examplepracticeapp.motexample.QuoteFragment

class QuoteViewPagerAdapter(fm: FragmentManager, fragments: ArrayList<QuoteFragment>)
    : FragmentPagerAdapter(fm) {

    var fragmentList : ArrayList<QuoteFragment> = fragments

    override fun getItem(position: Int): Fragment {
        return this.fragmentList[position]
    }

    override fun getCount(): Int {
        return this.fragmentList.size
    }

}