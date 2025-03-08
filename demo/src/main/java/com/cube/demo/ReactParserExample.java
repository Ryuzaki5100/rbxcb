package com.cube.demo;

import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Value;

public class ReactParserExample {
    public static void main(String[] args) {
        String reactCode = """
            import React, { useState } from 'react';
            import Button from './Button';

            class MyComponent extends React.Component {
                render() {
                    return <div><Button /></div>;
                }
            }

            function FunctionalComponent() {
                const [count, setCount] = useState(0);
                return <div>{count}</div>;
            }
        """;

        parseReactCode(reactCode);
    }

    public static void parseReactCode(String reactCode) {
        try (Context context = Context.create("js")) {
            // Load Babel Parser (UMD build, since require() is not available)
            context.eval("js", """
                var babelCode = fetch("https://unpkg.com/@babel/parser@7.22.5/babel-parser.js")
                    .then(response => response.text())
                    .then(code => eval(code));
            """);

            // Define a function to parse React code
            context.eval("js", """
                function parseReactCode(code) {
                    return JSON.stringify(BabelParser.parse(code, {
                        sourceType: "module",
                        plugins: ["jsx"]
                    }));
                }
            """);

            // Execute the parsing function
            Value parseFunction = context.getBindings("js").getMember("parseReactCode");
            String astJson = parseFunction.execute(reactCode).asString();

            // Print AST JSON
            System.out.println("Parsed AST: " + astJson);
        }
    }
}
