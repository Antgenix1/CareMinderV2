package com.example.careminder.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.careminder.R;
import com.example.careminder.view.NurseItemView;

import java.util.ArrayList;
import java.util.List;

public class NurseActivity extends AppCompatActivity {
    ListView itemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nurse);

        itemList = findViewById(R.id.nurse_list_left);

        addDummy();

        AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView parent, View v, int position, long id)  {
                ConstraintLayout bottom = v.findViewById(R.id.nurse_item_chat);

                if (bottom.getVisibility() == View.GONE) {
                    bottom.setVisibility(View.VISIBLE);
                } else {
                    bottom.setVisibility(View.GONE);
                }
            }
        };
        itemList.setOnItemClickListener(onItemClickListener);
    }

    private void setLayout(List<NurseItemView> productItemList) {
        final ArrayAdapter<NurseItemView> itemAdapter = new ArrayAdapter<NurseItemView>(getApplicationContext(), R.layout.activity_nurse_item) {
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                if (convertView == null) {
                    convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_nurse_item, parent, false);
                }

                NurseItemView nurseItemView = getItem(position);

                ConstraintLayout bzInfo = convertView.findViewById(R.id.nurse_item_chat);

                ConstraintLayout top = convertView.findViewById(R.id.nurse_item_background);
                ConstraintLayout bottom = convertView.findViewById(R.id.nurse_item_chat);
                ImageView nurse_item_logo = convertView.findViewById(R.id.nurse_item_logo);
                TextView nurse_item_title = convertView.findViewById(R.id.nurse_item_title);
                TextView nurse_item_time = convertView.findViewById(R.id.nurse_item_time);
                TextView nurse_item_description = convertView.findViewById(R.id.nurse_item_description);

                if (nurseItemView.getType()) { nurse_item_logo.setImageResource(R.drawable.circled_arrow); }
                else { nurse_item_logo.setImageResource(R.drawable.help); }

                nurse_item_title.setText(nurseItemView.getTitle());
                nurse_item_time.setText(nurseItemView.getTime());
                nurse_item_description.setText(nurseItemView.getDescription());

                if (nurseItemView.getColor()) {
                    top.setBackgroundResource(R.drawable.nurse_item_blue);
                    bottom.setBackgroundResource(R.drawable.nurse_item_chat_blue);
                }
                else {
                    top.setBackgroundResource(R.drawable.nurse_item_yellow);
                    bottom.setBackgroundResource(R.drawable.nurse_item_chat_yellow);
                }

                bottom.setVisibility(View.GONE);

                return convertView;
            }
        };

        itemAdapter.addAll(productItemList);
        itemList.setAdapter(itemAdapter);
    }

    private void addDummy() {
        List<NurseItemView> nurseItemView = new ArrayList<>();

        nurseItemView.add(new NurseItemView(true, true, "병상위치(이름)", "지금 이불이 거의 여름용인 것 같아서 너무...", "요청시간"));
        nurseItemView.add(new NurseItemView(true, false, "병상위치(이름)", "지금 이불이 거의 여름용인 것 같아서 너무...", "요청시간"));
        nurseItemView.add(new NurseItemView(true, true, "병상위치(이름)", "지금 이불이 거의 여름용인 것 같아서 너무...", "요청시간"));

        nurseItemView.add(new NurseItemView(false, true, "병상위치(이름)", "지금 이불이 거의 여름용인 것 같아서 너무...", "요청시간"));
        nurseItemView.add(new NurseItemView(false, false, "병상위치(이름)", "지금 이불이 거의 여름용인 것 같아서 너무...", "요청시간"));

        setLayout(nurseItemView);
    }
}