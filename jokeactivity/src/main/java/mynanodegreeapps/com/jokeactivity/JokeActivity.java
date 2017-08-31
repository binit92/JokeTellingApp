package mynanodegreeapps.com.jokeactivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class JokeActivity extends AppCompatActivity {

    String jokeString = "";
    TextView jokeTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);

        jokeTextView = (TextView) findViewById(R.id.jokeTextView);

        Intent intent = getIntent();
        Bundle b = intent.getExtras();

        if(b != null){
            jokeString = b.getString("joke");
            Log.d("JokeActivity: ", jokeString);

            if(jokeString != null && !jokeString.isEmpty()){
                jokeTextView.setText(jokeString);
            }
        }
    }
}
