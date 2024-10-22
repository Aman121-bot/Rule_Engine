package com.engine.service;

import com.engine.model.Node;
import java.util.Stack;
public class RuleParsor {



    public Node parse(String ruleString) {
        String[] tokens = tokenize(ruleString);
        Stack<String> tokenStack = new Stack<>();
        
        for (int i = tokens.length - 1; i >= 0; i--) {
            tokenStack.push(tokens[i]);
        }

        return buildAST(tokenStack);
    }

    private String[] tokenize(String ruleString) {
        return ruleString.split(" ");
    }

    private Node buildAST(Stack<String> tokens) {
        String token = tokens.pop();
        if (token.equals("(")) {
            Node left = buildAST(tokens);
            String operator = tokens.pop();  // AND/OR
            Node right = buildAST(tokens);
            tokens.pop();  // discard ')'
            return new Node("operator", operator, left, right);
        } else {
            return new Node("operand", token, null, null);  // Operand node
        }
    }
}



