package com.digivikings.cadastre.view;

public record BuildingDto(
        String buildingId,
        String usageType,
        Integer floors,
        Integer yearBuilt,
        Integer areaM2,
        String address
) {}