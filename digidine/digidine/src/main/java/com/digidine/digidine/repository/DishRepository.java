package com.digidine.digidine.repository;

import com.digidine.digidine.model.Dish;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DishRepository extends JpaRepository<Dish, Long> {

    // ek restaurant ke saare dishes
    List<Dish> findByRestaurantId(Long restaurantId);
}
