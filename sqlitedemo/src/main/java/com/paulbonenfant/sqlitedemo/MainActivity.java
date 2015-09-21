package com.paulbonenfant.sqlitedemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.paulbonenfant.sqlitedemo.db.StudentDataSource;

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

    public void save(View view){

        // load student info into instance
        Student student = loadStudent();

        // create datasource
        StudentDataSource studentDS = new StudentDataSource(this.getApplicationContext());

        // save the student to the database
        studentDS.saveStudent(student);

        // display the message
        Toast.makeText(this, "Student DB id is: " + student.getDbId(), Toast.LENGTH_SHORT).show();
    }

    public void show(View view) {

        // create the datasource
        StudentDataSource studentDS = new StudentDataSource(this.getApplicationContext());

        // get list of students from the db
        List<Student> students = studentDS.getAllStudents();

        TextView txtList = (TextView) findViewById(R.id.txtList);

        // build a String that contains the names
        StringBuilder sb = new StringBuilder();
        for (Student student: students) {
            sb.append(student.getName()).append("\n");
        }

        txtList.setText(sb.toString());
    }

    /* method to create student instance from the view */
    private Student loadStudent() {

        EditText edtName = (EditText) findViewById(R.id.edtName);
        String name = edtName.getText().toString();

        EditText edtNumber = (EditText)findViewById(R.id.edtNumber);
        String number = edtNumber.getText().toString();

        CheckBox chkFullTime = (CheckBox) findViewById(R.id.chkFullTime);
        boolean fullTime = chkFullTime.isChecked();

        return new Student(name, number, fullTime);
    }
}
