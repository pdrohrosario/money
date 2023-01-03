package com.money.repository;

import com.money.model.Poupanca;
import com.money.model.TipoGasto;
import com.money.model.dto.PoupancaDTO;
import com.money.model.dto.PoupancaDetalheDTO;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PoupancaRepository extends CrudRepository<Poupanca, Long>
{

}
