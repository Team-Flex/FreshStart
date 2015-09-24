package com.assignments.koorong.freshstart;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.assignments.koorong.freshstart.db_objects.PlayerDataSource;

import java.util.List;

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

    public void save(View view)
    {

        Player player = loadPlayer();
        //get data source to connect to DB
        PlayerDataSource source = new PlayerDataSource(this.getApplicationContext());

        // save the student to the database
        source.savePlayer(player);

        // display the message
        Toast.makeText(this, "Student DB id is: " + player.getDbId(), Toast.LENGTH_SHORT).show();
    }

    public Player loadPlayer()
    {
        EditText edtPlayerName = (EditText) findViewById(R.id.editTextPlayerName);
        String name = edtPlayerName.getText().toString();

        EditText edtNumber = (EditText)findViewById(R.id.editTextNumberOfGoals);
        int goals = Integer.parseInt(edtNumber.getText().toString());


       // RadioGroup group = (RadioGroup) findViewById(R.id.radioGroup);

        RadioButton goalieBtn = (RadioButton) findViewById(R.id.radioBtnGoalie);
        RadioButton forwardBtn = (RadioButton) findViewById(R.id.radioBtnForward);
        RadioButton defenceBtn = (RadioButton) findViewById(R.id.radioBtnDefence);

        String position = "";

        if(goalieBtn.isChecked()){position = "Goalie";}
        if(forwardBtn.isChecked()){position = "Forward";}
        if(defenceBtn.isChecked()){position = "Defence";}
        //int positionNumber = group.getCheckedRadioButtonId();


/*
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
        }*/
        return new Player(name,position, goals);
    }
    public void show(View view) {
/*
        // create the datasource
        PlayerDataSource playerDS = new PlayerDataSource(this.getApplicationContext());

        // get list of students from the db
        List<Player> players = playerDS.getAllPlayers();

        TextView txtPlayerList = (TextView) findViewById(R.id.txtPlayerList);

        // build a String that contains the names
        StringBuilder sb = new StringBuilder();
        for (Player player: players) {
            sb.append(player.getPlayerName() + " , " +player.getGoals() + " "+ player.getPosition()).append("\n");
        }
        txtPlayerList.setText(sb.toString());
    */

        Intent intent = new Intent(this,Player_List.class);
        startActivity(intent);

    }
    public boolean deletePlayer()
    {

        return true;
    }
}
