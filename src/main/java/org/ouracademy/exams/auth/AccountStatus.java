package org.ouracademy.exams.auth;

public enum AccountStatus {

    ASIGNED("Asignado"), OWNER("Apropiado"); 

    private String name;
 
    AccountStatus(String name) {
        this.name = name;
    }
 
    public String getName() {
        return this.name;
    }
    
}
