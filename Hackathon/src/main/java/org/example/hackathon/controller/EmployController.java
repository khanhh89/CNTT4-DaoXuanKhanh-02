package org.example.hackathon.controller;

import jakarta.validation.Valid;
import org.example.hackathon.model.Employee;
import org.example.hackathon.service.EmployService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@Controller
@RequestMapping("/employees")
public class EmployController {
    @Autowired
    private EmployService service;
    @GetMapping({"", "/"})
    public String list(@RequestParam(name = "keyword", required = false) String keyword,
                       Model model) {
        List<Employee> list;
        if (keyword != null && !keyword.isEmpty()) {
            list = service.search(keyword);
        } else {
            list = service.findAll();
        }

        model.addAttribute("employees", list);
        return "list";
    }
    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("employee", new Employee());
        return "form";
    }
    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("employee") Employee e,
                       BindingResult result,
                       Model model) {
        if (result.hasErrors()) {
            return "form";
        }
        if (e.getId() == null || e.getId().isEmpty()) {
            service.add(e);
        } else {
            service.update(e);
        }
        return "redirect:/employees/";
    }
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, Model model) {

        Employee e = service.findById(id);

        if (e == null) {
            return "redirect:/employees/";
        }
        model.addAttribute("employee", e);
        return "form";
    }
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") String id) {

        if (id != null && !id.isEmpty()) {
            service.delete(id);
        }
        return "redirect:/employees/";
    }
}