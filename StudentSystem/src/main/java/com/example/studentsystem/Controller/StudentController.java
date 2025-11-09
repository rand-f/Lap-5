package com.example.studentsystem.Controller;


import com.example.studentsystem.Api.ApiResponse;
import com.example.studentsystem.Model.Student;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/student")
public class StudentController {
    ArrayList<Student> students =new ArrayList<>();

    @GetMapping("/get")
    public ArrayList<Student> getStudents(){
        return students;
    }

    @PostMapping("/add")
    public ApiResponse addStudent(@RequestBody Student student){
        students.add(student);
        return new ApiResponse("Student added successfully");
    }

    @PutMapping("/update/{index}")
    public ApiResponse updateStudent(@PathVariable int index, @RequestBody Student student){
        students.set(index, student);
        return new ApiResponse("Student has been updated successfully");
    }

    @DeleteMapping("/delete/{index}")
    public ApiResponse deleteStudent(@PathVariable int index){
        students.remove(index);
        return new ApiResponse("Student has been removed successfully");
    }

    @GetMapping("/get/honor/class/{ID}")
    public ApiResponse CalculateHonorClass(@PathVariable String ID){
        for(Student student:students){
            if (student.getID().equals(ID)){
                if(student.getGPA()>=4.75){
                    return new ApiResponse("first class honor");
                }
                else if(student.getGPA()>=4.25){
                    return new ApiResponse("Second class honor");
                }
            }
        }
        return new ApiResponse("student is not a class honor");
    }

    @GetMapping("/get/above/average/students")
    public ArrayList<Student> aboveAverage(){
        ArrayList<Student> aboveAverageStudents = new ArrayList<>();
        double sum =0;
        for(Student student:students){
            sum+=student.getGPA();
        }
        double average = sum/students.size();

        for(Student student: students){
            if (student.getGPA()>average){
                aboveAverageStudents.add(student);
            }
        }
        return aboveAverageStudents;
    }

}
