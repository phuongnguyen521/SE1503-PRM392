package com.example.se1503_prm392;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListViewDemoSlot6 extends AppCompatActivity {

    //Khai bien
    ListView lvCourse;
    ArrayList<String> arrayCourse;
    EditText etCourseName;
    EditText etUpdateCourseName;
    Button btnAdd;
    Button btnUpdate;
    Button btnDelete;
    int positionCourse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_demo_slot6);

        //Anh xa
        lvCourse = (ListView) findViewById(R.id.lvCourse);
        arrayCourse = new ArrayList<>();
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnUpdate = (Button) findViewById(R.id.btnUpdate);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        etUpdateCourseName = (EditText) findViewById(R.id.etUpdateCourseName);
        etUpdateCourseName.setEnabled(false);
        etCourseName = (EditText) findViewById(R.id.etCourseName);

        //Code
        arrayCourse.add("Android");
        arrayCourse.add("PHP");
        arrayCourse.add("iOS");
        arrayCourse.add("Unity");
        arrayCourse.add("ASP.NET");

        //Adapter
        ArrayAdapter adapter = new ArrayAdapter<String>(
                ListViewDemoSlot6.this,
                android.R.layout.simple_list_item_1,
                arrayCourse);

        //Set adapter in listView
        lvCourse.setAdapter(adapter);

        //Show the content of clicked item
        lvCourse.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                etUpdateCourseName.setEnabled(true);
                etCourseName.setText(arrayCourse.get(position));
                etCourseName.setEnabled(false);
                positionCourse = position;
                //Show position of clicked item on screen
//                Toast.makeText(ListViewDemoSlot6.this,
//                        "Position: " + position,
//                        Toast.LENGTH_LONG).show();

                //Show content of clicked item on screen
//                Toast.makeText(ListViewDemoSlot6.this,
//                        "Content: " + arrayCourse.get(position),
//                        Toast.LENGTH_LONG).show();
            }
        });

        lvCourse.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {
                arrayCourse.remove(position);
                adapter.notifyDataSetChanged();
                Toast.makeText(ListViewDemoSlot6.this,
                        "Deleted Successfully", Toast.LENGTH_LONG).show();
                return true;
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String updatedCourseName = etUpdateCourseName.getText().toString();
                arrayCourse.set(positionCourse, updatedCourseName);
                etUpdateCourseName.setEnabled(false);
                etCourseName.setEnabled(true);
                adapter.notifyDataSetChanged();
                Toast.makeText(ListViewDemoSlot6.this,
                        "Updated Successfully", Toast.LENGTH_LONG).show();
                setEditText();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                arrayCourse.remove(positionCourse);
                etUpdateCourseName.setEnabled(false);
                etCourseName.setEnabled(true);
                adapter.notifyDataSetChanged();
                Toast.makeText(ListViewDemoSlot6.this,
                        "Deleted Successfully", Toast.LENGTH_LONG).show();
                setEditText();
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String course = etCourseName.getText().toString();
                arrayCourse.add(course);
                adapter.notifyDataSetChanged();
                setEditText();
            }
        });
    }

    protected void setEditText()
    {
        etUpdateCourseName.setText("Updated name");
        etCourseName.setText("Name");
    }
}