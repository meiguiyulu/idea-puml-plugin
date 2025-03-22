package com.example.plugin;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.Project;
import com.intellij.psi.*;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.fileEditor.FileEditorManager;

public class MethodAnalysisAction extends AnAction {
    @Override
    public void actionPerformed(AnActionEvent e) {
        Project project = e.getProject();
        if (project == null) return;

        // TODO: 实现方法选择对话框和调用链分析
        PsiMethod selectedMethod = getSelectedMethod(project);
        MethodCallAnalyzer analyzer = new MethodCallAnalyzer();
        CallChainResult result = analyzer.analyze(selectedMethod);
        
        PlantUMLGenerator generator = new PlantUMLGenerator();
        String uml = generator.generate(result);
        
        new ResultDisplay(project).show(uml);
    }

    private PsiMethod getSelectedMethod(Project project) {
        Editor editor = FileEditorManager.getInstance(project).getSelectedTextEditor();
        if (editor == null) return null;
        
        PsiFile psiFile = PsiDocumentManager.getInstance(project).getPsiFile(editor.getDocument());
        int offset = editor.getCaretModel().getOffset();
        
        PsiElement element = psiFile.findElementAt(offset);
        return PsiTreeUtil.getParentOfType(element, PsiMethod.class);
    }
}