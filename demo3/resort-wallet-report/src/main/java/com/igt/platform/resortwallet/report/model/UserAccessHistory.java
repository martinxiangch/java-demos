package com.igt.platform.resortwallet.report.model;

import java.util.Date;

public class UserAccessHistory {
    private String empoloyeeName;

    private String loginName;

    private String descirption;

    private Date createAccountDateTime;

    private Date lastLoginDateTime;

    private Date lastPWDChangedDateTime;
    
    private Date disableAccountDateTime;
    
    public String getEmpoloyeeName() {
        return empoloyeeName;
    }

    public void setEmpoloyeeName(String empoloyeeName) {
        this.empoloyeeName = empoloyeeName;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getDescirption() {
        return descirption;
    }

    public void setDescirption(String descirption) {
        this.descirption = descirption;
    }

    public Date getCreateAccountDateTime() {
        return createAccountDateTime;
    }

    public void setCreateAccountDateTime(Date createAccountDateTime) {
        this.createAccountDateTime = createAccountDateTime;
    }

    public Date getLastLoginDateTime() {
        return lastLoginDateTime;
    }

    public void setLastLoginDateTime(Date lastLoginDateTime) {
        this.lastLoginDateTime = lastLoginDateTime;
    }

    public Date getLastPWDChangedDateTime() {
        return lastPWDChangedDateTime;
    }

    public void setLastPWDChangedDateTime(Date lastPWDChangedDateTime) {
        this.lastPWDChangedDateTime = lastPWDChangedDateTime;
    }

    public Date getDisableAccountDateTime() {
        return disableAccountDateTime;
    }

    public void setDisableAccountDateTime(Date disableAccountDateTime) {
        this.disableAccountDateTime = disableAccountDateTime;
    }

}
