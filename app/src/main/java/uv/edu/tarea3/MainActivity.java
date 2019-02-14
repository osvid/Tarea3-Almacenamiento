package uv.edu.tarea3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<String> statistics;
    private Button button;
    public static final String fileName="statistics.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        statistics = new ArrayList<String>();

		  // TODO
		  // Assign the reference to button using findViewById
		  
		  // Set an event listener in the button so that 
		  // when the user clicks then the contents of the statistics
		  // must be written in a file in the external storate 
		  // associated to the application.
		  // You can use the method getFilePath (see bellow) to obtain
		  // the full path of the file

    }


    private String getFilePath(){
	    File f = getExternalFilesDir(null);
        return f.getAbsolutePath() + "/" + MainActivity.fileName;
	 }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // This inflates the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
		      // TODO
            // Launch PreferencesActivity
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void setEstadisticas(ArrayList<String> statistics) {
	     // This is a method that will be used by the test to populate the ArrayList
        this.statistics = statistics;
    }
}
