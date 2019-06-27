package com.dao;

import com.daoimpl.DBConnectorImpl;

import java.sql.Connection;

public interface DBConnector {

    public DBConnectorImpl getInstance();

    public Connection getConnection();

    public void Disconnect();
}
