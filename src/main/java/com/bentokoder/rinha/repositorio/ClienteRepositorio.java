package com.bentokoder.rinha.repositorio;

import com.bentokoder.rinha.entidade.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepositorio extends JpaRepository<Cliente, Integer> {
}
