package com.ecatom.oauthservice.services;

import com.ecatom.usercommons.model.User;

public interface UserServiceInterface {
    User findByUserName (String string);
}
