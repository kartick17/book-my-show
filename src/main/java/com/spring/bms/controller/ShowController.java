package com.spring.bms.controller;

import com.spring.bms.dto.ShowRequest;
import com.spring.bms.entity.Show;
import com.spring.bms.service.ShowService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/shows")
@RequiredArgsConstructor
public class ShowController {

    private final ShowService showService;

    @PostMapping
    public ResponseEntity<Show> addShow(ShowRequest req)
    {
        return ResponseEntity.ok(showService.addShow(req));
    }

    @GetMapping
    public ResponseEntity<List<Show>> getAllShow()
    {
        return ResponseEntity.ok(showService.getAllShow());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Show> getShowById(@PathVariable Long id)
    {
        return ResponseEntity.ok(showService.getShowById(id));
    }

    @GetMapping("/screen/{screenId}")
    public ResponseEntity<List<Show>> getShowByScreenId(@PathVariable Long screenId)
    {
        return ResponseEntity.ok((showService.getShowByScreen(screenId)));
    }

    @GetMapping("/movie/{movieId}")
    public ResponseEntity<List<Show>> getShowByMovieId(@PathVariable Long movieId)
    {
        return ResponseEntity.ok(showService.getShowByMovie(movieId));
    }

    @GetMapping("/movie/{movieId}/date")
    public ResponseEntity<List<Show>>
        getShowByMovieAndDate(@PathVariable Long movieId, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date)
    {
        return ResponseEntity.ok(showService.getShowByMovieAndDate(movieId, date));
    }
}
