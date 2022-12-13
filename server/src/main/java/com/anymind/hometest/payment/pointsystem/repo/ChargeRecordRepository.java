package com.anymind.hometest.payment.pointsystem.repo;

import java.util.List;
import java.util.Date;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.anymind.hometest.payment.pointsystem.model.db.*;
import com.anymind.hometest.payment.pointsystem.model.output.*;

@Repository("ChargeRecordRepository")
public interface ChargeRecordRepository extends JpaRepository<ChargeRecord, Long> {

  @Query(nativeQuery = true, value=
    "SELECT " + 
    "datetime_in_hour AS `datetime`, " +
    "SUM(final_price) AS sales, " + 
    "SUM(points) AS points " +
    "FROM anymind.charge_record " +
    "WHERE `datetime` BETWEEN :startDateTime AND :endDateTime " +
    "GROUP BY datetime_in_hour " +
    "ORDER BY datetime_in_hour ASC"
  )
  List<ChargeInHourRecordInterface> findGroupByDatetimeInHour(@Param("startDateTime") Date startDateTime, @Param("endDateTime") Date endDateTime);

}
