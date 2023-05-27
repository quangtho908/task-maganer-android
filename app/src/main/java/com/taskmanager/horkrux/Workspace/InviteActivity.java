package com.taskmanager.horkrux.Workspace;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.taskmanager.horkrux.databinding.InviteUserBinding;

public class InviteActivity extends AppCompatActivity {
    private InviteUserBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        binding.btnCreateInvite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // mời user
            }
        });
        super.onCreate(savedInstanceState);
    }


}
