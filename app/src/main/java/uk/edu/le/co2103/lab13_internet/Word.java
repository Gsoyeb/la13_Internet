package uk.edu.le.co2103.lab13_internet;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "word_table")       //this makes a table in SQLite
public class Word {
    @PrimaryKey(autoGenerate = false)   //telling the SQLite the pk of the table word_table
    @NonNull                            //value cannot be null since pk
    @ColumnInfo(name = "word")          //specifies the name of the column in the table word_table
    private String mWord;

    public Word(@NonNull String word){  //getting rid of the setter and allowing direct data input. Not good practice
        this.mWord = word;
    }

    public String getWord() {           //it's a must like a setter (in our case constructor)
        return this.mWord;
    }

}
