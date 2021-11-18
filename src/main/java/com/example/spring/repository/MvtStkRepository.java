package com.example.spring.repository;

 import java.math.BigDecimal;
import java.util.List;

 import com.example.spring.model.MvtStk;
 import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MvtStkRepository extends JpaRepository<MvtStk, Integer> {



}
