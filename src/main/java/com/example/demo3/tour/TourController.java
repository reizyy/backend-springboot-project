package com.example.demo3.tour;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;






@RestController
@RequestMapping("/tours")
public class TourController {
//CRUD Tour 
    
    private final Map<Integer, Tour> tourInMemDB;
    private static final AtomicInteger ATOMIC_INTEGER = new AtomicInteger(1);

    public TourController(){
        tourInMemDB = new HashMap<>();

    }
    

    //Get All
    @GetMapping
    public List<Tour> getAllTour(){
        return tourInMemDB.entrySet().stream().map(e -> e.getValue()).toList();
    }
    //Get tour by id
    @GetMapping("/{id}")
    public Tour getTourById(@PathVariable int id) { 
        return Optional.ofNullable(tourInMemDB.get(id))
        .orElseThrow(() -> new RuntimeException("Not Found"));
    }
    
    //create tour
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Tour createTour(@RequestBody Tour tour) {
        var newTour = new Tour(ATOMIC_INTEGER.getAndIncrement(),tour.title(),tour.maxPeople()); 
        var id = newTour.id();
        tourInMemDB.put(id, newTour);
        return tourInMemDB.get(id);
    }

    //update tour
    @PutMapping
    public Tour updateTour(@RequestBody Tour tour) {
        var updatedTour = new Tour(tour.id(),tour.title(),tour.maxPeople());
        var id = updatedTour.id();
        tourInMemDB.put(id, updatedTour);
        return tourInMemDB.get(id);
    }

    @DeleteMapping("/{id}")
    public String deleteTour(@PathVariable int id){
        if(!tourInMemDB.containsKey(id)){
            return "failed";
        }
        tourInMemDB.remove(id);
        return "success " + id;
    }
    
    

}
