/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bs.security.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
@Entity
@Table(name = "Users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer userid;

    @NotBlank(message = "Imie nie może być puste")
    private String name;

    @NotBlank(message = "Nazwisko nie może być puste")
    private String surname;

    @NotBlank(message = "Login nie może być pusty")
    @Size(min = 3, message = "Wpisz conajmniej 3 znaków")
    private String login;

    @NotBlank(message = "Hasło nie może być puste")
    @Size(min = 5, message = "Wpisz conajmniej 5 znaków")
    private String password;
 public User() {
 }
 public User(String name, String surname, String login,
 String password) {
 this.name = name;
 this.surname = surname;
 this.login = login;
 this.password = password;
 }
 @Override
    public String toString() {
        return "Country{" +
                "ID='" + userid + '\'' +
                ", Name='" + name + '\'' +
                ", Surname='" + surname + '\'' +
                ", Login=" + login +
                ", Password=" + password ;
    }
    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
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

} 