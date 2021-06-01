package com.cognizant.crud.Crud.Operation.dto;

import com.cognizant.crud.Crud.Operation.model.User;

public class AuthenticationResponse {
    private boolean authenticated;
    private User user;

    public boolean isAuthenticated() {
        return authenticated;
    }

    public void setAuthenticated(boolean authenticated) {
        this.authenticated = authenticated;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
