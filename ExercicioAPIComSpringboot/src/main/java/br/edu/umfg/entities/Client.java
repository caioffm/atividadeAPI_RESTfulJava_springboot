package br.edu.umfg.entities;

import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.UUID;

public class Client {
    private UUID clientId;
    private String firstName;
    private String surname;
    private String idNumber;

    public UUID getClientId() {
        return clientId;
    }

    public void setClientId(UUID clientId) {
        this.clientId = clientId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    @JsonCreator
    public Client(String firstName, String surname, String idNumber) {
        this.clientId = UUID.randomUUID();
        this.firstName = firstName;
        this.surname = surname;
        this.idNumber = idNumber;
    }}