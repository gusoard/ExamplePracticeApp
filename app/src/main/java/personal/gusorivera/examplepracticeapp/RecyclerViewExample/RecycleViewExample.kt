package personal.gusorivera.examplepracticeapp.RecyclerViewExample

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_recycle_view_example.*
import personal.gusorivera.examplepracticeapp.R
import personal.gusorivera.examplepracticeapp.RecyclerViewExample.data.PersonListAdapter
import personal.gusorivera.examplepracticeapp.RecyclerViewExample.model.Person

class RecycleViewExample : AppCompatActivity() {

    private var adapter: PersonListAdapter? = null
    private var personList: ArrayList<Person>? = null
    private var layoutManager: RecyclerView.LayoutManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycle_view_example)

        personList = ArrayList<Person>()
        layoutManager = LinearLayoutManager(this)
        adapter = PersonListAdapter(personList!!, this)

        // Setup list (Recycler view in the main layout)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter

        // Load data
        for (i in 0..16){
            val persona = Person()
            persona.name = "James" + i
            persona.age = 20 + i
            personList!!.add(persona)
        }
        adapter!!.notifyDataSetChanged()
    }
}
