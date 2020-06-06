package com.desarrolloweb.spring.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.desarrolloweb.spring.app.entities.Umedida;

@Repository
public interface UmedidaRepository extends JpaRepository<Umedida, Long> {

}
