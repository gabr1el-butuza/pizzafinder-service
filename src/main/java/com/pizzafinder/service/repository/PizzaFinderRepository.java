package com.pizzafinder.service.repository;

import com.pizzafinder.service.domain.PizzaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PizzaFinderRepository extends JpaRepository<PizzaEntity, String> {

     List<PizzaEntity> findByNameIgnoreCaseContaining(String name);
     PizzaEntity deletePizzaById(String id);

}
