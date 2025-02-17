package edu.icet.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class Admin {
    private String firstname;
    private String lastname;
    private String contactNo;
    private String email;
    private String username;
    private String password;
}
