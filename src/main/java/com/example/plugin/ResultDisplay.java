package com.example.plugin;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.ui.EditorTextField;
import org.jetbrains.annotations.Nullable;
import javax.swing.*;

public class ResultDisplay extends DialogWrapper {
    private final EditorTextField editor;

    public ResultDisplay(Project project) {
        super(project);
        editor = new EditorTextField("", project, com.intellij.openapi.fileTypes.PlainTextFileType.INSTANCE);
        editor.setPreferredSize(new java.awt.Dimension(600, 400));
        setTitle("PlantUML Code");
        init();
    }

    @Override
    protected @Nullable JComponent createCenterPanel() {
        return editor;
    }

    public void show(String uml) {
        editor.setText(uml);
        show();
    }
}