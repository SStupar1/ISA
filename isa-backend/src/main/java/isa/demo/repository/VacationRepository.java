package isa.demo.repository;

import isa.demo.model.Vacation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface VacationRepository extends JpaRepository<Vacation,Long> {


    Vacation findOneById(Long id);

    @Query("SELECT v FROM Vacation v where v.status='PENDING'")
    List<Vacation> findPending();

    @Query("SELECT v FROM Vacation v where v.status=?1")
    List<Vacation> findByStatus(String status);

    @Modifying
    @Transactional
    @Query("update Vacation v set v.status = :status where v.id = :id")
    int updateVacationStatus(@Param("status") String status, @Param("id") Long id);
}
