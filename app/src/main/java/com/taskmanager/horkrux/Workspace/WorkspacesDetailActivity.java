package com.taskmanager.horkrux.Workspace;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.taskmanager.horkrux.Activites.AssignTaskActivity;
import com.taskmanager.horkrux.AdminPanel.TeamMemberList;
import com.taskmanager.horkrux.Models.Task;
import com.taskmanager.horkrux.Models.Users;
import com.taskmanager.horkrux.R;
import com.taskmanager.horkrux.databinding.WorkspaceDetailBinding;
import com.taskmanager.horkrux.ui.home.HomeFragment;

public class WorkspacesDetailActivity extends AppCompatActivity {
    private WorkspaceDetailBinding binding ;
    private String currentName;
    private String currentId;
    private HomeFragment homeFragment;
    private FragmentTransaction fragmentTransaction;
    private FirebaseDatabase database;
    private FirebaseAuth auth;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = WorkspaceDetailBinding.inflate(getLayoutInflater());
        currentName = getIntent().getSerializableExtra("currentName").toString();
        currentId = getIntent().getSerializableExtra("currentId").toString();
        database = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();
        binding.workspaceName.setText(currentName);
        setContentView(binding.getRoot());

        binding.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { finish(); }
        });

        binding.workspaceAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu menu = new PopupMenu(WorkspacesDetailActivity.this, binding.workspaceAction);
                menu.getMenuInflater().inflate(R.menu.workspace_menu, menu.getMenu());

                menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        if(menuItem.getItemId() == R.id.create_task) {
                            Intent intent = new Intent(WorkspacesDetailActivity.this, AssignTaskActivity.class);
                            intent.putExtra("workspaceId", currentId);
                            startActivity(intent);
                            return true;
                        }
                        if(menuItem.getItemId() == R.id.invite_user) {
                            Intent intent = new Intent(WorkspacesDetailActivity.this, TeamMemberList.class);
                            intent.putExtra("workspaceId", currentId);
                            startActivity(intent);
                            return true;
                        }
                        if(menuItem.getItemId() == R.id.delete_workspace) {
                            deleteWorkspace();
                            finishAndRemoveTask();
                            return true;
                        }
                        return false;
                    }
                });

                menu.show();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        homeFragment = new HomeFragment(currentId, "");
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.currentActivity, homeFragment);
        transaction.commit();
    }

    private void deleteWorkspace() {
        database.getReference().child("workspaces/" + currentId)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshotWp) {
                        snapshotWp.getRef().removeValue();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {}
                });

        database.getReference().child("tasks")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot snap : snapshot.getChildren()) {
                            if(snap.getValue(Task.class).getWorkspaceId().equals(currentId)) {
                                snap.getRef().removeValue();
                                break;
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {}
                });

        DatabaseReference workspaceUser = database.getReference().child("Users");
        workspaceUser.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot snap : snapshot.getChildren()) {
                    Users user = snap.getValue(Users.class);
                    if((user.getWorkspaces() != null) && user.getWorkspaces().contains(currentId)) {
                        user.getWorkspaces().remove(currentId);
                        workspaceUser.child(user.getFireuserid() + "/workspaces").setValue(user.getWorkspaces());
                        return;
                    }

                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {}
        });
    }
}
;