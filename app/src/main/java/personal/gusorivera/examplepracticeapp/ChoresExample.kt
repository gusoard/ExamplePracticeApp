package personal.gusorivera.examplepracticeapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import personal.gusorivera.examplepracticeapp.data.ChoresDatabaseHandler
import personal.gusorivera.examplepracticeapp.model.Chore

/**
 * Hace referencia al ejemplo de ChoresExample
 */

var dbHandler: ChoresDatabaseHandler? = null

class ChoresExample : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chores_example)

        dbHandler = ChoresDatabaseHandler(this)

        var chore = Chore()
        chore.choreName = "Primera tarea"
        chore.assignedBy = "Gustavo"
        chore.assignedTo = "Rivera"

        dbHandler?.createChore(chore)

        var choreReaded: Chore? = dbHandler?.readAChore(2)
        if (choreReaded != null){
            Toast.makeText(this, "El nombre de la tarea es: ${choreReaded.choreName}", Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(this, "ES NULO", Toast.LENGTH_SHORT).show()
        }

    }
}
