package com.engine.controller;


    import com.engine.exception.RuleNotFoundException;
import com.engine.service.RuleService;
    import org.slf4j.Logger;
    import org.slf4j.LoggerFactory;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.web.bind.annotation.*;
    import java.util.Map;

    @RestController
    @RequestMapping("/api/rules")
public class RuleController {
        private static final Logger logger = LoggerFactory.getLogger(RuleController.class);
    
        @Autowired
        private RuleService ruleService;
    
        @PostMapping("/create")
        public String createRule(@RequestBody Map<String, String> request) {
            try {
                logger.info("Request to create rule received: {}", request.get("name"));
                ruleService.createRule(request.get("rule"), request.get("name"));
                return "Rule created successfully.";
            } catch (Exception e) {
                logger.error("Error while creating rule", e);
                return "Error creating rule: " + e.getMessage();
            }
        }
    
        @PostMapping("/evaluate/{ruleId}")
        public boolean evaluateRule(@PathVariable Long ruleId, @RequestBody Map<String, Object> userData) throws RuleNotFoundException {
            try {
                logger.info("Request to evaluate rule ID: {}", ruleId);
                return ruleService.evaluateRule(ruleId, userData);
            } catch (Exception e) {
                logger.error("Error while evaluating rule ID: {}", ruleId, e);
                return false;
            }
        }
    }
    


