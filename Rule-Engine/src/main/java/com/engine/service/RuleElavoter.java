package com.engine.service;


import com.engine.model.Node;
import java.util.Map;
public class RuleElavoter {

  



    public boolean evaluate(Node node, Map<String, Object> data) {
        if (node.getType().equals("operand")) {
            String condition = node.getValue();
            if (condition.contains(">")) {
                String[] parts = condition.split(">");
                return (int) data.get(parts[0].trim()) > Integer.parseInt(parts[1].trim());
            } else if (condition.contains("<")) {
                String[] parts = condition.split("<");
                return (int) data.get(parts[0].trim()) < Integer.parseInt(parts[1].trim());
            } else if (condition.contains("=")) {
                String[] parts = condition.split("=");
                return data.get(parts[0].trim()).equals(parts[1].trim().replace("'", ""));
            }
        } else if (node.getType().equals("operator")) {
            if (node.getValue().equals("AND")) {
                return evaluate(node.getLeft(), data) && evaluate(node.getRight(), data);
            } else if (node.getValue().equals("OR")) {
                return evaluate(node.getLeft(), data) || evaluate(node.getRight(), data);
            }
        }
        return false;
    }
}


