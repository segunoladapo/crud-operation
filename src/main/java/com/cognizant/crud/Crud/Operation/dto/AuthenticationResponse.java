package com.cognizant.crud.Crud.Operation.dto;

import com.cognizant.crud.Crud.Operation.model.User;


public class AuthenticationResponse {
    private boolean authenticated;
    private UserDto user;

    public boolean isAuthenticated() {
        return authenticated;
    }

    public void setAuthenticated(boolean authenticated) {
        this.authenticated = authenticated;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }
}
