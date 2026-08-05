package com.spring.bms.repository;

import com.spring.bms.entity.Show;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ShowRepository extends JpaRepository<Show, Long> {
    List<Show> findByMovieId(Integer movieId);
    List<Show> findByScreenId(Integer screenId);
    List<Show> findByMovieIdAndShowDate(Integer movieId, LocalDate showDate);
    List<Show> findByScreenIdAndShowDate(Integer screenId, LocalDate showDate);
}
