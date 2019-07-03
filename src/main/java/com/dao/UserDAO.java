package com.dao;

import com.entities.User;

import javax.servlet.http.HttpSession;

public interface UserDAO {
    public User userInfo(String id);
    public void purchasePayment (HttpSession session);
}
