//    1. Erstelle die Datenbank-Tabelle: Hier ist ein einfaches SQL-Skript, um eine Tabelle für die Benutzerdaten zu erstellen.  Sql:
//
//    CREATE TABLE IF NOT EXISTS users (
//    id INTEGER PRIMARY KEY AUTOINCREMENT,
//    username TEXT UNIQUE NOT NULL,
//    password TEXT NOT NULL,
//    email TEXT UNIQUE NOT NULL,
//    is_verified INTEGER DEFAULT 0
//);
//    2. Füge Datenbankfunktionalitäten hinzu: Implementiere Funktionen, um Benutzer zu registrieren und zu überprüfen.   Kotlin:
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "user_database"
        private const val DATABASE_VERSION = 1

        private const val TABLE_NAME = "users"
        private const val COLUMN_ID = "id"
        private const val COLUMN_USERNAME = "username"
        private const val COLUMN_PASSWORD = "password"
        private const val COLUMN_EMAIL = "email"
        private const val COLUMN_IS_VERIFIED = "is_verified"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTable = "CREATE TABLE IF NOT EXISTS $TABLE_NAME (" +
                "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "$COLUMN_USERNAME TEXT UNIQUE NOT NULL," +
                "$COLUMN_PASSWORD TEXT NOT NULL," +
                "$COLUMN_EMAIL TEXT UNIQUE NOT NULL," +
                "$COLUMN_IS_VERIFIED INTEGER DEFAULT 0)"
        db.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun registerUser(username: String, password: String, email: String): Boolean {
        val db = this.writableDatabase
        val contentValues = ContentValues()

        contentValues.put(COLUMN_USERNAME, username)
        contentValues.put(COLUMN_PASSWORD, password)
        contentValues.put(COLUMN_EMAIL, email)

        val result = db.insert(TABLE_NAME, null, contentValues)
        return result != -1L
    }

    fun isUsernameAvailable(username: String): Boolean {
        val db = this.readableDatabase
        val query = "SELECT * FROM $TABLE_NAME WHERE $COLUMN_USERNAME = ?"
        val cursor: Cursor = db.rawQuery(query, arrayOf(username))
        val count = cursor.count
        cursor.close()
        return count == 0
    }

    fun isEmailAvailable(email: String): Boolean {
        val db = this.readableDatabase
        val query = "SELECT * FROM $TABLE_NAME WHERE $COLUMN_EMAIL = ?"
        val cursor: Cursor = db.rawQuery(query, arrayOf(email))
        val count = cursor.count
        cursor.close()
        return count == 0
    }
}
