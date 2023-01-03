package com.money.repository;

import com.money.model.Objetivo;
import com.money.model.Transferencia;
import com.money.model.dto.ObjetivoDTO;
import com.money.model.dto.ObjetivoDetalheDTO;
import com.money.model.dto.ObjetivosFinalizadosDTO;
import com.money.model.dto.ObjetivoStatusDTO;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ObjetivoRepository extends CrudRepository<Objetivo, Long>
{

}
