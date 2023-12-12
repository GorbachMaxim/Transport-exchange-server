package com.example.transportexchangeserver.service;

import com.example.transportexchangeserver.dto.mapper.CarOfferListMapper;
import com.example.transportexchangeserver.dto.mapper.CarOfferMapper;
import com.example.transportexchangeserver.dto.mapper.CargoOfferListMapper;
import com.example.transportexchangeserver.dto.mapper.CargoOfferMapper;
import com.example.transportexchangeserver.dto.pojo.CarOfferDTO;
import com.example.transportexchangeserver.dto.pojo.CargoOfferDTO;
import com.example.transportexchangeserver.dto.pojo.CompanyDTO;
import com.example.transportexchangeserver.model.*;
import com.example.transportexchangeserver.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class OfferService {

    private final CarOfferRepository carOfferRepository;
    private final CarRepository carRepository;
    private final CargoOfferRepository cargoOfferRepository;
    private final CargoRepository cargoRepository;
    private final CompanyRepository companyRepository;

    private final CommentRepository commentRepository;





    private final UserDetailsServiceImpl userService;



    private final CarOfferMapper carOfferMapper;
    private final CarOfferListMapper carOfferListMapper;

    private final CargoOfferMapper cargoOfferMapper;
    private final CargoOfferListMapper cargoOfferListMapper;



    public List<CarOfferDTO> getAllCarOffers(){
        List<CarOffer> modelList = carOfferRepository.findAll();
        List<CarOfferDTO> DTOList = carOfferListMapper.toDTOList(modelList);
        return DTOList;
    };

    public CarOfferDTO getCarOfferById(long id){
        Optional<CarOffer> carOffer = carOfferRepository.findById(id);
        if(carOffer.isPresent()) {
            CarOfferDTO carOfferDTO = carOfferMapper.toDTO(carOffer.get());
            return carOfferDTO;
        }
        return null;
    };

    public boolean addCarOffer(CarOfferDTO carOfferDTO, HttpServletRequest request) {
        User user = userService.getUserFromJWT(request);
        if(user == null) {
            return false;
        }

        Optional<Company> company = companyRepository.getCompanyByUser_Id(user.getId());
        if (!company.isPresent()) {
            return false;
        }


        CarOffer carOffer = carOfferMapper.toModel(carOfferDTO);
        carRepository.save(carOffer.getCar());

        carOffer.setCompany(company.get());
        carOfferRepository.save(carOffer);
        return true;
    }

    public boolean updateCarOffer(CarOfferDTO carOfferDTO, HttpServletRequest request) {
        Optional<CarOffer> carOffer = carOfferRepository.findById(carOfferDTO.getId());


        if (!carOffer.isPresent()) {
            return false;
        }

        User user = userService.getUserFromJWT(request);
        if(user == null) {
            return false;
        }

        if (!Objects.equals(carOffer.get().getCompany().getUser().getId(), user.getId())){
            return false;
        }

        CarOffer UpdatedCarOffer = carOfferMapper.toModel(carOfferDTO);
        UpdatedCarOffer.setCompany(carOffer.get().getCompany());
        UpdatedCarOffer.getCar().setId(carOffer.get().getCar().getId());
        carRepository.save(UpdatedCarOffer.getCar());

        carOfferRepository.save(UpdatedCarOffer);
        return true;
    }

    @Transactional
    public boolean deleteCarOfferById(long id, HttpServletRequest request) {
        Optional<CarOffer> carOffer = carOfferRepository.findById(id);


        if (!carOffer.isPresent()) {
            return false;
        }

        User user = userService.getUserFromJWT(request);
        if(user == null) {
            return false;
        }

        Role admin = new Role();
        admin.setId(2);
        admin.setName(ERole.ROLE_ADMIN);

        if (!user.getRoles().contains(admin)) {
            if(!carOffer.get().getCompany().getUser().getId().equals(user.getId())) {
                return false;
            }
        }
        commentRepository.deleteAllCommentsByOffer_id(id);
        carOfferRepository.deleteById(id);
        return true;
    }












    public List<CargoOfferDTO> getAllCargoOffers(){

        List<CargoOffer> modelList = cargoOfferRepository.findAll();
        List<CargoOfferDTO> DTOList = cargoOfferListMapper.toDTOList(modelList);
        return DTOList;
    };

    public CargoOfferDTO getCargoOfferById(long id){
        Optional<CargoOffer> cargoOffer = cargoOfferRepository.findById(id);
        if(cargoOffer.isPresent()) {
            CargoOfferDTO cargoOfferDTO = cargoOfferMapper.toDTO(cargoOffer.get());
            return cargoOfferDTO;
        }
        return null;
    };

    public boolean addCargoOffer(CargoOfferDTO cargoOfferDTO, HttpServletRequest request) {
        User user = userService.getUserFromJWT(request);
        if(user == null) {
            return false;
        }

        Optional<Company> company = companyRepository.getCompanyByUser_Id(user.getId());
        if (!company.isPresent()) {
            return false;
        }


        CargoOffer cargoOffer = cargoOfferMapper.toModel(cargoOfferDTO);
        cargoRepository.save(cargoOffer.getCargo());

        cargoOffer.setCompany(company.get());
        cargoOfferRepository.save(cargoOffer);
        return true;
    }

    public boolean updateCargoOffer(CargoOfferDTO cargoOfferDTO, HttpServletRequest request) {
        Optional<CargoOffer> cargoOffer = cargoOfferRepository.findById(cargoOfferDTO.getId());


        if (!cargoOffer.isPresent()) {
            return false;
        }

        User user = userService.getUserFromJWT(request);
        if(user == null) {
            return false;
        }

        if (!Objects.equals(cargoOffer.get().getCompany().getUser().getId(), user.getId())){
            return false;
        }

        CargoOffer UpdatedCargoOffer = cargoOfferMapper.toModel(cargoOfferDTO);
        UpdatedCargoOffer.setCompany(cargoOffer.get().getCompany());
        UpdatedCargoOffer.getCargo().setId(cargoOffer.get().getCargo().getId());
        cargoRepository.save(UpdatedCargoOffer.getCargo());

        cargoOfferRepository.save(UpdatedCargoOffer);
        return true;
    }

    @Transactional
    public boolean deleteCargoOfferById(long id, HttpServletRequest request) {
        Optional<CargoOffer> cargoOffer = cargoOfferRepository.findById(id);


        if (!cargoOffer.isPresent()) {
            return false;
        }

        User user = userService.getUserFromJWT(request);
        if(user == null) {
            return false;
        }

        Role admin = new Role();
        admin.setId(2);
        admin.setName(ERole.ROLE_ADMIN);


        if (!user.getRoles().contains(admin)) {
            if(!cargoOffer.get().getCompany().getUser().getId().equals(user.getId())) {
                return false;
            }
        }
        commentRepository.deleteAllCommentsByOffer_id(id);

        cargoOfferRepository.deleteById(id);
        return true;
    }
}
