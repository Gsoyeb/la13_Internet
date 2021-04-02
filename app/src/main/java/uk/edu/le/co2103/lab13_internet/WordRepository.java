package uk.edu.le.co2103.lab13_internet;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class WordRepository {
    private WordDao mWordDao;
    private LiveData<List<Word>> mAllWords;


    WordRepository(Application application){
        WordRoomDatabase db = WordRoomDatabase.getDatabase(application);
        mWordDao = db.wordDao();
        mAllWords = mWordDao.getAlphabetizedWord();     //get the alphabetized words from @Dao
    }

    LiveData<List<Word>> getAllWords(){    //Obeserved LiveData will notify the user when data is changed. Not executed in the main UI thread.
        return mAllWords;
    }

    void insert(Word word){
        WordRoomDatabase.databaseWriteExecutor.execute(()->{mWordDao.insert(word);});   //databaseWriteExecutor to make sure it's not executed in the UI thread.
    }

}


/*
    Dao is passed to the repository and not the whole DB. Why? Because it contains all the read and write methods for the DB so no need to expose the entire DB to the repo.
    getAllWords() returns LiveData list of words from Room. Here, LiveData will observe if data is changed and if so, it will update them.
    Because we do not want to run insert in the main thread, we use ExecutorService from WordRoomDatabase to perform insert in a background thread.
 */