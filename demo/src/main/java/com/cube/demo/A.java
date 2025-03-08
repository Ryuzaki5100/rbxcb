package com.cube.demo;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseResult;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.ImportDeclaration;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.util.ArrayList;
import java.util.List;

public class A {

    public static void main(String[] args) {
        String javaCode = "import java.util.List;\n" +
                "import java.util.ArrayList;\n" +
                "\n" +
                "public class Example extends BaseClass implements InterfaceA, InterfaceB {\n" +
                "    private int count;\n" +
                "    private String name;\n" +
                "\n" +
                "    public void exampleMethod() {\n" +
                "        List<String> list = new ArrayList<>();\n" +
                "        list.add(\"Hello, World!\");\n" +
                "    }\n" +
                "    \n" +
                "    public int addNumbers(int a, int b) {\n" +
                "        return a + b;\n" +
                "    }\n" +
                "}";

        parseJavaCode(javaCode);
    }

    public static void parseJavaCode(String javaCode) {
        ParseResult<CompilationUnit> parseResult = new JavaParser().parse(javaCode);

        if (parseResult.isSuccessful() && parseResult.getResult().isPresent()) {
            CompilationUnit cu = parseResult.getResult().get();

            // Create a visitor to extract elements
            CodeVisitor visitor = new CodeVisitor();
            cu.accept(visitor, null);

            // Print extracted elements
            System.out.println("Imports:");
            for (String imp : visitor.getImports()) {
                System.out.println("  " + imp);
            }

            System.out.println("\nClasses:");
            for (ClassOrInterfaceDeclaration cls : visitor.getClasses()) {
                System.out.println("Class: " + cls.getNameAsString());

                // Superclass (if any)
                if (cls.getExtendedTypes().isNonEmpty()) {
                    System.out.println("  Extends: " + cls.getExtendedTypes().get(0).getNameAsString());
                }

                // Implemented interfaces (if any)
                if (cls.getImplementedTypes().isNonEmpty()) {
                    System.out.print("  Implements: ");
                    cls.getImplementedTypes().forEach(i -> System.out.print(i.getNameAsString() + " "));
                    System.out.println();
                }

                // Class Variables
                System.out.println("  Variables:");
                for (String field : visitor.getFields(cls)) {
                    System.out.println("    - " + field);
                }

                // Methods with Full Body
                System.out.println("  Methods:");
                for (MethodDeclaration method : cls.getMethods()) {
                    System.out.println("    - " + method.getDeclarationAsString());
                    method.getBody().ifPresent(body -> System.out.println("      Body:\n" + body.toString()));
                }
            }
        } else {
            System.err.println("Parsing failed: " + parseResult.getProblems());
        }
    }

    private static class CodeVisitor extends VoidVisitorAdapter<Void> {
        private List<String> imports = new ArrayList<>();
        private List<ClassOrInterfaceDeclaration> classes = new ArrayList<>();
        private List<FieldDeclaration> fields = new ArrayList<>();

        @Override
        public void visit(ImportDeclaration n, Void arg) {
            super.visit(n, arg);
            imports.add(n.getNameAsString());
        }

        @Override
        public void visit(ClassOrInterfaceDeclaration n, Void arg) {
            super.visit(n, arg);
            classes.add(n);
        }

        @Override
        public void visit(FieldDeclaration n, Void arg) {
            super.visit(n, arg);
            fields.add(n);
        }

        public List<String> getImports() {
            return imports;
        }

        public List<ClassOrInterfaceDeclaration> getClasses() {
            return classes;
        }

        public List<String> getFields(ClassOrInterfaceDeclaration cls) {
            List<String> fieldList = new ArrayList<>();
            for (FieldDeclaration field : fields) {
                if (cls.findAncestor(CompilationUnit.class).isPresent() &&
                        cls.findAncestor(CompilationUnit.class).get().equals(field.findAncestor(CompilationUnit.class).get())) {
                    field.getVariables().forEach(var -> fieldList.add(var.getType() + " " + var.getNameAsString()));
                }
            }
            return fieldList;
        }
    }
}
