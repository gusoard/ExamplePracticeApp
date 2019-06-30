package personal.gusorivera.examplepracticeapp.motexample


import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_quote.view.*

import personal.gusorivera.examplepracticeapp.R
import java.util.concurrent.ThreadLocalRandom

private const val ARG_QUOTE = "QUOTE"
private const val ARG_AUTHOR = "AUTHOR"

class QuoteFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.fragment_quote, container, false)

        var quote = arguments?.get(ARG_QUOTE).toString()
        var author = arguments?.get(ARG_AUTHOR).toString()

        view.txtQuote.text = quote
        view.txtByAuthor.text = author
        view.cvQuoteFragment.setBackgroundResource(getColor())

        // Inflate the layout for this fragment
        return view
    }

    private fun getColor(): Int {
        val color:Int
        val quotesArray : IntArray = intArrayOf(R.color.blue_500,
                R.color.amber_500,
                R.color.blue_grey_500,
                R.color.brown_500,
                R.color.cyan_500,
                R.color.dark_purple_500,
                R.color.green_500,
                R.color.light_blue_500,
                R.color.indigo_500,
                R.color.red_500)

        val randomNum = ThreadLocalRandom.current().nextInt(quotesArray.size)
        return quotesArray[randomNum]
    }

    companion object {
        fun newInstance(quote: String, author: String) : QuoteFragment {
            val fragment = QuoteFragment()
            val bundle = Bundle()

            bundle.putString(ARG_QUOTE, quote)
            bundle.putString(ARG_AUTHOR, author)



            fragment.arguments = bundle // this is how we pass information between fragments and activities

            return fragment
        }
    }
}
