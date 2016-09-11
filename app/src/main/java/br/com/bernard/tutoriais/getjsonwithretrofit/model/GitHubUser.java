package br.com.bernard.tutoriais.getjsonwithretrofit.model;

/**
 * Created by bernard on 11/09/16.
 */
public class GitHubUser {


    String login;
    String name;
    String location;
    String type;


    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    @Override
    public String toString() {
        return "GitHubUser{" +
                "login='" + login + '\'' +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
