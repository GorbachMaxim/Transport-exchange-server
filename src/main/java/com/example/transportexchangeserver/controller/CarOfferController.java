package com.example.transportexchangeserver.controller;

import com.example.transportexchangeserver.dto.City;
import com.example.transportexchangeserver.dto.MessageResponse;
import com.example.transportexchangeserver.dto.pojo.CarOfferDTO;
import com.example.transportexchangeserver.service.OfferService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/car_offers")
@CrossOrigin
@AllArgsConstructor
public class CarOfferController {

    private final OfferService offerService;


    @GetMapping()
    public @ResponseBody List<CarOfferDTO> getAllCarOffers(){

        List<CarOfferDTO> list = offerService.getAllCarOffers();
        return list;
    }

    @GetMapping("/{id}")
    public @ResponseBody CarOfferDTO getCarOffer(@PathVariable long id){
        CarOfferDTO carOfferDTO = offerService.getCarOfferById(id);
        return carOfferDTO;
    }

    @PostMapping
    public ResponseEntity<?> addCarOffer(@RequestBody @Valid CarOfferDTO carOffer, HttpServletRequest request) {
        if(offerService.addCarOffer(carOffer, request))
            return ResponseEntity.ok(new MessageResponse("CarOffer Created"));
        else
            return ResponseEntity.badRequest().body(new MessageResponse("User doesn't have company"));
    }

    @PutMapping
    public ResponseEntity<?> updateCarOffer(@RequestBody @Valid CarOfferDTO carOffer, HttpServletRequest request){
        if(offerService.updateCarOffer(carOffer, request))
            return ResponseEntity.ok(new MessageResponse("CarOffer Updated"));
        else
            return ResponseEntity.status(403).body(new MessageResponse("Not your company"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCarOffer(@PathVariable long id, HttpServletRequest request){
        if(offerService.deleteCarOfferById(id, request))
            return ResponseEntity.ok(new MessageResponse("CarOffer Deleted"));
        else
            return ResponseEntity.status(403).body(new MessageResponse("Not your company"));
    }









}
