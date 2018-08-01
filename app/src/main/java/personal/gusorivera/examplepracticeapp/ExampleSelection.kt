package personal.gusorivera.examplepracticeapp

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_example_selection.*
import personal.gusorivera.examplepracticeapp.ChoreExample.ChoresExample
import personal.gusorivera.examplepracticeapp.RecyclerViewExample.RecycleViewExample
import personal.gusorivera.examplepracticeapp.VolleyJsonExample.VolleyJsonExample

/**
 * Proyecto basado en actividades del curso de Paulo Dichone de Udemy: Kotlin Android Development Masterclass
 *
 * Combinación de varias actividades, con algunas modificaciones a modo de prueba, agrupadas en una
 *  sola aplicaciónn.
 *
 *  El propósito de ésta aplicación es exclusivo para pruebas y educación
 */
class ExampleSelection : AppCompatActivity(), View.OnClickListener {

    override fun onClick(v: View?) {
        // IMPORTANTE: Cada boton que se agregue, se debe asignar el click listener
        var intentExample: Intent? = null
        when(v?.id){
            R.id.btnSimpleListView -> {
                intentExample = Intent(this, SimpleListViewExample::class.java)

            }
            R.id.btnRecycleView -> {
                intentExample = Intent(this, RecycleViewExample::class.java)
            }

            R.id.btnSharedPrefs -> {
                intentExample = Intent(this, SharedPrefsExample::class.java)
            }
            R.id.btnChoresDatabase -> {
                intentExample = Intent(this, ChoresExample::class.java)
            }
            R.id.btnVolleyJsonExample -> {
                intentExample = Intent(this, VolleyJsonExample::class.java)
            }
        // IMPORTANTE: Cada boton que se agregue, se debe asignar el click listener
        }
        if (intentExample != null){
            startActivity(intentExample)
        }

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_example_selection)
        // IMPORTANTE: Cada boton que se agregue, se debe asignar el click listener
        btnSimpleListView.setOnClickListener(this)
        btnRecycleView.setOnClickListener(this)
        btnSharedPrefs.setOnClickListener(this)
        btnChoresDatabase.setOnClickListener(this)
        btnVolleyJsonExample.setOnClickListener(this)
    }
}
