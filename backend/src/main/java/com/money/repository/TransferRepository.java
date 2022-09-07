package com.money.repository;

import com.money.model.Transfer;
import com.money.model.User;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransferRepository extends JpaRepository<Transfer, Long>
{
	Page<Transfer> findTransferByUser(Optional<User> user, Pageable pageable);
}
