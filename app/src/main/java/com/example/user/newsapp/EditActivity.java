package com.example.user.newsapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;


/**
 * Created by User on 7/30/2019.
 */
public class EditActivity extends AppCompatActivity {
    DbHandler h = new DbHandler(this);
    EditText title;
    EditText desc;
    EditText details;
    ImageView image;
    int id;
    Button edit;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        title = (EditText) findViewById(R.id.text_title);
        desc =(EditText) findViewById(R.id.text_desc);
        details = (EditText) findViewById(R.id.text_details);
        image = (ImageView)findViewById(R.id.image);
        edit = (Button)findViewById(R.id.button_edit);
        Intent myIntent = getIntent(); // gets the previously created intent
        id = myIntent.getExtras().getInt("id");

        for (int i = 0; i < MainActivity.the_news.size();i++)
        {
            if (MainActivity.the_news.get(i).getID() == id) {
                title.setText(MainActivity.the_news.get(i).getTitle());
                desc.setText(MainActivity.the_news.get(i).getDesc());
                details.setText(MainActivity.the_news.get(i).getDetails());
                int resID = getResources().getIdentifier(MainActivity.the_news.get(i).getImage(), "drawable", getPackageName());
                image.setImageResource(resID);
            }

        }

//        edit the info

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.the_news.get(id).setTitle(title.getText().toString());
                MainActivity.the_news.get(id).setDesc(desc.getText().toString());
                MainActivity.the_news.get(id).setDetails(details.getText().toString());
                Intent myIntent = new Intent(EditActivity.this,MainActivity.class);
                startActivity(myIntent);

            }
        });
    }
    @Override
public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menu, menu);
    return true;
}

    private void killActivity() {
        finishAffinity();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();


        if (id == R.id.action_settings) {
            AlertDialog.Builder builder = new AlertDialog.Builder(EditActivity.this);
            builder.setTitle(R.string.app_name);
            builder.setIcon(R.mipmap.ic_launcher);
            builder.setMessage("Do you want to exit?")
                    .setCancelable(false)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            killActivity();
                            System.exit(0);

                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();


        }

        return false;
    }

}
