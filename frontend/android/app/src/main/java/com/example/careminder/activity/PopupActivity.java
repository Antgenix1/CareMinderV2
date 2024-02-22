package com.example.careminder.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.careminder.R;
import com.example.careminder.adpater.RequestAdapter;
import com.example.careminder.dummy.RequestDummy;

import java.util.ArrayList;
import java.util.List;

public class PopupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup_layout);

    }
}
