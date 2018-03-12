package com.igt.platform.resortwallet.report.model;

import java.util.Date;

public class ReportRequest  {
    
    private String empoloyeeName;

    private String loginName;

    private Date startDateTime;
    
    private Date endDateTime;

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

    public Date getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(Date startDateTime) {
        this.startDateTime = startDateTime;
    }

    public Date getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(Date endDateTime) {
        this.endDateTime = endDateTime;
    }

}
