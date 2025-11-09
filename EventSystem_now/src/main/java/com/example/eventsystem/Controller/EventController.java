package com.example.eventsystem.Controller;

import com.example.eventsystem.Api.ApiResponse;
import com.example.eventsystem.Model.Capacity;
import com.example.eventsystem.Model.Event;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/event")
public class EventController {
    ArrayList<Event> events = new ArrayList<>();

    @GetMapping("/get")
    public ArrayList<Event> getEvents(){
        return events;
    }

    @PostMapping("/add")
    public ApiResponse addEvent(@RequestBody Event event){
        event.setStartDate(LocalDateTime.now());
        events.add(event);
        return new ApiResponse("Event added successfully");
    }

    @PutMapping("/update/{index}")
    public ApiResponse updateEvent(@PathVariable int index,@RequestBody Event event){
        events.set(index,event);
        return new ApiResponse("Event updated successfully \n"+events.get(index));
    }

    @DeleteMapping("/delete/{index}")
    public  ApiResponse deleteEvent(@PathVariable int index){
        events.remove(index);
        return new ApiResponse("Event has been removed successfully");
    }

    @PutMapping("/change/capacity/{ID}")
    public ApiResponse changeCapacity(@PathVariable String ID, @RequestBody Capacity capacity){
        for (Event event: events){
            if (event.getID().equals(ID)){
                event.setCapacity(capacity.getCapacity());
            }
        }
        return new ApiResponse("Capacity has been updated");
    }

    @GetMapping("/get/by/id/{ID}")
    public Event getByID(@PathVariable String ID){
        for (Event event:events){
            if(event.getID().equals(ID)){
                return event;
            }
        }
        return null;
    }
}
