package models.converters;

import models.DTO.TaxiDTO;
import models.entity.Taxi;
import models.view.TaxiView;

public class TaxiConverter {
    public static Taxi toEntity(TaxiDTO taxiDTO) {
        Taxi taxi = new Taxi();
        taxi.setTaxiID(taxiDTO.getTaxiID());
        taxi.setDriverID(taxiDTO.getDriverID());
        taxi.setCapacity(taxiDTO.getCapacity());
        taxi.setFare(taxiDTO.getFare());
        taxi.setLicensePlate(taxiDTO.getLicensePlate());
        taxi.setCategory(taxiDTO.getCategory());
        return taxi;
    }

    public static TaxiDTO toDTO(Taxi taxi) {
        TaxiDTO taxiDTO = new TaxiDTO();
        taxiDTO.setTaxiID(taxi.getTaxiID());
        taxiDTO.setDriverID(taxi.getDriverID());
        taxiDTO.setCapacity(taxi.getCapacity());
        taxiDTO.setFare(taxi.getFare());
        taxiDTO.setLicensePlate(taxi.getLicensePlate());
        taxiDTO.setCategory(taxi.getCategory());
        return taxiDTO;
    }

    public static TaxiView toView(TaxiDTO taxiDTO) {
        TaxiView taxiView = new TaxiView();
        taxiView.setCapacity(taxiDTO.getCapacity());
        taxiView.setFare(taxiDTO.getFare());
        taxiView.setLicensePlate(taxiDTO.getLicensePlate());
        taxiView.setCategory(taxiDTO.getCategory());
        return taxiView;
    }
}