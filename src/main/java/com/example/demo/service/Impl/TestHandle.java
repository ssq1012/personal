package com.example.demo.service.Impl;

import com.example.demo.domain.SecurityGroupRuleStatus;
import org.springframework.stereotype.Component;

/**
 * @author ssq
 * @create on 2022/08/31
 */
@Component
public class TestHandle {

    public Integer parsePolicy(String policy) {
        return SecurityGroupRuleStatus.getIntegerByStatus(policy);
    }
}
