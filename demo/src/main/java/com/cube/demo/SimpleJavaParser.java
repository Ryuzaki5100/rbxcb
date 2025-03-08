package com.cube.demo;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseResult;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.ImportDeclaration;
import com.github.javaparser.ast.body.*;
import com.github.javaparser.ast.nodeTypes.NodeWithAnnotations;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SimpleJavaParser {

    public static void main(String[] args) {
        String javaCode = "import java.util.List;\n" +
                "import java.util.ArrayList;\n" +
                "\n" +
                "@CustomClassAnnotation\n" +
                "public class Example extends BaseClass implements InterfaceA, InterfaceB {\n" +
                "    @VariableAnnotation\n" +
                "    private int count;\n" +
                "    private String name;\n" +
                "\n" +
                "    @MethodAnnotation\n" +
                "    public void exampleMethod(String message, int times) {\n" +
                "        List<String> list = new ArrayList<>();\n" +
                "        list.add(\"Hello, World!\");\n" +
                "    }\n" +
                "}";

//        JavaFileStructure parsedStructure = parseJavaCode(javaCode);
        System.out.println(SimpleJavaParser.parseCodeToJSON(javaCode));
    }

    public static JavaFileStructure parseJavaCode(String javaCode) {
        ParseResult<CompilationUnit> parseResult = new JavaParser().parse(javaCode);

        if (parseResult.isSuccessful() && parseResult.getResult().isPresent()) {
            CompilationUnit cu = parseResult.getResult().get();
            JavaFileVisitor visitor = new JavaFileVisitor();
            cu.accept(visitor, null);
            return visitor.getJavaFileStructure();
        } else {
            System.err.println("Parsing failed: " + parseResult.getProblems());
            return null;
        }
    }

    public static String parseCodeToJSON(String fileContent) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT); // Pretty print

        try {
            JavaFileStructure parsedStructure = SimpleJavaParser.parseJavaCode(fileContent);

            if (parsedStructure == null) {
                return objectMapper.writeValueAsString(Map.of("error", "Parsing failed"));
            }

            return objectMapper.writeValueAsString(parsedStructure);
        } catch (Exception e) {
            e.printStackTrace();
            try {
                return objectMapper.writeValueAsString(Map.of("error", "Exception occurred: " + e.getMessage()));
            } catch (Exception ex) {
                return "{\"error\": \"Critical JSON serialization failure\"}";
            }
        }
    }

    private static class JavaFileVisitor extends VoidVisitorAdapter<Void> {
        private final JavaFileStructure javaFileStructure = new JavaFileStructure();
        private JavaFileStructure.JavaClassStructure currentClass = null;

        @Override
        public void visit(ImportDeclaration n, Void arg) {
            javaFileStructure.imports.add(n.getNameAsString());
            super.visit(n, arg);
        }

        @Override
        public void visit(ClassOrInterfaceDeclaration n, Void arg) {
            currentClass = new JavaFileStructure.JavaClassStructure();
            currentClass.name = n.getNameAsString();
            currentClass.annotations = extractAnnotations(n);
            currentClass.extendedClass = n.getExtendedTypes().isNonEmpty() ? n.getExtendedTypes().get(0).getNameAsString() : "";
            currentClass.implementedInterfaces = n.getImplementedTypes().stream().map(t -> t.getNameAsString()).toList();
            javaFileStructure.classes.add(currentClass);

            // Now visit the children AFTER setting currentClass
            super.visit(n, arg);
        }

        @Override
        public void visit(FieldDeclaration n, Void arg) {
            if (currentClass == null) {
                System.err.println("Warning: FieldDeclaration found but no class exists in the structure.");
                return;
            }
            for (VariableDeclarator var : n.getVariables()) {
                JavaFileStructure.JavaFieldStructure field = new JavaFileStructure.JavaFieldStructure();
                field.name = var.getNameAsString();
                field.type = var.getType().toString();
                field.annotations = extractAnnotations(n);
                currentClass.fields.add(field);
            }
            super.visit(n, arg);
        }

        @Override
        public void visit(MethodDeclaration n, Void arg) {
            if (currentClass == null) {
                System.err.println("Warning: MethodDeclaration found but no class exists in the structure.");
                return;
            }
            JavaFileStructure.JavaMethodStructure method = new JavaFileStructure.JavaMethodStructure();
            method.name = n.getNameAsString();
            method.returnType = n.getType().toString();
            method.annotations = extractAnnotations(n);
            method.parameters = n.getParameters().stream()
                    .map(param -> param.getType() + " " + param.getNameAsString())
                    .toList();
            method.body = n.getBody().map(Object::toString).orElse("");
            currentClass.methods.add(method);
            super.visit(n, arg);
        }

        private List<String> extractAnnotations(NodeWithAnnotations<?> node) {
            return node.getAnnotations().stream().map(a -> a.getNameAsString()).toList();
        }

        public JavaFileStructure getJavaFileStructure() {
            return javaFileStructure;
        }
    }
}


@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
class JavaFileStructure {
    List<String> imports = new ArrayList<>();
    List<JavaClassStructure> classes = new ArrayList<>();


    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    static class JavaClassStructure {
        String name;
        String extendedClass;
        List<String> implementedInterfaces = new ArrayList<>();
        List<String> annotations = new ArrayList<>();
        List<JavaFieldStructure> fields = new ArrayList<>();
        List<JavaMethodStructure> methods = new ArrayList<>();
    }


    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    static class JavaFieldStructure {
        String name;
        String type;
        List<String> annotations = new ArrayList<>();
    }


    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    static class JavaMethodStructure {
        String name;
        String returnType;
        List<String> parameters = new ArrayList<>();
        List<String> annotations = new ArrayList<>();
        String body;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Imports:\n");
        imports.forEach(i -> sb.append("  ").append(i).append("\n"));

        for (JavaClassStructure cls : classes) {
            sb.append("\nClass: ").append(cls.name).append("\n");
            if (!cls.annotations.isEmpty()) {
                sb.append("  Annotations: ").append(cls.annotations).append("\n");
            }
            if (!cls.extendedClass.isEmpty()) {
                sb.append("  Extends: ").append(cls.extendedClass).append("\n");
            }
            if (!cls.implementedInterfaces.isEmpty()) {
                sb.append("  Implements: ").append(cls.implementedInterfaces).append("\n");
            }
            sb.append("  Fields:\n");
            for (JavaFieldStructure field : cls.fields) {
                sb.append("    - ").append(field.type).append(" ").append(field.name);
                if (!field.annotations.isEmpty()) {
                    sb.append(" (Annotations: ").append(field.annotations).append(")");
                }
                sb.append("\n");
            }
            sb.append("  Methods:\n");
            for (JavaMethodStructure method : cls.methods) {
                sb.append("    - ").append(method.returnType).append(" ").append(method.name)
                        .append("(").append(String.join(", ", method.parameters)).append(")\n");
                if (!method.annotations.isEmpty()) {
                    sb.append("      Annotations: ").append(method.annotations).append("\n");
                }
                sb.append("      Body: ").append(method.body).append("\n");
            }
        }
        return sb.toString();
    }
}