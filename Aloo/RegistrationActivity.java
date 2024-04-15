
//    3. Verwende die Datenbankfunktionalitäten in der Aktivität: Integriere die Datenbankfunktionalitäten in deine Aktivität für die Benutzerregistrierung.    Kotlin:
//import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class RegistrationActivity : AppCompatActivity() {

    private lateinit var dbHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        dbHelper = DatabaseHelper(this)

        val usernameEditText = findViewById<EditText>(R.id.editTextUsername)
        val passwordEditText = findViewById<EditText>(R.id.editTextPassword)
        val emailEditText = findViewById<EditText>(R.id.editTextEmail)
        val registerButton = findViewById<Button>(R.id.buttonRegister)

        registerButton.setOnClickListener {
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()
            val email = emailEditText.text.toString()

            if (isValidInput(username, password, email)) {
                if (dbHelper.isUsernameAvailable(username) && dbHelper.isEmailAvailable(email)) {
                    if (dbHelper.registerUser(username, password, email)) {
                        // Beispiel: Toast-Nachricht für erfolgreiche Registrierung
                        Toast.makeText(this, "Registrierung erfolgreich", Toast.LENGTH_SHORT).show()
                    } else {
                        // Beispiel: Toast-Nachricht für Registrierungsfehler
                        Toast.makeText(this, "Registrierung fehlgeschlagen", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    // Beispiel: Toast-Nachricht für bereits verwendeten Benutzernamen oder E-Mail
                    Toast.makeText(this, "Benutzername oder E-Mail bereits vergeben", Toast.LENGTH_SHORT).show()
                }
            } else {
                // Beispiel: Toast-Nachricht für ungültige Eingaben
                Toast.makeText(this, "Ungültige Eingaben", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun isValidInput(username: String, password: String, email: String): Boolean {
        return username.isNotEmpty() && password.isNotEmpty() && isValidEmail(email)
    }

    private fun isValidEmail(email: String): Boolean {
        val emailRegex = Regex("^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})")
        return email.matches(emailRegex)
    }
}
