package com.pizzafinder.service.repository;

import com.pizzafinder.service.domain.LocationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface LocationRepository extends JpaRepository<LocationEntity, String> {

     LocationEntity findByGoogleId(String googleId);

}
