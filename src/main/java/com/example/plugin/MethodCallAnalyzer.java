package com.example.plugin;

import com.intellij.psi.JavaRecursiveElementVisitor;
import com.intellij.psi.PsiMethod;
import com.intellij.psi.PsiMethodCallExpression;
import com.intellij.psi.util.PsiTreeUtil;
import java.util.LinkedHashSet;
import java.util.Set;

public class MethodCallAnalyzer {
    private final Set<PsiMethod> visitedMethods = new LinkedHashSet<>();

    public CallChainResult analyze(PsiMethod entryMethod) {
        visitedMethods.clear();
        traverseMethods(entryMethod);
        return new CallChainResult(entryMethod, visitedMethods);
    }

    private void traverseMethods(PsiMethod method) {
        if (method == null || visitedMethods.contains(method)) return;
        
        visitedMethods.add(method);
        
        method.accept(new JavaRecursiveElementVisitor() {
            @Override
            public void visitMethodCallExpression(PsiMethodCallExpression expression) {
                super.visitMethodCallExpression(expression);
                PsiMethod calledMethod = expression.resolveMethod();
                if (calledMethod != null && !visitedMethods.contains(calledMethod)) {
                    traverseMethods(calledMethod);
                }
            }
        });
    }
}