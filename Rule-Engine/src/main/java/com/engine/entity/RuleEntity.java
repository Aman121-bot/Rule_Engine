package com.engine.entity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "rules")
public class RuleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "rule_name", nullable = false)
    private String ruleName;

    @Column(name = "rule_ast", columnDefinition = "TEXT", nullable = false)
    private String ruleAst;

    public RuleEntity() { }

    public RuleEntity(String ruleName, String ruleAst) {
        this.ruleName = ruleName;
        this.ruleAst = ruleAst;
    }

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getRuleName() { return ruleName; }
    public void setRuleName(String ruleName) { this.ruleName = ruleName; }

    public String getRuleAst() { return ruleAst; }
    public void setRuleAst(String ruleAst) { this.ruleAst = ruleAst; }
}
   
