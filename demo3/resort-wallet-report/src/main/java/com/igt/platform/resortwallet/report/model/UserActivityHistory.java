package com.igt.platform.resortwallet.report.model;

import java.util.Date;

public class UserActivityHistory {
    private String accountName;
    
    private int failedLoginCount;
    
    private String activityType;
    
    private String activityMessage;
    
    private Date occurredDateTime;
    
    private String details;

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public int getFailedLoginCount() {
        return failedLoginCount;
    }

    public void setFailedLoginCount(int failedLoginCount) {
        this.failedLoginCount = failedLoginCount;
    }

    public String getActivityType() {
        return activityType;
    }

    public void setActivityType(String activityType) {
        this.activityType = activityType;
    }

    public String getActivityMessage() {
        return activityMessage;
    }

    public void setActivityMessage(String activityMessage) {
        this.activityMessage = activityMessage;
    }

    public Date getOccurredDateTime() {
        return occurredDateTime;
    }

    public void setOccurredDateTime(Date occurredDateTime) {
        this.occurredDateTime = occurredDateTime;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
    
}
