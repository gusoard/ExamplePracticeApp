package personal.gusorivera.examplepracticeapp

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_chores_example.*
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

        var choresCount = dbHandler!!.getChorusCount()

        if (dbHandler!!.getChorusCount() < 1){
            saveChoreButton.setOnClickListener{

                Thread(Runnable {
                    this@ChoresExample.runOnUiThread(java.lang.Runnable {
                        pBar.visibility = View.VISIBLE
                        saveChoreButton.visibility = View.INVISIBLE})

                    if (!TextUtils.isEmpty(enterChoreId.text.toString())
                            && !TextUtils.isEmpty(assignById.text.toString())
                            && !TextUtils.isEmpty(assignToId.text.toString())){

                        var newChore = Chore()
                        newChore.choreName = enterChoreId.text.toString()
                        newChore.assignedTo = assignToId.text.toString()
                        newChore.assignedBy = assignById.text.toString()

                        newChore.id = dbHandler!!.createChore(newChore).toInt()
                        Thread.sleep(2000)
                        if (newChore.id != -1){
                            startActivity(Intent(this,  ChoresList::class.java))
                            finish()

                        }else{
                            this@ChoresExample.runOnUiThread(java.lang.Runnable {
                                Toast.makeText(this, "Hubo un error guardando la tarea: ${newChore.choreName}", Toast.LENGTH_SHORT).show()
                            })
                        }

                    }
                    this@ChoresExample.runOnUiThread(java.lang.Runnable {
                        saveChoreButton.visibility = View.VISIBLE
                        pBar.visibility = View.INVISIBLE
                    })
                }).start()
            }

        }else{
            // Start Activity
            startActivity(Intent(this,  ChoresList::class.java))
            finish()
        }

    }
}
