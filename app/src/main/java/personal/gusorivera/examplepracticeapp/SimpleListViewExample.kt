package personal.gusorivera.examplepracticeapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_simple_list_view_example.*

class SimpleListViewExample : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_simple_list_view_example)

        // Listviews =  adapter and datasource

        // También puede ser un Array<String> = arrayOf(String1, string2, string3....) de tamaño fijo.

        var namesArray: ArrayList<String> = arrayListOf()
        for (i in 0..20){
            namesArray.add("Persona: " + (i+1))
        }
        var namesAdapter: ArrayAdapter<String> = ArrayAdapter(this, android.R.layout.simple_list_item_1, namesArray)
        lvPrueba.adapter = namesAdapter
        lvPrueba.setOnItemClickListener { parent, view, position, id ->  ClickEvent(parent, view, position, id)}

    }

    private fun ClickEvent(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        var itemName:String = lvPrueba.getItemAtPosition(position).toString()
        Toast.makeText(this, "La persona que presionaste fue: $itemName", Toast.LENGTH_SHORT).show()
    }
}
