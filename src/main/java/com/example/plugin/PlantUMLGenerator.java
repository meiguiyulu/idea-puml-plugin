package com.example.plugin;

import com.intellij.psi.PsiMethod;
import java.util.List;
import java.util.stream.Collectors;

public class PlantUMLGenerator {
    public String generate(CallChainResult result) {
        StringBuilder uml = new StringBuilder();
        uml.append("@startuml\n");
        uml.append("participant \"" + getSimpleName(result.getEntryMethod()) + "\" as entry\n");
        
        List<PsiMethod> methods = result.getCallChain().stream()
                .filter(m -> !m.equals(result.getEntryMethod()))
                .toList();
        
        for (PsiMethod method : methods) {
            uml.append("participant \"" + getSimpleName(method) + "\" as m" + method.hashCode() + "\n");
        }
        
        uml.append("\nentry -> entry : " + getMethodSignature(result.getEntryMethod()) + "\n");
        
        for (PsiMethod method : methods) {
            uml.append("entry -> m" + method.hashCode() + " : " + getMethodSignature(method) + "\n");
        }
        
        uml.append("@enduml");
        return uml.toString();
    }
    
    private String getSimpleName(PsiMethod method) {
        return method.getContainingClass() != null 
                ? method.getContainingClass().getName() + "::" + method.getName() 
                : method.getName();
    }
    
    private String getMethodSignature(PsiMethod method) {
        return method.getName() + "()";
    }
}