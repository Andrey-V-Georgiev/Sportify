package com.softuni.sportify.repositories;

import com.softuni.sportify.domain.entities.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, String> {
}
