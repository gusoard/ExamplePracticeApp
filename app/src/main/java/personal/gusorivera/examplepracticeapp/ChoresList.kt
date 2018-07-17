package personal.gusorivera.examplepracticeapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_recycle_view_example.*
import personal.gusorivera.examplepracticeapp.data.ChoreListAdapter
import personal.gusorivera.examplepracticeapp.data.PersonListAdapter
import personal.gusorivera.examplepracticeapp.model.Chore

class ChoresList : AppCompatActivity() {

    private var adapter: ChoreListAdapter? = null
    private var choreList: ArrayList<Chore>? = null
    private var layoutManager: RecyclerView.LayoutManager? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chores_list)

        choreList = dbHandler!!.readChores()
        layoutManager = LinearLayoutManager(this)
        adapter = ChoreListAdapter(choreList!!, this)

        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter



    }
}
