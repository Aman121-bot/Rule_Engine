package com.engine.service;


import com.engine.entity.RuleEntity;
import com.engine.exception.RuleNotFoundException;
import com.engine.model.Node;
import com.engine.repository.RuleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.engine.service.RuleElavoter;


@Service
public class RuleService {

  





    private static final Logger logger = LoggerFactory.getLogger(RuleService.class);

    @Autowired
    private RuleRepository ruleRepository;

    @Autowired
    private RuleParsor ruleParser;

    @Autowired
    private RuleElavoter ruleEvaluator;

    public void createRule(String ruleString, String ruleName) {
        try {
            logger.info("Creating rule: {}", ruleName);
            Node ast = ruleParser.parse(ruleString);
            RuleEntity ruleEntity = new RuleEntity(ruleName, ast.toString());
            ruleRepository.save(ruleEntity);
            logger.info("Rule created successfully: {}", ruleName);
        } catch (Exception e) {
            logger.error("Error while creating rule: {}", ruleName, e);
            throw new RuntimeException("Error creating rule: " + ruleName, e);
        }
    }

    public boolean evaluateRule(Long ruleId, Map<String, Object> data) throws RuleNotFoundException {
        try {
            logger.info("Evaluating rule with ID: {}", ruleId);
            Object ruleEntity = ruleRepository.findById(ruleId)
                    .orElseThrow(() -> new RuleNotFoundException("Rule not found with ID: " + ruleId));
            Node ast = ruleParser.parse(((RuleEntity) ruleEntity).getRuleAst());
            boolean result = ruleEvaluator.evaluate(ast, data);
            logger.info("Rule evaluation result for rule ID {}: {}", ruleId, result);
            return result;
        } catch (RuleNotFoundException e) {
            logger.error("Error: {}", e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            logger.error("Error evaluating rule with ID: {}", ruleId, e);
            throw new RuntimeException("Error evaluating rule with ID: " + ruleId, e);
        }
    }
}


