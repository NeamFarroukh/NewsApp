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
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by User on 7/30/2019.
 */
public class Details extends AppCompatActivity{
    TextView title;
    TextView desc;
    TextView details;
    ImageView image;
    Button edit;
    int rid;
    int id;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        title = (TextView) findViewById(R.id.text_title);
        desc =(TextView) findViewById(R.id.text_desc);
        details = (TextView) findViewById(R.id.text_details);
        edit = (Button)findViewById(R.id.button_edit);
        image = (ImageView)findViewById(R.id.image) ;

        Intent myIntent = getIntent(); // gets the previously created intent
        id = myIntent.getExtras().getInt("id");

//        get the info for the selected item

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


// start the editing page
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(Details.this, EditActivity.class);

                myIntent.putExtra("id",id);
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
            AlertDialog.Builder builder = new AlertDialog.Builder(Details.this);
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
