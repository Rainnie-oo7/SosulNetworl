import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class RegistrationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        val usernameEditText = findViewById<EditText>(R.id.editTextUsername)
        val passwordEditText = findViewById<EditText>(R.id.editTextPassword)
        val emailEditText = findViewById<EditText>(R.id.editTextEmail)
        val registerButton = findViewById<Button>(R.id.buttonRegister)

        registerButton.setOnClickListener {
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()
            val email = emailEditText.text.toString()

            // Überprüfe, ob die Eingaben gültig sind
            if (isValidInput(username, password, email)) {
                // Hier kannst du die Benutzerregistrierung und E-Mail-Verifizierung implementieren
                // Achte darauf, sichere Methoden für die Passwortspeicherung zu verwenden
                // und einen sicheren Mechanismus für die E-Mail-Verifizierung zu implementieren
                // Zum Beispiel könntest du einen Bestätigungslink per E-Mail senden.
                // Für Produktionsanwendungen wäre es ratsam, eine sichere Backend-Infrastruktur zu verwenden.

                // Beispiel: Toast-Nachricht für erfolgreiche Registrierung
                Toast.makeText(this, "Registrierung erfolgreich", Toast.LENGTH_SHORT).show()
            } else {
                // Beispiel: Toast-Nachricht für ungültige Eingaben
                Toast.makeText(this, "Ungültige Eingaben", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun isValidInput(username: String, password: String, email: String): Boolean {
        // Hier kannst du deine Validierungslogik implementieren
        // Zum Beispiel kannst du Regex für E-Mail-Validierung verwenden

        return username.isNotEmpty() && password.isNotEmpty() && isValidEmail(email)
    }

    private fun isValidEmail(email: String): Boolean {
        // Beispiel: Vereinfachte E-Mail-Validierung mit Regex
        val emailRegex = Regex("^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})")
        return email.matches(emailRegex)
    }
}
