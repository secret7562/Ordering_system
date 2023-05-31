package com.example.ordering_system;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private String[] Dish = {"美味蟹堡", "義式香腸堡", "蔬菜水果堡", "香蕉潛艇堡", "香烤雞肉堡"};
    private boolean[] chooseDish = {false, false, false, false, false};
    private boolean[] lastCheckedArray = {false, false, false, false, false};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btOrderSystem = findViewById(R.id.btSystem);
        btOrderSystem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 在這裡顯示浮動視窗
//                isCheckedArray = Arrays.copyOf(lastCheckedArray, lastCheckedArray.length);
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("選擇菜單").setMultiChoiceItems(Dish, chooseDish, new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                                // 在這裡處理菜單選擇狀態的變化
                                chooseDish[which] = isChecked;
                            }
                        })
                        .setPositiveButton("確定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // 在這裡處理確定按鈕的點擊事件
                                lastCheckedArray = Arrays.copyOf(chooseDish, chooseDish.length);
                                updateOrderText();
                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                chooseDish = Arrays.copyOf(lastCheckedArray, lastCheckedArray.length);
                            }
                        });

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }
    public void updateOrderText(){
        StringBuilder selectedItems = new StringBuilder();
        for (int i = 0; i < chooseDish.length; i++) {
            if (chooseDish[i]) {
                selectedItems.append(Dish[i]).append("\n");
            }
        }
        TextView orderTextView = findViewById(R.id.tOrderText);
        orderTextView.setText(selectedItems.toString());
    }
}