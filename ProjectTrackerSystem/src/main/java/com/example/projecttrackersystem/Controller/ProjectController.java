package com.example.projecttrackersystem.Controller;

import com.example.projecttrackersystem.Api.ApiResponse;
import com.example.projecttrackersystem.Model.Project;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/project/tracker")
public class ProjectController {

    ArrayList<Project> projects = new ArrayList<>();

    @GetMapping("/get")
    public ArrayList<Project> getProjects(){
        return projects;
    }

    @PostMapping("/add")
    public ApiResponse addProject(@RequestBody Project project){
        projects.add(project);
        return new ApiResponse("Project has been added successfully");
    }

    @PutMapping("/update/{index}")
    public ApiResponse updateProject(@PathVariable int index, @RequestBody Project project){
        projects.set(index,project);
        return new ApiResponse("project has been updated successfully");
    }

    @DeleteMapping("/delete/{index}")
    public ApiResponse deleteProject(@PathVariable int index){
        projects.remove(index);
        return new ApiResponse("Project has been deleted successfully");
    }

    // could make it return response or the modified object itself
    @PutMapping("/change/status/{ID}")
    public ApiResponse changeStatus(@PathVariable String ID){
        for(Project project:projects){
            if(project.getID().equals(ID)){
                project.setStatus("done");
                return new ApiResponse("project status is updated \n"+ project);
            }
        }
        return new ApiResponse("project not found");
    }

    @GetMapping("/get/by/title/{title}")
    public Project getByTitle(@PathVariable String title){
        for(Project project:projects){
            if (project.getTitle().equalsIgnoreCase(title)){
                return project;
            }
        }
        return null;
    }

    @GetMapping("/get/company/projects/{companyName}")
    public ArrayList<Project> projectsByCompany(@PathVariable String companyName){
        ArrayList<Project> companyProjects = new ArrayList<>();
        for (Project project:projects){
            if (project.getCompanyName().equalsIgnoreCase(companyName)){
                companyProjects.add(project);
            }
        }
        return companyProjects;
    }
}
