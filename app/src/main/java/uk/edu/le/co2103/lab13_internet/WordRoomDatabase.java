package uk.edu.le.co2103.lab13_internet;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Word.class}, version = 1, exportSchema = false)   //tell the entities that belong to the database and version number. DB migration not needed so exportSchema = false
public abstract class WordRoomDatabase extends RoomDatabase {       //database must be abstract and extend RoomDatabase

    public abstract WordDao wordDao();      //database exposes DAOs through an abstract getter for each @Dao

    private static volatile WordRoomDatabase INSTANCE;      //singleton WordRoomDatabase to prevent multiple instances of the DB opened at the same time
    private static final int NUMBER_OF_THREADS = 4;         //number of threads

    static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS); //configure how DB operations will be run: in this case using 4 background threads (asynchronously)

    static WordRoomDatabase getDatabase(final Context context){ //returns a singleton. Will create DB, if accessed, using Room DB builder.
        if (INSTANCE == null){  //checks if the instance created is null. If it is,
            synchronized (WordRoomDatabase.class){      //stop thread interference and ... get the current context from WordRoomDatabase class and name it word_database and build it
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(), WordRoomDatabase.class, "word_database").build();
            }
        }
        return INSTANCE;        //return the singleton database
    }


}


/*
Notes:
    Room is a database layer that sits on top of a SQLite DB
    Room takes DAO to issue its query. It does not allow to issue queries on main thread to avoid poor UI performance. It uses background threads.
    This allows the LiveData to be always ready as run asynchronously in a background thread
    Room allows also compile-time checks of SQLite statements


 */