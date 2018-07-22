package personal.gusorivera.examplepracticeapp.model

import java.text.DateFormat
import java.util.*

/**
 * Hace referencia al ejemplo de ChoresExample
 */
class Chore() {

    var choreName: String? = null
    var assignedBy: String? = null
    var assignedTo: String? = null
    var timeAssigned: Long? = null
    var id: Long = -1

    constructor(choreName: String, assignedBy: String, assignedTo: String, timeAssigned: Long, id:Long): this() {
        this.choreName = choreName
        this.assignedBy = assignedBy
        this.assignedTo = assignedTo
        this.timeAssigned = timeAssigned
        this.id = id
    }

    fun showHumanDate(timeAssigned: Long): String {

        var dateFormat: java.text.DateFormat = DateFormat.getDateInstance()
        var formattedDate: String = dateFormat.format(Date(timeAssigned).time)

        return "Created: ${formattedDate}"

    }
}