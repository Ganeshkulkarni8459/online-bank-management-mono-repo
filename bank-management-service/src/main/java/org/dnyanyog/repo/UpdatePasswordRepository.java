package org.dnyanyog.repo;

import java.util.List;

import org.dnyanyog.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
@Repository
public interface UpdatePasswordRepository extends JpaRepository<Admin,Long> {
	@Query("SELECT e FROM Admin e WHERE e.adminEmail = :adminEmail")
    List<Admin> findByAdminEmail(@Param("adminEmail") String adminEmail);

}
