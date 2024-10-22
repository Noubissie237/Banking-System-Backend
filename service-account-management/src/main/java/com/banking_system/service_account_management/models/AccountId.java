package com.banking_system.service_account_management.models;

import java.io.Serializable;

public class AccountId implements Serializable {
    private int agence_id;
    private int user_number;

    public AccountId() {}

    public AccountId(int agence_id, int user_number) {
        this.agence_id = agence_id;
        this.user_number = user_number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AccountId)) return false;
        AccountId that = (AccountId) o;
        return agence_id == that.agence_id && user_number == that.user_number;
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(agence_id, user_number);
    }
}