This file explains my code a little bit.
first I started creating a simple database with the help of SQLiteOpenHelper (DbHandler) 
but then I decided to keep it more simple (yet I kept the class)
thus I just created a class theNews which contains the main elements of 
each news: 
ID,
Title,
Description
and the Details
This class contains all the methods needed to retrieve and update the info 
of the news (get and set methods)

Then in the main activity which lists all the news, I created an arraylist of
instances of this class (theNews)
I added a simple 5 news.
Once you click on any item the app will direct you to the details page.
You will find an Update Info button which will direct you "on click" to a page
where you can edit the title, description and the details.
The submit changes will take you back to the main menu where you will notice 
the changes you have done. 
In the toolbar once you click on the menu item there is an exit button which
will help you exit the app whenever you want to.

