package com.ppi.trackventory.services;

import com.ppi.trackventory.models.User;

public interface UserService {

    public User saveUser(User usuario) throws Exception;

    public User getUser(String username);

    public void deleteUser(Long usuarioId);
}
