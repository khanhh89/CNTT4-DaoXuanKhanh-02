package org.example.hackathon.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class Employee {
    private String id;

    @NotBlank(message = "Tên không được để trống")
    private String fullName;

    @NotBlank(message = "Vị trí không được để trống")
    private String position;

    @NotNull(message = "Lương không được để trống")
    @Min(value = 100, message = "Lương phải >= 100")
    private Double salary;

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public String getPosition() { return position; }
    public void setPosition(String position) { this.position = position; }
    public Double getSalary() { return salary; }
    public void setSalary(Double salary) { this.salary = salary; }
}