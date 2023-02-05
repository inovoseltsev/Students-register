package com.inovoseltsev.studentsregister.controller;

import com.inovoseltsev.studentsregister.entity.Student;
import com.inovoseltsev.studentsregister.repository.StudentRepository;
import com.inovoseltsev.studentsregister.service.PaginationService;
import com.inovoseltsev.studentsregister.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.util.List;
import java.util.Map;

@Controller
public class MainController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private PaginationService paginationService;

    @GetMapping("/")
    public ModelAndView setStudentsData() {
        paginationService.countTablePages();
        List<Student> students = paginationService.getObjectsPage(1);
        ModelMap objects = new ModelMap();
        objects.addAttribute("studentList", students);
        objects.addAttribute("numberOfPages", paginationService.getNumberOfTablePages());
        objects.addAttribute("navigationPage", 1);
        return new ModelAndView("index").addAllObjects(objects);
    }

    @PostMapping("/redact")
    public String processForm(@RequestParam Map<String, String> studentParameters) {
        if ("".equals(studentParameters.get("id"))) {
            return "forward:/add";
        } else {
            return "forward:/edit";
        }
    }

    @PostMapping("/add")
    public String addStudent(@RequestParam Map<String, String> formParameters) {
        Student student = new Student();
        student.setFirstName(formParameters.get("firstName"));
        student.setLastName(formParameters.get("lastName"));
        student.setBirthday(Date.valueOf(formParameters.get("birthday")));
        student.setFaculty(formParameters.get("faculty"));
        studentService.create(student);
        paginationService.countTablePages();
        int pageNumber = Integer.parseInt(formParameters.get("pageNumber"));
        return "redirect:/page?number=" + pageNumber;
    }

    @PostMapping("/edit")
    public String editStudent(@RequestParam Map<String, String> formParameters) {
        Long studentId = Long.parseLong(formParameters.get("id"));
        Student currentStudent = studentRepository.findById(studentId);
        currentStudent.setFirstName(formParameters.get("firstName"));
        currentStudent.setLastName(formParameters.get("lastName"));
        currentStudent.setBirthday(Date.valueOf(formParameters.get("birthday")));
        currentStudent.setFaculty(formParameters.get("faculty"));
        studentService.update(currentStudent);
        int pageNumber = Integer.parseInt(formParameters.get("pageNumber"));
        return "redirect:/page?number=" + pageNumber;
    }

    @GetMapping("/delete")
    public String deleteStudent(HttpServletRequest request) {
        Long id = Long.parseLong(request.getParameter("id"));
        int pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
        Student currentStudent = studentRepository.findById(id);
        studentService.remove(currentStudent);
        paginationService.countTablePages();
        int numberOfTablePages = paginationService.getNumberOfTablePages();
        if (pageNumber > numberOfTablePages) {
            pageNumber = numberOfTablePages;
        }
        return "redirect:/page?number=" + pageNumber;
    }

    @GetMapping("/page")
    public ModelAndView setStudentsForPage(HttpServletRequest request) {
        int pageNumber = Integer.parseInt(request.getParameter("number"));
        if (pageNumber == 0) {
            pageNumber = 1;
        }
        List<Student> currentStudents = paginationService.getObjectsPage(pageNumber);
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("studentList", currentStudents);
        modelMap.addAttribute("numberOfPages", paginationService.getNumberOfTablePages());
        modelMap.addAttribute("navigationPage", pageNumber);
        return new ModelAndView("index").addAllObjects(modelMap);
    }
}
