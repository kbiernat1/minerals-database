package com.crud.minerals.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue
    private long id;
    private String login;
    private String password;
    private String email;

    private User(final long id, final String login, final String email) {
        this.id = id;
        this.login = login;
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User userModel = (User) o;

        return Objects.equals(id, userModel.id) &&
                Objects.equals(login, userModel.login) &&
                Objects.equals(password, userModel.password) &&
                Objects.equals(email, userModel.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, password, email);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
