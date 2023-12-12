package com.example.transportexchangeserver.service;

import com.example.transportexchangeserver.dto.mapper.CommentListMapper;
import com.example.transportexchangeserver.dto.mapper.CommentMapper;
import com.example.transportexchangeserver.dto.pojo.CommentDTO;
import com.example.transportexchangeserver.model.*;
import com.example.transportexchangeserver.repository.CarOfferRepository;
import com.example.transportexchangeserver.repository.CargoOfferRepository;
import com.example.transportexchangeserver.repository.CommentRepository;
import com.example.transportexchangeserver.repository.OfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CommentService {
    @Autowired
    private CarOfferRepository carOfferRepository;

    @Autowired
    private CargoOfferRepository cargoOfferRepository;

    @Autowired
    private OfferRepository offerRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserDetailsServiceImpl userService;

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private CommentListMapper commentListMapper;

    public boolean addComment(HttpServletRequest request, CommentDTO commentDTO, long id)  {
        Optional<Offer> offer = offerRepository.findById(id);
        if(!offer.isPresent()){
            return false;
        }

        User user = userService.getUserFromJWT(request);

        if(!user.isVerified()){
            return false;
        }

        Comment comment = commentMapper.toModel(commentDTO);
        comment.setOffer(offer.get());
        comment.setUser(user);
        commentRepository.save(comment);
        return true;
    }

    public List<CommentDTO> getCommentsByOfferId(HttpServletRequest request, long id){
        User user = userService.getUserFromJWT(request);
        for (Role role : user.getRoles()) {
            if (role.getName() == ERole.ROLE_ADMIN) {
                return commentListMapper.toDTOList(commentRepository.findCommentsByOffer_id(id));
            }
        }
        Optional<Offer> offer = offerRepository.findById(id);
        if(!offer.isPresent()){
            return null;
        }

        if(offer.get().getCompany().getUser().getId() == user.getId()){
            return commentListMapper.toDTOList(commentRepository.findCommentsByOffer_id(id));
        }else
            return commentListMapper.toDTOList(commentRepository.findCommentsByOffer_idAndUser_Id(id, user.getId()));

    }


    public boolean deleteCommentById(HttpServletRequest request, long id){
        User user1 = userService.getUserFromJWT(request);

        Optional<Comment> commentOptional = commentRepository.findById(id);
        if(!commentOptional.isPresent()){
            return false;
        }
        Comment comment = commentOptional.get();

        User user2 = comment.getUser();

        Optional<Offer> offer = offerRepository.findById(comment.getOffer().getId());
        if(!offer.isPresent()){
            return false;
        }
        commentRepository.deleteById(comment.getId());

        if(Objects.equals(user1.getId(), user2.getId())){
            commentRepository.deleteById(comment.getId());
            return true;

        }


        for (Role role : user1.getRoles()) {
            if (role.getName() == ERole.ROLE_ADMIN) {
                commentRepository.deleteById(comment.getId());
                return true;
            }
        }
        return false;
    }
}
