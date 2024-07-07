package com.app.multas.repo;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.multas.model.Multa;



public interface MultaRepository extends JpaRepository<Multa, Long> {
    @Query(value = "SELECT * FROM Multa WHERE fecha_limite < :actual", nativeQuery = true)
    List<Multa> findExpiredMultas(LocalDateTime actual);
}