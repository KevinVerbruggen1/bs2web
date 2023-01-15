package be.ucll.ip.web3.domain.model;

public enum Role {
    TEAMLEADER("teamleader"), EMPLOYEE("employee"), ADMIN("admin"), DIRECTOR("director");

    private String stringValue;

    private Role(String stringValue){
        this.stringValue=stringValue;
    }

    public String getStringValue() {return stringValue;}
}
