package com.uni.thesissystem.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity

public class User extends IdGenerator{
    private String username;
    private String password;
    private String role;
}

