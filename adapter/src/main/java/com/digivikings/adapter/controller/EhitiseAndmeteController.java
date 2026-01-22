package com.digivikings.adapter.controller;


import com.digivikings.adapter.view.BuildingDto;
import com.digivikings.adapter.view.EhitiseAndmeteResponse;
import com.digivikings.adapter.view.SummaryDto;
import jakarta.validation.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Validated
@RestController
@RequestMapping("/api/v1")
public class EhitiseAndmeteController {

    @GetMapping("/ehitis-andmed")
    public EhitiseAndmeteResponse ehitiseAndmeteParing(
            @RequestParam @NotBlank String cadastralId,
            @RequestParam(required = false, defaultValue = "101110010100") String dataVector
    ) {
        String requestId = UUID.randomUUID().toString();

        List<BuildingDto> buildings = List.of(
                new BuildingDto("EH-0001", "RESIDENTIAL", 2, 1998, 180, "Tallinn, Example st 1"),
                new BuildingDto("EH-0002", "GARAGE", 1, 2005, 40, "Tallinn, Example st 1")
        );

        int totalArea = buildings.stream().mapToInt(b -> b.areaM2() == null ? 0 : b.areaM2()).sum();

        SummaryDto summary = new SummaryDto(buildings.size(), totalArea);

        return new EhitiseAndmeteResponse(
                cadastralId,
                dataVector,
                requestId,
                buildings,
                summary
        );
    }
}
