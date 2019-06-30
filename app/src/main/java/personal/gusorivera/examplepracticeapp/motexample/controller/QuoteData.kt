package personal.gusorivera.examplepracticeapp.motexample.controller

import android.util.Log
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonArrayRequest
import org.json.JSONArray
import org.json.JSONException
import personal.gusorivera.examplepracticeapp.motexample.model.Quote

class QuoteData {
    val quoteArrayList : ArrayList<Quote> = arrayListOf()

    fun getQuotes(callback: QuoteListAsyncResponse?){
        var url = "https://raw.githubusercontent.com/pdichone/UIUX-Android-Course/master/Quotes.json%20"

        var requestInfo = JsonArrayRequest(Request.Method.GET
                , url
                , null
                , Response.Listener {
            response : JSONArray ->
            try {

                for(i in 0 until response.length() - 1){
                    var quoteObject = response.getJSONObject(i)
                    var quote = Quote(quoteObject.getString("quote"), quoteObject.getString("name"))
                    // Log.d("Quote", quote.quoate + " -- " + quote.author)
                    quoteArrayList.add(quote)
                }

            }catch (e: JSONException){
                e.printStackTrace()
            }
            callback?.processFinished(quoteArrayList)

        }, Response.ErrorListener {
            error : VolleyError ->
            try {
                Log.d("Error", "Not working $error")
            }catch (e: JSONException){
                e.printStackTrace()
            }
        })

        AppController.instance!!.addToRequestQueue(requestInfo)
    }

}