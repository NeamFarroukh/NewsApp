package com.example.user.newsapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    //array list of news objects
    public static ArrayList<theNews> the_news = new ArrayList<theNews>();

    //creating instances of news objects
    theNews n1 = new theNews(0, "Sophie Turner", "Sophie Turner And Joe Jonas Just Paid Tribute To Their Late Dog In The Sweetest Way",
            "news1", " Sophie Turner and Joe Jonas found a beautiful way to pay tribute to their late dog, Waldo Picasso, by getting matching tattoos in his honour. The couple, who are currently mourning the loss of their Alaskan Klee Kai – after he was reportedly knocked down and killed last week in a “freak accident” in New York City – took to Instagram to share the sweet gesture.\n" + "\n" + "“I miss you, Waldo,” Turner wrote on her Instagram Story. “Rest in peace, my little baby.”");

    theNews n2 = new theNews(1, "HRH The Duchess", "HRH The Duchess of Sussex Introduces The September Issue In Her Own Words", "news2", "It was in early January, on a cold and blustery London day, that I sat down for a cup of tea with British Vogue’s editor-in-chief, Edward Enninful. Though we have several mutual friends, our first encounter had been years in the making, the impetus for which was my asking him to support an organisation I strongly believe in called Smart Works.It was in early January, on a cold and blustery London day, that I sat down for a cup of tea with British Vogue’s editor-in-chief, Edward Enninful. Though we have several mutual friends, our first encounter had been years in the making, the impetus for which was my asking him to support an organisation I strongly believe in called Smart Works.");
    theNews n3 = new theNews(2, "Edward Enninful", "Edward Enninful On Why HRH The Duchess Of Sussex Is The Ultimate Force For Change", "news3", "It began with an email received in the chilly depths of January. I was spending a few days in the Austrian mountains when a message arrived in my inbox out of the blue. The sender went by the simplest of monikers – “M” – and for a second I was confused, wondering who this mysterious, one-lettered correspondent could possibly be. I couldn’t have known then how the answer to this question would come to define an extraordinary few months in the life of this magazine, or that it would lead to HRH The Duchess of Sussex becoming the first person ever to guest edit a September issue of British Vogue.");
    theNews n4 = new theNews(3, "Susan Devaney", "Anne Hathaway Opens Up About That Instagram Post And Her Personal Fertility Journey", "news4", "Anne Hathaway wants other women to know that she, too, has faced issues trying to conceive. After taking to Instagram last week to announce that she’s expecting her second child, with her husband Adam Shulman, she’s since spoken candidly about her fertility struggles with both pregnancies.\n" +
            "“It’s not for a movie... #2,” the actor joked in the post. “All kidding aside, for everyone going through infertility and conception hell, please know it was not a straight line to either of my pregnancies. Sending you extra love.”");

    theNews n5= new theNews(4,"Elie Pithers","Peter Lindbergh On The Making Of The Forces For Change Cover","news5","When it came to photographing the extraordinary cast of women who appear on the cover of this collector’s edition of British Vogue, there was only one man for the job: Peter Lindbergh. “It was one of those brilliantly spontaneous moments when HRH The Duchess of Sussex and I had exactly the same idea at exactly the same time,” says Edward Enninful, of the choice. “Peter sees beauty in real people, in real situations. He makes everybody feel their best.”The German photography titan boasts a long history with British Vogue, but it was his now-famous January 1990 cover, featuring a gang of supermodels, that was the reference for the Forces for Change issue. “Natural” was a word that came up repeatedly in cover discussions. “I hate retouching, I hate make-up. I always say, ‘Take the make-up off!’” Lindbergh, 74, confirms. “The number of beautiful women who have asked me to lengthen their legs or move their eyes further apart…” he breaks into a laugh. “You would not believe. It’s a culture of madness.”");
    //arrays that will be used to fill up the list view
    String[] listviewTitle;
    String[] listviewdetails;
    int[] listviewImage;
    String[] listviewShortDescription;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listviews);

        if (the_news.size() != 5) {
            the_news.add(n1);
            the_news.add(n2);
            the_news.add(n3);
            the_news.add(n4);
            the_news.add(n5);
        }


        listviewTitle = new String[the_news.size()];
        listviewShortDescription = new String[the_news.size()];
        listviewdetails = new String[the_news.size()];
        listviewImage = new int[the_news.size()];

        for (int i = 0; i < the_news.size(); i++) {
//            get the info and add to the arrays
            theNews n = the_news.get(i);
            listviewTitle[i] = n.getTitle();
            listviewShortDescription[i] = n.getDesc();
            String image = n.getImage();
            int resID = getResources().getIdentifier(image, "drawable", getPackageName());
            listviewImage[i] = resID;
            listviewdetails[i] = n.getDetails();


        }

//    filling the listview
        List<HashMap<String, String>> aList = new ArrayList<HashMap<String, String>>();

        for (int i = 0; i < the_news.size(); i++) {
            HashMap<String, String> hm = new HashMap<String, String>();
            hm.put("listview_title", listviewTitle[i]);

            hm.put("listview_discription", listviewShortDescription[i]);
            hm.put("listview_image", Integer.toString(listviewImage[i]));
            aList.add(hm);
        }

        String[] from = {"listview_image", "listview_title", "listview_discription"};
        int[] to = {R.id.listview_image, R.id.listview_item_title, R.id.listview_item_short_description};

        SimpleAdapter simpleAdapter = new SimpleAdapter(getBaseContext(), aList, R.layout.listview_activity, from, to);
        ListView androidListView = (ListView) findViewById(R.id.list_view);
        androidListView.setAdapter(simpleAdapter);
        androidListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                showing the selected items details
                Intent myIntent = new Intent(MainActivity.this, Details.class);
                myIntent.putExtra("id", position);


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
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
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



