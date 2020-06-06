package com.desarrolloweb.spring.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.desarrolloweb.spring.app.entities.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}
