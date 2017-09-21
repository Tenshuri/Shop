package shop.beans;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import org.mariadb.jdbc.Driver;

public class SourceMariaDB implements Serializable {

    private String login = "p1205854";
    private String password = "172275";
    private String hostname = "iutdoua-web.univ-lyon1.fr";
    private String port = "3306";
    private String nomDeLaBase = "p1205854";
    private String msg;
    private Connection cnx;

    public SourceMariaDB() {
    }

    public Connection getCnx() {
        String urlJdbc;
        urlJdbc="jdbc:mariadb://"+hostname+":"+port+"/"+nomDeLaBase;
        urlJdbc=urlJdbc+"?user="+login+"&password="+password;
        try{
            Class.forName("org.mariadb.jdbc.Driver");
            cnx=DriverManager.getConnection(urlJdbc);
        }catch(Exception e){
            // blème
        }
        return cnx;
    }

    public String getMessage() {
        return msg;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getNomDeLaBase() {
        return nomDeLaBase;
    }

    public void setNomDeLaBase(String nomDeLaBase) {
        this.nomDeLaBase = nomDeLaBase;
    }


}


