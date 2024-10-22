package com.engine.Rule.Engine;

import com.engine.entity.RuleEntity;
import com.engine.exception.RuleNotFoundException;
import com.engine.model.Node;
import com.engine.repository.RuleRepository;
import com.engine.service.RuleService;

import com.engine.service.RuleParsor;
import com.engine.service.RuleElavoter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Map;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;



public class RuleServiceTest {

    @InjectMocks
    private RuleService ruleService;

    @Mock
    private RuleRepository ruleRepository;

    @Mock
    private RuleParsor ruleParser;

    @Mock
    private RuleElavoter ruleEvaluator;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void testCreateRule() {
        String ruleString = "age > 30 AND department = 'Sales'";
        String ruleName = "SalesRule";
    
        // Operand nodes representing conditions
        Node ageCondition = new Node("operand", "age > 30", null, null); 
        Node departmentCondition = new Node("operand", "department = 'Sales'", null, null);
    
        // The AND operator combining the two conditions
        Node andOperator = new Node("operator", "AND", ageCondition, departmentCondition);
    
        when(ruleParser.parse(ruleString)).thenReturn(andOperator);
    
        ruleService.createRule(ruleString, ruleName);
    
        verify(ruleRepository, times(1)).save(any(RuleEntity.class));
    }


    @Test
    void testEvaluateRule_Success() throws RuleNotFoundException {
        Long ruleId = 1L;
        RuleEntity ruleEntity = new RuleEntity("TestRule", "someAst");
        Map<String, Object> userData = Map.of("age", 35, "department", "Sales");
    
        // Mock the repository to return the rule entity
        when(ruleRepository.findById(ruleId)).thenReturn(Optional.of(ruleEntity));
    
        // Create a valid AST node to mock the parsed AST
        Node ageCondition = new Node("operand", "age > 30", null, null); 
        Node departmentCondition = new Node("operand", "department = 'Sales'", null, null);
        Node andOperator = new Node("operator", "AND", ageCondition, departmentCondition);
    
        // Mock the parser to return a valid AST (AND node)
        when(ruleParser.parse(ruleEntity.getRuleAst())).thenReturn(andOperator);
    
        // Mock the evaluator to return true when the rule is evaluated
        when(ruleEvaluator.evaluate(any(Node.class), any(Map.class))).thenReturn(true);
    
        // Call the service method and assert success
        boolean result = ruleService.evaluateRule(ruleId, userData);
    
        assertTrue(result);
    }
    



    @Test
    void testEvaluateRule_RuleNotFound() {
        Long ruleId = 1L;

        when(ruleRepository.findById(ruleId)).thenReturn(Optional.empty());

        assertThrows(RuleNotFoundException.class, () -> ruleService.evaluateRule(ruleId, Map.of()));
    }

}