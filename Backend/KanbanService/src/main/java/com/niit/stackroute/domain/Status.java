package com.niit.stackroute.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Status {
    @Id
    private String statusId;
    private String statusName;
    private String spaceName;
    private String email;
    private String statusDescription;

    public Status() {
    }

    public Status(String statusId, String statusName, String spaceName, String email, String statusDescription) {
        this.statusId = statusId;
        this.statusName = statusName;
        this.spaceName = spaceName;
        this.email = email;
        this.statusDescription = statusDescription;
    }

    public String getStatusId() {
        return statusId;
    }

    public void setStatusId(String statusId) {
        this.statusId = statusId;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getSpaceName() {
        return spaceName;
    }

    public void setSpaceName(String spaceName) {
        this.spaceName = spaceName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatusDescription() {
        return statusDescription;
    }

    public void setStatusDescription(String statusDescription) {
        this.statusDescription = statusDescription;
    }

    @Override
    public String toString() {
        return "Status{" +
                "statusId='" + statusId + '\'' +
                ", statusName='" + statusName + '\'' +
                ", spaceName='" + spaceName + '\'' +
                ", email='" + email + '\'' +
                ", statusDescription='" + statusDescription + '\'' +
                '}';
    }
}
