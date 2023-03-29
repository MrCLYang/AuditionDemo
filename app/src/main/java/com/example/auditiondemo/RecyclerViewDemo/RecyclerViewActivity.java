package com.example.auditiondemo.RecyclerViewDemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.database.DatabaseUtils;
import android.os.Bundle;
import android.view.View;

import com.example.auditiondemo.R;
import com.example.auditiondemo.databinding.ActivityRecyclerViewBinding;

import java.util.List;

public class RecyclerViewActivity extends AppCompatActivity {

    private ActivityRecyclerViewBinding binding;
    WordViewModel wordViewModel;
    MyAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_recycler_view);
        wordViewModel=new ViewModelProvider(this,new ViewModelProvider.AndroidViewModelFactory(getApplication())).get(WordViewModel.class);
        adapter=new MyAdapter(wordViewModel);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setAdapter(adapter);
        wordViewModel.getAllWordLive().observe(this, new Observer<List<Word>>() {
            @Override
            public void onChanged(List<Word> words) {
                int temp=adapter.getItemCount();
                adapter.setAllWords(words);
                if(temp!=words.size()){
                    adapter.notifyDataSetChanged();
                }
            }
        });

        binding.Insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] english={
                        "Hello",
                        "World",
                        "Android",
                        "Google",
                        "Studio",
                        "Project",
                        "Database",
                        "Recycler",
                        "View",
                        "String",
                        "Value",
                        "Integer"
                };
                String[] chinese={
                        "你好",
                        "世界",
                        "安卓系统",
                        "谷歌公司",
                        "工作室",
                        "项目",
                        "数据库",
                        "回收站",
                        "试图",
                        "字符串",
                        "价值",
                        "整数类型",
                };

                for (int i = 0; i < english.length; i++) {
                    wordViewModel.insertWords(new Word(english[i],chinese[i]));
                }
            }
        });

        binding.Clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wordViewModel.deleteAllWords();
            }
        });
    }
}