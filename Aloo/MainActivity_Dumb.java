import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Dummy data for the forum
        ArrayList<String> forumPosts = new ArrayList<>();
        forumPosts.add("Post 1: Hello, World!");
        forumPosts.add("Post 2: How are you?");
        forumPosts.add("Post 3: Android development is fun!");

        // Create an ArrayAdapter to populate the ListView
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, forumPosts);

        // Get a reference to the ListView
        ListView forumListView = findViewById(R.id.forumListView);

        // Set the adapter for the ListView
        forumListView.setAdapter(adapter);
    }
}
