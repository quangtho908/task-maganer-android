package com.taskmanager.horkrux.Workspace;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.taskmanager.horkrux.Activites.AboutUs;
import com.taskmanager.horkrux.Activites.MainActivity;
import com.taskmanager.horkrux.Activites.Profile;
import com.taskmanager.horkrux.AuthNew.NewLoginActivity;
import com.taskmanager.horkrux.CommonUtils;
import com.taskmanager.horkrux.Models.Count;
import com.taskmanager.horkrux.Models.Users;
import com.taskmanager.horkrux.R;
import com.taskmanager.horkrux.databinding.CreateWorkspaceBinding;
import com.taskmanager.horkrux.databinding.NavHeaderMainBinding;
import com.taskmanager.horkrux.databinding.WorkspaceListBinding;
import com.taskmanager.horkrux.ui.home.HomeFragment;

public class WorkspacesActivity extends AppCompatActivity {
    private WorkspaceListBinding binding;
    public DrawerLayout drawerLayout;
    private NavHeaderMainBinding navHeaderMainBinding;
    private NavigationView navigationView;
    private FirebaseDatabase database;
    private FirebaseAuth auth;
    private String USER_PATH;
    public static Count count;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = WorkspaceListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        count = new Count(0, 0, 0, 0);
        database = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();
        drawerLayout = findViewById(R.id.my_drawer_layout);
        USER_PATH = "Users/" + auth.getUid() + "/";
        binding.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        binding.addWorkSpace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialogCreateWorkspace();
            }
        });
    }

    private void showDialogCreateWorkspace(){
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.create_workspace);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int screenWidth = displayMetrics.widthPixels;
        int dialogWidth = (int) (screenWidth * 0.9);
        int dialogHeight = ViewGroup.LayoutParams.WRAP_CONTENT;
        Window window = dialog.getWindow();
        window.setLayout(dialogWidth, dialogHeight);
        CreateWorkspaceBinding createWorkspaceBinding = CreateWorkspaceBinding.inflate(getLayoutInflater());
        createWorkspaceBinding.btnCreateWorkspace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        dialog.show();
    }

}
