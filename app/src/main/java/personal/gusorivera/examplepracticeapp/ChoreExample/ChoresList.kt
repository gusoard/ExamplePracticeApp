package personal.gusorivera.examplepracticeapp.ChoreExample

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_chores_list.*
import kotlinx.android.synthetic.main.popup_chore.view.*
import personal.gusorivera.examplepracticeapp.R
import personal.gusorivera.examplepracticeapp.ChoreExample.data.ChoreListAdapter
import personal.gusorivera.examplepracticeapp.ChoreExample.model.Chore
import java.util.*

class ChoresList : AppCompatActivity() {

    private var adapter: ChoreListAdapter? = null
    private var choreList: ArrayList<Chore>? = null
    private var layoutManager: RecyclerView.LayoutManager? = null

    private var dialogBuilder: AlertDialog.Builder? = null
    private var choreDialog: AlertDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chores_list)

        choreList = dbHandler!!.readChores()
        choreList!!.reverse()
        layoutManager = LinearLayoutManager(this)
        adapter = ChoreListAdapter(choreList!!, this)
        choresRecyclerView.layoutManager = layoutManager
        choresRecyclerView.adapter = adapter
        adapter!!.notifyDataSetChanged()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.top_menu_chores, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            R.id.add_menu_button ->{
                createPopupDialog()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun createPopupDialog(){

        var view = layoutInflater.inflate(R.layout.popup_chore, null)
        var choreName = view.popupEnterChoreId
        var assignedBy = view.popupAssignById
        var assignedTo = view.popupAssignToId

        var saveButton = view.popupSaveChoreButton

        dialogBuilder = AlertDialog.Builder(this).setView(view)
        choreDialog = dialogBuilder!!.create()
        choreDialog?.show()

        saveButton.setOnClickListener{

            if (!TextUtils.isEmpty(choreName.text.toString())
                    && !TextUtils.isEmpty(assignedBy.text.toString())
                    && !TextUtils.isEmpty(assignedTo.text.toString())){
                var newChore: Chore = Chore()

                newChore.choreName = choreName.text.toString()
                newChore.assignedBy = assignedBy.text.toString()
                newChore.assignedTo = assignedTo.text.toString()
                newChore.id = dbHandler!!.createChore(newChore)

                if(newChore.id <= -1){
                    Toast.makeText(this, "Hubo un error creando la nueva tarea", Toast.LENGTH_SHORT).show()
                }else{
                    choreDialog?.dismiss()
                    choreList!!.add(0, newChore)
                    adapter!!.notifyItemInserted(0)
                    choresRecyclerView.smoothScrollToPosition(0)
                }
            }else{
                Toast.makeText(this, "Se deben completar todos los datos", Toast.LENGTH_SHORT).show()

                if (TextUtils.isEmpty(choreName.text.toString())){
                    choreName.setBackgroundColor(Color.RED)
                }else{
                    choreName.setBackgroundColor(Color.TRANSPARENT)
                }
                if (TextUtils.isEmpty(assignedBy.text.toString())){
                    assignedBy.setBackgroundColor(Color.RED)
                }else{
                    assignedBy.setBackgroundColor(Color.TRANSPARENT)

                }
                if (TextUtils.isEmpty(assignedTo.text.toString())){
                    assignedTo.setBackgroundColor(Color.RED)
                }else{
                    assignedTo.setBackgroundColor(Color.TRANSPARENT)
                }

            }




        }


    }
}
