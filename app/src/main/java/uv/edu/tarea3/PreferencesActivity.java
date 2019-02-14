package uv.edu.tarea3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class PreferencesActivity extends AppCompatActivity {
    private EditText sets;
    private EditText games_set;
    private CheckBox golden_point;
    private Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferences);

		  // Obtain the references to the views using findViewById

        // Obtain the default SharedPreferences 

	     // Read the preferences and set the values on the Views.
		  // If the preferences do not exist (because it is the first
		  // time that we run the app) then you must set the values
		  // of a Grand Slam (5 sets, 6 games per set and if there is 
		  // golden point).
		  
		  // Register a listener on the button to save the preferences
    }
}
