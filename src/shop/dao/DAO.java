package shop.dao;

import shop.beans.SourceMariaDB;

import java.sql.Connection;

abstract class DAO {

    private Connection connection;

    Connection getConnection() {
        if (this.connection == null) {
            this.connection = new SourceMariaDB().getCnx();
        }
        return connection;
    }
}
