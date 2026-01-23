package com.digivikings.cadastre.view;

import java.util.List;

public record EhitiseAndmeteResponse(
        String cadastralId,
        String dataVector,
        String requestId,
        List<BuildingDto> buildings,
        SummaryDto summary
) {}