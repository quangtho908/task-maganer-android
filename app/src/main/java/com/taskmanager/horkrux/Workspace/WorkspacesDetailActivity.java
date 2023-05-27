package com.taskmanager.horkrux.Workspace;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.taskmanager.horkrux.databinding.WorkspaceDetailBinding;

public class WorkspacesDetailActivity extends AppCompatActivity {
    private WorkSpaceDetailBinding binding ;
    final String[] taskCategories = {"ALL Tasks", "TO-DO", "IN PROGRESS", "DONE"};
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        binding = WorkSpaceDetailBinding.inflate(getLayoutInflater());
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,taskCategories);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.taskCategory.setAdapter(adapter);
        binding.taskCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                // xử lý khi được chọn
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        super.onCreate(savedInstanceState);
    }
}
