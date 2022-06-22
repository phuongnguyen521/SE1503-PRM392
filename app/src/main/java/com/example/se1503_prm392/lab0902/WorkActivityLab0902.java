package com.example.se1503_prm392.lab0902;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.se1503_prm392.R;

import java.util.ArrayList;

public class WorkActivityLab0902 extends AppCompatActivity {

    DatabaseLab0902 database;
    ListView lvWork;
    ArrayList<Work> workList;
    WorkAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_lab0902);

        lvWork = findViewById(R.id.lvWork);
        workList = new ArrayList<>();
        adapter = new WorkAdapter(this, R.layout.item_work_lab0902, workList);
        lvWork.setAdapter(adapter);

        //Create database WorkNote
        database = new DatabaseLab0902(this,"WorkNote.sqlite", null, 1);
        //Create table Work
        database.QueryData("Create table if not exists Work(id Integer Primary Key Autoincrement,"
                + "NameWork nvarchar(200))");
        //Insert data
        //database.QueryData("Insert into Work values(null, 'Project Android')");
        //database.QueryData("Insert into Work values(null, 'Design app')");

        //Select data
        Cursor dataWork = database.GetData("Select id, NameWork from Work");
        while (dataWork.moveToNext()){
            String name = dataWork.getString(1);
            int id = dataWork.getInt(0);
            workList.add(new Work(id, name));
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_work, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menuWorkAdd){
            DialogAdd();
        }
        return super.onOptionsItemSelected(item);
    }

    private void DialogAdd(){
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_add_work_lab0902);

        EditText etAddWork = dialog.findViewById(R.id.etAddWork);
        Button btnAddWorkName = dialog.findViewById(R.id.btnAddWorkName);
        Button btnCancelAddingWork = dialog.findViewById(R.id.btnCancelAddingWork);

        btnAddWorkName.setOnClickListener(view -> {
            String namework = etAddWork.getText().toString();
            if (namework.equals("")){
                Toast.makeText(WorkActivityLab0902.this, "Please enter name of work", Toast.LENGTH_LONG).show();
            } else {
                database.QueryData("Insert into Work values(null, '"+namework+"')");
                Toast.makeText(WorkActivityLab0902.this, "Added successfully", Toast.LENGTH_LONG).show();
                dialog.dismiss();
                GetDataWork();
            }
        });
        btnCancelAddingWork.setOnClickListener(view -> {
            dialog.dismiss();
        });
        dialog.show();
    }

    public void DialogEditWork(String name, int id){
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_edit_work_lab0902);

        EditText etEditWork = dialog.findViewById(R.id.etEditWork);
        Button btnEditWorkName = dialog.findViewById(R.id.btnEditWorkName);
        Button btnCancelEditingWork = dialog.findViewById(R.id.btnCancelEditingWork);

        etEditWork.setText(name);

        btnEditWorkName.setOnClickListener(view -> {
            String newName = etEditWork.getText().toString().trim();
            database.QueryData("UPDATE Work SET NameWork = '" + newName + "' WHERE id = '" + id + "'");
            Toast.makeText(WorkActivityLab0902.this, "Edited successfully", Toast.LENGTH_LONG).show();
            dialog.dismiss();
            GetDataWork();
        });

        btnCancelEditingWork.setOnClickListener(view -> {
            dialog.dismiss();
        });
        dialog.show();
    }

    public void DialogDeleteWork(String name, int id){
        AlertDialog.Builder dialogDelete = new AlertDialog.Builder(this);
        dialogDelete.setMessage("Do you want to delete " + name + "?");
        dialogDelete.setPositiveButton("Yes", (dialogInterface, i) -> {
           database.QueryData("DELETE FROM Work WHERE id = '" + id + "'");
            Toast.makeText(WorkActivityLab0902.this, "Deleted successfully", Toast.LENGTH_LONG).show();
            GetDataWork();
        });

        dialogDelete.setNegativeButton("No", (dialogInterface, i) -> {

        });
        dialogDelete.show();
    }

    private void GetDataWork(){
        Cursor dataWork = database.GetData("Select id, NameWork from Work");
        workList.clear();

        while (dataWork.moveToNext()){
            String name = dataWork.getString(1);
            int id = dataWork.getInt(0);
            workList.add(new Work(id, name));
        }
        adapter.notifyDataSetChanged();
    }
}