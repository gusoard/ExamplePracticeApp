package personal.gusorivera.examplepracticeapp.data

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import personal.gusorivera.examplepracticeapp.R
import personal.gusorivera.examplepracticeapp.model.Chore

class ChoreListAdapter(private val list: ArrayList<Chore>, private val context: Context) :
        RecyclerView.Adapter<ChoreListAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        fun bindItem(chore:Chore){
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


        }

        // on click
        override fun onClick(v: View?) {

        }

        // edit

        // delete
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(list[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder{
        val view = LayoutInflater.from(context).inflate(R.layout.chore_row, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }


}