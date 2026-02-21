package com.digidine.digidine.controller;

import com.digidine.digidine.model.Restaurant;
import com.digidine.digidine.model.User;
import com.digidine.digidine.repository.RestaurantRepository;
import com.digidine.digidine.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/restaurants")
@CrossOrigin(origins = "https://digidine-phi.vercel.app")
public class RestaurantController {

    private final RestaurantRepository restaurantRepo;
    private final UserRepository userRepo;

    public RestaurantController(RestaurantRepository restaurantRepo, UserRepository userRepo) {
        this.restaurantRepo = restaurantRepo;
        this.userRepo = userRepo;
    }

    @PostMapping
    public ResponseEntity<?> createRestaurant(
            @RequestParam Long ownerId,
            @RequestBody Restaurant restaurant) {

        User owner = userRepo.findById(ownerId)
                .orElseThrow(() -> new RuntimeException("Owner not found"));

        restaurant.setOwner(owner);
        return ResponseEntity.ok(restaurantRepo.save(restaurant));
    }
}
