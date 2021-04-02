package uk.edu.le.co2103.lab13_internet;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class WordViewModel extends AndroidViewModel {
    private WordRepository mRepository;
    private final LiveData<List<Word>> mAllWords;

    public WordViewModel(@NonNull Application application) { //application as parameter as requested in the Repository class
        super(application);
        this.mRepository = new WordRepository(application); //called the constructor for initializing the repository with the Application, which should be in UI. Hence, repo is updated with UI values.
        this.mAllWords = mRepository.getAllWords();         //get all the words from the repository
    }

    public LiveData<List<Word>> getAllWords() {
        return mAllWords;                   //send all the words back to the UI from the Repository
    }

    public void insert(Word word){          //get the call from the UI with parameter of type Word. Save it in the db by calling ...
        mRepository.insert(word);          //insert, which in turn inserts Word into the @Dao, which in turn inserts it into WordRoomDatabase using databaseWriteExecutor.
    }
}

/*
ViewModel's role is to provide data to the UI and survive configuration changes (like changing rotation).
it acts as a communication between UI and repository.
 */