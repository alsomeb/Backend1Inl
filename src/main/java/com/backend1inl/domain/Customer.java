package com.backend1inl.domain;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer {

    private Long id;

    @NotEmpty(message = "First Name Is Mandatory")
    @Size(min = 3, message = "At least 3 Letters for first name")
    @Pattern(regexp="^[A-Öa-ö]*$", message = "Only Letters for first name")
    private String firstName;

    @NotEmpty(message = "Last name is mandatory.")
    @Size(min = 3, message = "At least 3 Letters for last name")
    @Pattern(regexp="^[A-Öa-ö]*$",message = "Only Letters for first name")
    private String lastName;

    @NotEmpty(message = "Social security number is mandatory.")
    @Size(min = 10, max = 12, message = "Social security number needs to be 10 or 12 digits")
    private String ssn;

}
