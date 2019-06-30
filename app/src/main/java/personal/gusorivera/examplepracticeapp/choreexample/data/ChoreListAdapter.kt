package personal.gusorivera.examplepracticeapp.choreexample.data

import android.content.Context
import android.graphics.Color
import android.support.v7.app.AlertDialog
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.popup_chore.view.*
import personal.gusorivera.examplepracticeapp.R
import personal.gusorivera.examplepracticeapp.choreexample.model.Chore

class ChoreListAdapter(private val list: ArrayList<Chore>, private val context: Context) :
        RecyclerView.Adapter<ChoreListAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        fun bindItem(chore: Chore){
            var choreName: TextView = itemView.findViewById(R.id.listChoreName)
            var assignedBy: TextView = itemView.findViewById(R.id.listAssignedBy)
            var assignedTo: TextView = itemView.findViewById(R.id.listAssignedTo)
            var timeAssigned: TextView = itemView.findViewById(R.id.listDate)

            var deleteButton: Button = itemView.findViewById(R.id.listDeleteButton)
            var editButton: Button = itemView.findViewById(R.id.listEditButton)

            choreName.text = chore.choreName
            assignedBy.text = chore.assignedBy
            assignedTo.text = chore.assignedTo
            timeAssigned.text = chore.showHumanDate(chore.timeAssigned!!)

            deleteButton.setOnClickListener(this)
            editButton.setOnClickListener(this)

        }

        override fun onClick(v: View?) {
            var mPosition = adapterPosition
            var selectedChore = list[mPosition]
            when(v?.id){
                R.id.listDeleteButton -> {
                    list.removeAt(mPosition)
                    notifyItemRemoved(mPosition)
                    var dbHandler: ChoresDatabaseHandler = ChoresDatabaseHandler(context)

                    var resultado: Int = dbHandler.deleteChore(selectedChore)
                    when {
                        resultado < 1 -> Toast.makeText(context,"Hubo un error eliminando la tarea seleccionada", Toast.LENGTH_SHORT).show()
                        resultado > 1 -> Toast.makeText(context,"Hubo un error y se eliminaron varias filas", Toast.LENGTH_LONG).show()
                        else -> Toast.makeText(context,"${selectedChore.choreName} eliminado satisfactoriamente.", Toast.LENGTH_LONG).show()
                    }
                }
                R.id.listEditButton -> {
                    createEditChorePopupDialog(selectedChore, mPosition)

                }
                else -> {
                    Toast.makeText(context,"You shouldn't be here!", Toast.LENGTH_SHORT).show()
                }
            }
        }

        fun createEditChorePopupDialog(chore: Chore, position: Int){

            var dialogBuilder: AlertDialog.Builder?
            var editChoreDialog: AlertDialog?
            var dbHandler: ChoresDatabaseHandler = ChoresDatabaseHandler(context)

            var view = LayoutInflater.from(context).inflate(R.layout.popup_chore, null)
            var choreNameEditText = view.popupEnterChoreId
            var assignedByEditText = view.popupAssignById
            var assignedToEditText = view.popupAssignToId

            choreNameEditText.setText(chore.choreName)
            assignedByEditText.setText(chore.assignedBy)
            assignedToEditText.setText(chore.assignedTo)

            var saveEditButton = view.popupSaveChoreButton

            dialogBuilder = AlertDialog.Builder(context).setView(view)
            editChoreDialog = dialogBuilder!!.create()
            editChoreDialog?.show()


            saveEditButton.setOnClickListener{

                if (!TextUtils.isEmpty(choreNameEditText.text.toString())
                        && !TextUtils.isEmpty(assignedByEditText.text.toString())
                        && !TextUtils.isEmpty(assignedToEditText.text.toString())){

                    chore.choreName = choreNameEditText.text.toString()
                    chore.assignedBy = assignedByEditText.text.toString()
                    chore.assignedTo = assignedToEditText.text.toString()
                    var resultOk = dbHandler.updateChore(chore)

                    if (resultOk != 1){
                        Toast.makeText(context, "Hubo un error actualizando la tarea: ${chore.choreName}", Toast.LENGTH_SHORT).show()
                    }else{
                        notifyItemChanged(position)
                    }
                    editChoreDialog?.dismiss()
                }else{
                    Toast.makeText(context, "Se deben completar todos los datos", Toast.LENGTH_SHORT).show()

                    if (TextUtils.isEmpty(choreNameEditText.text.toString())){
                        choreNameEditText.setBackgroundColor(Color.RED)
                    }else{
                        choreNameEditText.setBackgroundColor(Color.TRANSPARENT)
                    }
                    if (TextUtils.isEmpty(assignedByEditText.text.toString())){
                        assignedByEditText.setBackgroundColor(Color.RED)
                    }else{
                        assignedByEditText.setBackgroundColor(Color.TRANSPARENT)

                    }
                    if (TextUtils.isEmpty(assignedToEditText.text.toString())){
                        assignedToEditText.setBackgroundColor(Color.RED)
                    }else{
                        assignedToEditText.setBackgroundColor(Color.TRANSPARENT)
                    }

                }




            }


        }

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(list[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.chore_row, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }


}