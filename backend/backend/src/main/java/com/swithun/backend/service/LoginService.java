package com.swithun.backend.service;

import com.swithun.backend.dao.UserRepository;
import com.swithun.backend.entity.UserEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    @Autowired
    private UserRepository uRepository;

    public UserEntity checkUser(int id) {
        return uRepository.findById(id);
    }
}
