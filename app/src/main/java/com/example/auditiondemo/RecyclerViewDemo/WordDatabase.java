package com.example.auditiondemo.RecyclerViewDemo;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import org.jetbrains.annotations.NotNull;

@Database(entities = {Word.class},version = 2,exportSchema = false)
public abstract class WordDatabase extends RoomDatabase {
    private static WordDatabase INSTANCE;
    static synchronized WordDatabase getDatabase(Context context){
        if(INSTANCE==null){
            INSTANCE= Room.databaseBuilder(context.getApplicationContext(),WordDatabase.class,"word_database")
                    .addMigrations(MIGRATION_1_2)
                    .build();
        }
        return INSTANCE;
    }
    public abstract WordDao getWordDao();
    static final Migration MIGRATION_1_2=new Migration(1,2) {
        @Override
        public void migrate(@NonNull @NotNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE word ADD COLUMN chinese_invisible INTEGER NOT NULL DEFAULT 0 ");
        }
    };
}
