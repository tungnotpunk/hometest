package com.anymind.hometest.payment.pointsystem.repo;

import com.anymind.hometest.payment.pointsystem.model.db.ChargeRecord;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository("ChargeRecordRepository")
public interface ChargeRecordRepository extends JpaRepository<ChargeRecord, Long> {

}
