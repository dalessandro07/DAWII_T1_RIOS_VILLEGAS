package com.cibertec.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cibertec.models.DragonBall;

@Repository
public interface IDragonBallRepository extends CrudRepository<DragonBall, Integer> {
}
