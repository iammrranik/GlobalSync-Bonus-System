package com.globalsync.bonus.system.api;

import com.globalsync.bonus.system.domain.PerformanceReview;
import com.globalsync.bonus.system.service.IPerformanceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/performance-reviews")
public class PerformanceApi {

    private final IPerformanceService performanceService;

    public PerformanceApi(IPerformanceService performanceService) {
        this.performanceService = performanceService;
    }

    @PostMapping
    public ResponseEntity<String> createReview(@RequestBody PerformanceReview review) {
        int result = performanceService.addPerformanceReview(review);
        if (result > 0) {
            return new ResponseEntity<>("Performance review saved successfully", HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Failed to save performance review", HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PerformanceReview> getReviewById(@PathVariable Integer id) {
        return performanceService.getPerformanceReviewById(id)
                .map(review -> new ResponseEntity<>(review, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<PerformanceReview>> getAllReviews(
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size) {
        if (page != null && size != null) {
            return new ResponseEntity<>(performanceService.getAllPerformanceReviews(page, size), HttpStatus.OK);
        }
        return new ResponseEntity<>(performanceService.getAllPerformanceReviews(), HttpStatus.OK);
    }

    @GetMapping("/count")
    public ResponseEntity<Integer> getReviewCount() {
        return new ResponseEntity<>(performanceService.getPerformanceReviewCount(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateReview(@PathVariable int id, @RequestBody PerformanceReview review) {
        review.setId(id);
        int result = performanceService.updatePerformanceReview(review);
        if (result > 0) {
            return new ResponseEntity<>("Performance review updated successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Failed to update performance review", HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteReview(@PathVariable int id) {
        int result = performanceService.removePerformanceReview(id);
        if (result > 0) {
            return new ResponseEntity<>("Performance review deleted successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Performance review not found or failed to delete", HttpStatus.NOT_FOUND);
    }
}
