package com.example.transportexchangeserver.controller;

import com.example.transportexchangeserver.dto.MessageResponse;
import com.example.transportexchangeserver.dto.pojo.CarOfferDTO;
import com.example.transportexchangeserver.dto.pojo.CargoOfferDTO;
import com.example.transportexchangeserver.service.OfferService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/cargo_offers")
@CrossOrigin
@AllArgsConstructor
public class CargoOfferController {
    private final OfferService offerService;

    @GetMapping()
    public List<CargoOfferDTO> getAllCargoOffers(){

        List<CargoOfferDTO> list = offerService.getAllCargoOffers();
        return list;
    }

    @GetMapping("/{id}")
    public CargoOfferDTO getCargoOffer(@PathVariable long id){
        CargoOfferDTO cargoOfferDTO = offerService.getCargoOfferById(id);
        return cargoOfferDTO;
    }

    @PostMapping
    public ResponseEntity<?> addCargoOffer(@RequestBody @Valid CargoOfferDTO cargoOffer, HttpServletRequest request) {
        if(offerService.addCargoOffer(cargoOffer, request))
            return ResponseEntity.ok(new MessageResponse("CargoOffer Created"));
        else
            return ResponseEntity.badRequest().body(new MessageResponse("User doesn't have company"));
    }

    @PutMapping
    public ResponseEntity<?> updateCargoOffer(@RequestBody @Valid CargoOfferDTO cargoOffer, HttpServletRequest request){
        if(offerService.updateCargoOffer(cargoOffer, request))
            return ResponseEntity.ok(new MessageResponse("CargoOffer Updated"));
        else
            return ResponseEntity.status(403).body(new MessageResponse("Not your company"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCargoOffer(@PathVariable long id, HttpServletRequest request){
        if(offerService.deleteCargoOfferById(id, request))
            return ResponseEntity.ok(new MessageResponse("CargoOffer Deleted"));
        else
            return ResponseEntity.status(403).body(new MessageResponse("Not your company"));
    }
}
