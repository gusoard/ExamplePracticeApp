package personal.gusorivera.examplepracticeapp.data

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import android.widget.Toast
import personal.gusorivera.examplepracticeapp.model.*

/**
 * Hace referencia al ejemplo de ChoresExample
 *
 * La sección de FACTORY es para un custom Cursor para las querys
 */
class ChoresDatabaseHandler(context: Context) :
        SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    val TAG_CHORES_DATABASE_HANDLER = "Chores Database Handler"
    override fun onCreate(db: SQLiteDatabase?) {

        var CREATE_CHORE_TABLE = "CREATE TABLE $TABLE_NAME ($KEY_ID INTEGER PRIMARY KEY, " +
                "$KEY_CHORE_NAME TEXT, " +
                "$KEY_CHORE_ASSIGNEDBY TEXT, " +
                "$KEY_CHORE_ASSIGNEDTO TEXT, " +
                "$KEY_CHORE_ASSIGNED_TIME LONG)"

        // If db is not null, execute
        db?.execSQL(CREATE_CHORE_TABLE)


    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        // create table again
        onCreate(db)
    }

    /**
     * CRUD - Create, Read, Update, Delete
     */

    fun createChore(chore: Chore):Long{
        var db: SQLiteDatabase = writableDatabase
        var insertOk:Long = -1
        // keyValuePair
        var values = ContentValues()

      try {
          values.put(KEY_CHORE_NAME, chore.choreName)
          values.put(KEY_CHORE_ASSIGNEDBY, chore.assignedBy)
          values.put(KEY_CHORE_ASSIGNEDTO, chore.assignedTo)
          values.put(KEY_CHORE_ASSIGNED_TIME, System.currentTimeMillis())

          insertOk = db.insert(TABLE_NAME, null, values)
          Log.d("DATA INSERTED", "SUCCESS")
      }catch (e:Exception){
          Log.d("DATA INSERTED", "FAILED")
      }finally {
          db.close()
      }
        return insertOk
    }

    fun readAChore(id: Int) : Chore?{
        var db:SQLiteDatabase = readableDatabase
        var chore: Chore? = null
        var cursor: Cursor = db.query(
                                TABLE_NAME,
                                arrayOf(KEY_ID,
                                        KEY_CHORE_NAME,
                                        KEY_CHORE_ASSIGNEDBY,
                                        KEY_CHORE_ASSIGNEDTO,
                                        KEY_CHORE_ASSIGNED_TIME),
                                "$KEY_ID =?",
                                arrayOf(id.toString()),
                                null, null, null, null)

        // MoveToFirst will return false if the cursor is empty
        if (!cursor.isClosed && cursor.moveToFirst()){
            chore = Chore()
            chore.id = cursor.getInt(cursor.getColumnIndex(KEY_ID))
            chore.choreName = cursor.getString(cursor.getColumnIndex(KEY_CHORE_NAME))
            chore.assignedBy = cursor.getString(cursor.getColumnIndex(KEY_CHORE_ASSIGNEDBY))
            chore.assignedTo = cursor.getString(cursor.getColumnIndex(KEY_CHORE_ASSIGNEDTO))
            chore.timeAssigned = cursor.getLong(cursor.getColumnIndex(KEY_CHORE_ASSIGNED_TIME))
        }
        cursor.close()
        return chore
    }

    fun readChores(): ArrayList<Chore>{
        var db: SQLiteDatabase = readableDatabase
        var list: ArrayList<Chore> = ArrayList()

        var selectAllCommand = "SELECT * FROM $TABLE_NAME"

        var cursor: Cursor = db.rawQuery(selectAllCommand, null)

        // loop through the chores
        if (cursor.moveToFirst()){
            var chore: Chore
            do {
                chore = Chore()
                chore.id = cursor.getInt(cursor.getColumnIndex(KEY_ID))
                chore.choreName = cursor.getString(cursor.getColumnIndex(KEY_CHORE_NAME))
                chore.assignedBy = cursor.getString(cursor.getColumnIndex(KEY_CHORE_ASSIGNEDBY))
                chore.assignedTo = cursor.getString(cursor.getColumnIndex(KEY_CHORE_ASSIGNEDTO))
                chore.timeAssigned = cursor.getLong(cursor.getColumnIndex(KEY_CHORE_ASSIGNED_TIME))
                list.add(chore)
            }while (cursor.moveToNext())
        }

        return list
    }

    fun updateChore(chore: Chore): Int{
        val db: SQLiteDatabase = writableDatabase
        val values = ContentValues()
        var result = 0
       try {
           values.put(KEY_CHORE_NAME, chore.choreName)
           values.put(KEY_CHORE_ASSIGNEDBY, chore.assignedBy)
           values.put(KEY_CHORE_ASSIGNEDTO, chore.assignedTo)
           values.put(KEY_CHORE_ASSIGNED_TIME, System.currentTimeMillis())

           result = db.update(TABLE_NAME, values, "$KEY_ID = ?", arrayOf(chore.id.toString()))
       }catch (e:Exception){
           Log.d(TAG_CHORES_DATABASE_HANDLER, e.message)
       }finally {
           db.close()
       }
        return result
    }

    fun deleteChore(chore: Chore){
        val db: SQLiteDatabase = writableDatabase
        db.delete(TABLE_NAME, "$KEY_ID = ?", arrayOf(chore.id.toString()))
        db.close()
    }

    fun getChorusCount(): Int {
        val db: SQLiteDatabase = writableDatabase
        val countQuery = "SELECT * FROM $TABLE_NAME"
        val cursor: Cursor = db.rawQuery(countQuery,null)
        val count = cursor.count
        cursor.close()
        return count
    }
}