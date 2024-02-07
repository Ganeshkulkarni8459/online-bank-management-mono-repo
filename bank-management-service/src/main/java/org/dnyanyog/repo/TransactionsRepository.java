package org.dnyanyog.repo;

import org.dnyanyog.entity.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
@Repository
public interface TransactionsRepository extends CrudRepository<Transactions,String> {

}
