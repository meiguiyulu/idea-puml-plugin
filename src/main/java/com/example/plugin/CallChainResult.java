package com.example.plugin;

import com.intellij.psi.PsiMethod;
import java.util.Set;

public class CallChainResult {
    private final PsiMethod entryMethod;
    private final Set<PsiMethod> callChain;

    public CallChainResult(PsiMethod entryMethod, Set<PsiMethod> callChain) {
        this.entryMethod = entryMethod;
        this.callChain = callChain;
    }

    public PsiMethod getEntryMethod() {
        return entryMethod;
    }

    public Set<PsiMethod> getCallChain() {
        return callChain;
    }
}