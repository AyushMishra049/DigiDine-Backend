package com.digidine.digidine.controller;

import com.digidine.digidine.model.Dish;
import com.digidine.digidine.model.Restaurant;
import com.digidine.digidine.repository.DishRepository;
import com.digidine.digidine.repository.RestaurantRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5173")
public class DishController {

    private final DishRepository dishRepo;
    private final RestaurantRepository restaurantRepo;

    public DishController(DishRepository dishRepo, RestaurantRepository restaurantRepo) {
        this.dishRepo = dishRepo;
        this.restaurantRepo = restaurantRepo;
    }

    // 👉 GET /api/restaurants/{restaurantId}/dishes
    @GetMapping("/restaurants/{restaurantId}/dishes")
    public ResponseEntity<List<Dish>> getDishesByRestaurant(@PathVariable Long restaurantId) {
        List<Dish> dishes = dishRepo.findByRestaurantId(restaurantId);
        return ResponseEntity.ok(dishes);
    }

    // 👉 POST /api/restaurants/{restaurantId}/dishes
    @PostMapping("/restaurants/{restaurantId}/dishes")
    public ResponseEntity<?> addDishToRestaurant(
            @PathVariable Long restaurantId,
            @RequestBody Dish dishRequest
    ) {
        // restaurant exist karta hai ya nahi check
        Restaurant restaurant = restaurantRepo.findById(restaurantId).orElse(null);
        if (restaurant == null) {
            return ResponseEntity.badRequest().body("Restaurant not found");
        }

        Dish dish = new Dish();
        dish.setName(dishRequest.getName());
        dish.setCategory(dishRequest.getCategory());
        dish.setDescription(dishRequest.getDescription());
        dish.setPrice(dishRequest.getPrice());
        dish.setType(dishRequest.getType());
        dish.setImageUrl(dishRequest.getImageUrl());
        dish.setRestaurant(restaurant);

        Dish saved = dishRepo.save(dish);

        // ✅ Simple, safe response – restaurant object ke bina
        return ResponseEntity.ok(new DishResponse(
                saved.getId(),
                saved.getName(),
                saved.getCategory(),
                saved.getDescription(),
                saved.getPrice(),
                saved.getType(),
                saved.getImageUrl()
        ));
    }

    // ✅ Response DTO (infinite loop / heavy JSON se bachne ke liye)
    public static class DishResponse {
        public Long id;
        public String name;
        public String category;
        public String description;
        public Double price;
        public String type;
        public String imageUrl;

        public DishResponse(Long id, String name, String category, String description,
                            Double price, String type, String imageUrl) {
            this.id = id;
            this.name = name;
            this.category = category;
            this.description = description;
            this.price = price;
            this.type = type;
            this.imageUrl = imageUrl;
        }
    }
}
