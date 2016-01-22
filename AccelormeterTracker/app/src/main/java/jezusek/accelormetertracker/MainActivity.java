package jezusek.accelormetertracker;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText etInput, etThreshold;
    TextView tvOutput;
    Button bSubmit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);



        etInput = (EditText) findViewById(R.id.userInput);
        tvOutput = (TextView) findViewById(R.id.output);
        bSubmit = (Button) findViewById(R.id.submitBtn);
        etThreshold = (EditText) findViewById(R.id.threshold);

    }

        public void register(View v) {
            String accel = etInput.getText().toString();
           Double num = Double.parseDouble(accel);
            String output = "World";
            Double compare = 0.2;
            if(!etThreshold.getText().toString().matches("")){
                compare = Double.parseDouble(etThreshold.getText().toString());

            }
            if(num<compare)
            {
                output = "Hello";
            }

            tvOutput.setText(output);

            Toast.makeText(this, "Saving Data...", Toast.LENGTH_SHORT).show();
            new RegisterActivity(this).execute(accel, output);
        }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
