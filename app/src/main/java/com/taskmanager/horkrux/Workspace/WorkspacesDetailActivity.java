package com.taskmanager.horkrux.Workspace;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.taskmanager.horkrux.Activites.AssignTaskActivity;
import com.taskmanager.horkrux.R;
import com.taskmanager.horkrux.databinding.WorkspaceDetailBinding;

public class WorkspacesDetailActivity extends AppCompatActivity {
    private WorkspaceDetailBinding binding ;
    private String currentName;
    private String currentId;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = WorkspaceDetailBinding.inflate(getLayoutInflater());
        currentName = getIntent().getSerializableExtra("currentName").toString();
        currentId = getIntent().getSerializableExtra("currentId").toString();
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
                        return false;
                    }
                });

                menu.show();
            }
        });
    }
}
;