package org.example.hackathon.service;

import org.example.hackathon.model.Employee;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployService {

    private final List<Employee> list = new ArrayList<>();
    private long nextId = 1;
    public List<Employee> findAll() {
        return list;
    }

    public void add(Employee employee) {
        employee.setId(String.valueOf(nextId++));
        list.add(employee);
    }

    public void delete(String id) {
        list.removeIf(e -> e.getId().equals(id));
    }

    public Employee findById(String id) {
        if (id == null) return null;

        return list.stream()
                .filter(e -> e.getId() != null && e.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public void update(Employee employee) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId().equals(employee.getId())) {
                list.set(i, employee);
                return;
            }
        }
    }

    public List<Employee> search(String keyword) {
        if (keyword == null || keyword.isBlank()) return list;
        String lowKey = keyword.toLowerCase();
        return list.stream()
                .filter(e -> (e.getFullName() != null && e.getFullName().toLowerCase().contains(lowKey)) ||
                        (e.getPosition() != null && e.getPosition().toLowerCase().contains(lowKey)))
                .toList();
    }
}