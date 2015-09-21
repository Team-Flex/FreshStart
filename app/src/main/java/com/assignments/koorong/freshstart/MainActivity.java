package com.assignments.koorong.freshstart;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.assignments.koorong.freshstart.db_objects.PlayerDataSource;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

    public void save()
    {

       // Player player = new Player();
        //get data source to connect to DB
        PlayerDataSource source = new PlayerDataSource(this.getApplicationContext());

        // save the student to the database
       // source.savePlayer(player);

        // display the message
     //   Toast.makeText(this, "Student DB id is: " + player.getDbId(), Toast.LENGTH_SHORT).show();
    }

    public Player loadPlayer()
    {

        EditText edtPlayerName = (EditText) findViewById(R.id.editTextPlayerName);
        String name = edtPlayerName.getText().toString();

        EditText edtNumber = (EditText)findViewById(R.id.editTextNumberOfGoals);
        int goals = Integer.parseInt(edtNumber.getText().toString());

        RadioGroup group = (RadioGroup) findViewById(R.id.radioGroup);
        int positionNumber = group.getCheckedRadioButtonId();
        String position = "";

        switch(positionNumber)
        {

            case 1:
                position="goalie";
                break;
            case 2:
                position="defence";
                break;
            case 3:
                position="forward";
                break;

        }

        return new Player(name,position, goals);

    }
}
