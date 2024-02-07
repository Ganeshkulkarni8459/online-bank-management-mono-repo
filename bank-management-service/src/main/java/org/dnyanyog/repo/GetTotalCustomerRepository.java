package org.dnyanyog.repo;


import org.dnyanyog.entity.CustomerResgistration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
@Repository
public interface GetTotalCustomerRepository extends JpaRepository<CustomerResgistration,String> {

}
