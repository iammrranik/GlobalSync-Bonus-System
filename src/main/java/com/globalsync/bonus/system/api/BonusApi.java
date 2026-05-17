package com.globalsync.bonus.system.api;

import com.globalsync.bonus.system.domain.BonusRecord;
import com.globalsync.bonus.system.domain.PerformanceReview;
import com.globalsync.bonus.system.service.IBonusService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bonus-records")
public class BonusApi {

    private final IBonusService bonusService;

    public BonusApi(IBonusService bonusService) {
        this.bonusService = bonusService;
    }

    @PostMapping("/calculate")
    public ResponseEntity<?> calculateBonus(@RequestBody PerformanceReview review) {
        try {
            BonusRecord result = bonusService.calculateAndSaveBonus(review);
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<BonusRecord> getBonusRecordById(@PathVariable Integer id) {
        return bonusService.getBonusRecordById(id)
                .map(record -> new ResponseEntity<>(record, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<BonusRecord>> getAllBonusRecords(
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size) {
        if (page != null && size != null) {
            return new ResponseEntity<>(bonusService.getAllBonusRecords(page, size), HttpStatus.OK);
        }
        return new ResponseEntity<>(bonusService.getAllBonusRecords(), HttpStatus.OK);
    }

    @GetMapping("/count")
    public ResponseEntity<Integer> getBonusRecordCount() {
        return new ResponseEntity<>(bonusService.getBonusRecordCount(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateBonusRecord(@PathVariable int id, @RequestBody BonusRecord record) {
        record.setId(id);
        int result = bonusService.updateBonusRecord(record);
        if (result > 0) {
            return new ResponseEntity<>("Bonus record updated successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Failed to update bonus record", HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBonusRecord(@PathVariable int id) {
        int result = bonusService.removeBonusRecord(id);
        if (result > 0) {
            return new ResponseEntity<>("Bonus record deleted successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Bonus record not found or failed to delete", HttpStatus.NOT_FOUND);
    }
}