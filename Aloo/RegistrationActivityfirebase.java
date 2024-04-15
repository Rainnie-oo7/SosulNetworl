// Beispielcode mit Firebase Authentication (Android Studio)
// Du musst Firebase SDK zu deinem Projekt hinzufügen.

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;

public class RegistrationActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        mAuth = FirebaseAuth.getInstance();

        // Hier implementierst du die Benutzerregistrierung, Speicherung und E-Mail-Verifizierung.
    }

    private void registerUser(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this, task -> {
                if (task.isSuccessful()) {
                    // Benutzer erfolgreich registriert
                    sendEmailVerification();
                } else {
                    // Fehler bei der Registrierung
                    // Hier kannst du Fehlermeldungen behandeln
                    Exception exception = task.getException();
                }
            });
    }

    private void sendEmailVerification() {
        // Sende E-Mail-Verifizierung hier
        // Siehe Firebase-Dokumentation für Details
    }
}
