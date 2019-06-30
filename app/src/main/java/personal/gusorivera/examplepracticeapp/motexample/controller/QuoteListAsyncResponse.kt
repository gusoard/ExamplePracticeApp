package personal.gusorivera.examplepracticeapp.motexample.controller

import personal.gusorivera.examplepracticeapp.motexample.model.Quote

interface QuoteListAsyncResponse {
    fun processFinished(quotes: ArrayList<Quote>)
}
