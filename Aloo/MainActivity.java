import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            Fragment selectedFragment;

            switch (item.getItemId()) {
                case R.id.action_home:
                    selectedFragment = new HomeFragment();
                    break;
                case R.id.action_forum:
                    selectedFragment = new ForumFragment();
                    break;
                case R.id.action_profile:
                    selectedFragment = new ProfileFragment();
                    break;
                default:
                    selectedFragment = new HomeFragment();
            }

            loadFragment(selectedFragment);
            return true;
        });

        // Load the default fragment
        loadFragment(new HomeFragment());
    }

    private void loadFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragmentContainer, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
