package personal.gusorivera.examplepracticeapp.motexample

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_motivational_main.*
import personal.gusorivera.examplepracticeapp.R
import personal.gusorivera.examplepracticeapp.motexample.controller.QuoteData
import personal.gusorivera.examplepracticeapp.motexample.controller.QuoteListAsyncResponse
import personal.gusorivera.examplepracticeapp.motexample.controller.QuoteViewPagerAdapter
import personal.gusorivera.examplepracticeapp.motexample.model.Quote

class MotivationalMainActivity : AppCompatActivity() {

    lateinit var quoteViewPagerAdapter : QuoteViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_motivational_main)
        quoteViewPagerAdapter  = QuoteViewPagerAdapter(supportFragmentManager, getFragments())
        vpMain.adapter = quoteViewPagerAdapter
    }

    private fun getFragments() : ArrayList<QuoteFragment> {

        val fragmentList : ArrayList<QuoteFragment> = arrayListOf()

        QuoteData().getQuotes(object : QuoteListAsyncResponse{
            override fun processFinished(quotes: ArrayList<Quote>) {

//                for (i in 0 until quotes.size){
//                    var quoteFragment = QuoteFragment.newInstance(
//                            quotes[i].quoate,
//                            quotes[i].author
//                    )
//                    fragmentList.add(quoteFragment)
//                }

                (0 until quotes.size).mapTo(fragmentList) {
                    QuoteFragment.newInstance(
                            quotes[it].quote,
                            quotes[it].author
                    )
                }
                quoteViewPagerAdapter.notifyDataSetChanged()
            }
        })
        return fragmentList
    }
}
