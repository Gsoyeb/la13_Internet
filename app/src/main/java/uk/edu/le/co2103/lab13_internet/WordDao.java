package uk.edu.le.co2103.lab13_internet;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface WordDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)         //special annotation where no SQL is needed. Other are @Delete and @Update. onConflict ingnore if the word is in DB already.
    void insert(Word word);                                 //method to insert one word.

    @Query("delete from word_table")                        //No annotations, like @Insert for deleting multiple entities.
    void deleteAll();                                       //delete all words.

@Query("select * from word_table order by word asc")        //Returns a list of word in ascending order.
    LiveData<List<Word>> getAlphabetizedWord();             //method that will return a list of words in order. Add LiveData to constantly get the updated Database values
}

/*
* Notes:
*   DAO validates the SQL code at compile time and associates it with a method.
*   Here we are going to use it for getting all words alphabetically, inserting words and deleting all words.
*
* */

/*
    New notes:
        When data changed we need to take some actions. How do we do that?
            We need to observe the data so that we can react when a change happens.
            But observing data across multiple components can be tricky. To solve the issue we need
            LiveData that can update data when database is updated.
                We need to put LiveData on getAlpahbetizedWord() coz it's the only one that is needed
                for our components to work. We need its live data.
 */
