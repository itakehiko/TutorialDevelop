package com.techacademy.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "authentication")
public class Authentication {

    @Id
    private String loginUser;

    private String password;

    private Date validDate;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
}
