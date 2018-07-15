package personal.gusorivera.examplepracticeapp.data

import android.app.Activity
import android.content.Context
import android.preference.PreferenceManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import personal.gusorivera.examplepracticeapp.R
import personal.gusorivera.examplepracticeapp.model.Person

/**
 *  list -> The data to be displayed
 *  Context in which it will be created. So it knows how to connect to other components
 *  VH -> View Holder only creates rows that are being viewed. What the user can see
 */
class PersonListAdapter(private val list:ArrayList<Person>, private val context: Context) : RecyclerView.Adapter<PersonListAdapter.ViewHolder>(){

    inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        fun bindItem(person:Person){
            var name: TextView = itemView.findViewById(R.id.name) as TextView
            var age: TextView = itemView.findViewById(R.id.age) as TextView

            name.text = person.name
            age.text = person.age.toString()

            itemView.setOnClickListener{
                Toast.makeText(context, name.text, Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ViewHolder {
        // Create out view from our xml file
        val view = LayoutInflater.from(context).inflate(R.layout.list_row, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(list[position])
    }

}