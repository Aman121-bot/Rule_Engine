package com.engine.model;

public class Node {
  
        private String type;  // 'operator' or 'operand'
        private String value; // e.g., "> 30", "department", etc.
        private Node left;    // Left child for operators
        private Node right;   // Right child for operators
    
        public Node(String type, String value, Node left, Node right) {
            this.type = type;
            this.value = value;
            this.left = left;
            this.right = right;
        }

    
    
        // Getters and setters
        public String getType() { return type; }
        public void setType(String type) { this.type = type; }
    
        public String getValue() { return value; }
        public void setValue(String value) { this.value = value; }
    
        public Node getLeft() { return left; }
        public void setLeft(Node left) { this.left = left; }
    
        public Node getRight() { return right; }
        public void setRight(Node right) { this.right = right; }
    
    
}
