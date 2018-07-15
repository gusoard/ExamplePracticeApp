package personal.gusorivera.examplepracticeapp.model

/**
 * Hace referencia al ejemplo de ChoresExample
 */

val DATABASE_VERSION: Int = 1
val DATABASE_NAME: String = "chore.db"

// Tablas
val TABLE_NAME: String = "chores"

// Nombres de columnas

    // Tabla chores
        val KEY_ID: String = "id"
        val KEY_CHORE_NAME: String = "chore_name"
        val KEY_CHORE_ASSIGNEDBY: String = "chore_assigned_by"
        val KEY_CHORE_ASSIGNEDTO: String = "chore_assigned_to"
        val KEY_CHORE_ASSIGNED_TIME: String = "chore_assigned_time"
