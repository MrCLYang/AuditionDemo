package com.example.auditiondemo.RecyclerViewDemo;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class WordViewModel extends AndroidViewModel {

    private WordDao wordDao;
    private WordRepository wordRepository;
    public WordViewModel(@NonNull @NotNull Application application) {
        super(application);
        wordRepository=new WordRepository(application);
    }

    public LiveData<List<Word>> getAllWordLive(){
        return wordRepository.getAllWordLive();
    }

    void insertWords(Word... words){
        wordRepository.insertWords(words);
    }

    void updateWords(Word ... words){
        wordRepository.updateWords(words);
    }

    void deleteWords(Word ... words){
        wordRepository.deleteWords(words);
    }

    void deleteAllWords(){
        wordRepository.deleteAllWords();
    }


}
