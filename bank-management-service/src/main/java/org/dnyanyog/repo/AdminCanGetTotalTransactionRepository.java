package org.dnyanyog.repo;

import java.util.List;

import org.dnyanyog.entity.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
@Repository
public interface AdminCanGetTotalTransactionRepository extends JpaRepository<Transactions,Long> {


}
