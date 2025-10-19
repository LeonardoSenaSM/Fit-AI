package dev.leonardosena.fitai.FitAI.repository;

import dev.leonardosena.fitai.FitAI.model.FoodItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodItemRepository extends JpaRepository<FoodItem, Long> {
}