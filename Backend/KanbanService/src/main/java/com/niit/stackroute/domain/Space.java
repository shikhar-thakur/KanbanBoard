package com.niit.stackroute.domain;



import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document public class Space {

    @Id
    private String spaceID;
    private String spaceName;
    private String email;

    public Space() {
    }

    public Space(String spaceID, String spaceName, String email) {
        this.spaceID = spaceID;
        this.spaceName = spaceName;
        this.email = email;
    }

    public String getSpaceID() {
        return spaceID;
    }

    public void setSpaceID(String spaceID) {
        this.spaceID = spaceID;
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

    @Override
    public String toString() {
        return "Space{" +
                "spaceID='" + spaceID + '\'' +
                ", spaceName='" + spaceName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
