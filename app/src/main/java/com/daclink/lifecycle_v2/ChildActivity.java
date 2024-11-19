package com.daclink.lifecycle_v2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.daclink.lifecycle_v2.databinding.ActivityChildBinding;

/**
 * @author Tariq Kakar
 * Date: 18-november-2024
 * Description: Creates a context where a button's display is related to the state of MainActivity or
 * a click by the user.
 */

public class ChildActivity extends AppCompatActivity {

    private static final String SHOW_MESSAGE_ONE = "com.daclink.lifecycle_v2.extraValue";
    ActivityChildBinding binding;
    private boolean showMessage1Child;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChildBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        showMessage1Child = getIntent().getBooleanExtra(SHOW_MESSAGE_ONE, false);

        binding.ChildActivitybutton.setText(showMessage1Child ? "TRUE!" : "FALSE");

        binding.ChildActivitybutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMessage1Child = !showMessage1Child;
                binding.ChildActivitybutton.setText(showMessage1Child ? "TRUE!" : "FALSE");
            }
        });

        binding.ChildActivitybutton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Intent intent = MainActivity.intentFactory(getApplicationContext(), showMessage1Child);
                startActivity(intent);
                return false;
            }
        });

    }

    static Intent intentFactory(Context context, boolean messageValue){
        Intent intent = new Intent(context, ChildActivity.class);
        intent.putExtra(SHOW_MESSAGE_ONE, messageValue);
        return intent;
    }
}