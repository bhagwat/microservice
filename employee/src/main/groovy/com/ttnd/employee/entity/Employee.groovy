package com.ttnd.employee.entity

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "employees")
public class Employee {
    @Id
    String id;

    String email;
    String fullName;
    String managerEmail;
}