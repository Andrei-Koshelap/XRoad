package com.digivikings.cadastre.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class ParcelController {

    @GetMapping("/parcels/{cadastralId}")
    public ResponseEntity<?> getParcel(@PathVariable String cadastralId) {
        // demo response
        return ResponseEntity.ok(Map.of(
                "cadastralId", cadastralId,
                "area", 1200,
                "address", "Tallinn, Example st 1"
        ));
    }

    @GetMapping("/health")
    public ResponseEntity<?> health() {
        return ResponseEntity.ok(Map.of("status", "UP"));
    }
}
