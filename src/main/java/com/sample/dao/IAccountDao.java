package com.sample.dao;

import com.sample.domin.AccountUser;

import java.util.List;

public interface IAccountDao {
    List<AccountUser> findAll();
}
