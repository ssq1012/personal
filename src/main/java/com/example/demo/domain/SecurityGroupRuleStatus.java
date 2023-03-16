package com.example.demo.domain;

/**
 * @author ssq
 * @create on 2022/08/31
 */
public enum SecurityGroupRuleStatus {

    /**
     * 接受访问
     */
    ACCEPT("Accept", 1),
    /**
     * 拒绝访问
     */
    DROP("Drop", 0);

    private String status;
    private Integer integer;

    SecurityGroupRuleStatus(String status, Integer integer) {
        this.integer = integer;
        this.status = status;
    }

    /**
     * 根据integer获取状态
     * @param integer 代表状态的整形数字
     * @return 状态
     */
    public static String getStatusByInteger(Integer integer) {
        for (SecurityGroupRuleStatus securityGroupRuleStatus : SecurityGroupRuleStatus.values()) {
            if (securityGroupRuleStatus.getInteger().equals(integer)) {
                return securityGroupRuleStatus.getStatus();
            }
        }
        return null;
    }

    /**
     * 根据状态获取对应的整型数字
     * @param status 状态
     * @return 状态
     */
    public static Integer getIntegerByStatus(String status) {
        for (SecurityGroupRuleStatus securityGroupRuleStatus : SecurityGroupRuleStatus.values()) {
            if (securityGroupRuleStatus.getStatus().equalsIgnoreCase(status)) {
                return securityGroupRuleStatus.getInteger();
            }
        }
        return 0;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getInteger() {
        return integer;
    }

    public void setInteger(Integer integer) {
        this.integer = integer;
    }

    @Override
    public String toString() {
        return getInteger().toString();
    }

}
