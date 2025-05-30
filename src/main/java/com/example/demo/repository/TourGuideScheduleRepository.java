package com.example.demo.repository;

import com.example.demo.entity.TourGuide;
import com.example.demo.entity.TourGuideSchedule;
import com.example.demo.entity.TourSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TourGuideScheduleRepository extends JpaRepository<TourGuideSchedule, Long> {
    List<TourSchedule> findByTourGuide(TourGuide tourGuide);

    // Option 2: Using @Query for explicitness
    @Query("SELECT tgs.tourSchedule FROM TourGuideSchedule tgs WHERE tgs.tourGuide = :tourGuide")
    List<TourSchedule> findSchedulesByTourGuideJPQL(@Param("tourGuide") TourGuide tourGuide);

    // Alternative: if you only want to pass the ID
    @Query("SELECT tgs.tourSchedule FROM TourGuideSchedule tgs WHERE tgs.tourGuide.id = :tourGuideId")
    List<TourSchedule> findSchedulesByTourGuideIdJPQL(@Param("tourGuideId") Long tourGuideId);
}
