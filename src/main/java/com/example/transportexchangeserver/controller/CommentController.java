package com.example.transportexchangeserver.controller;

import com.example.transportexchangeserver.dto.MessageResponse;
import com.example.transportexchangeserver.dto.pojo.CommentDTO;
import com.example.transportexchangeserver.dto.pojo.ReviewDTO;
import com.example.transportexchangeserver.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/comments")
@CrossOrigin
public class CommentController {

    @Autowired
    private CommentService commentService;



    @GetMapping("/{id}")
    public List<CommentDTO> getOfferComments(HttpServletRequest request, @PathVariable long id){
        List<CommentDTO> commentDTOS = commentService.getCommentsByOfferId(request, id);
        return commentDTOS;
    }

    @PostMapping("/{id}")
    public ResponseEntity<?> addComment(HttpServletRequest request, @PathVariable long id, @RequestBody @Valid CommentDTO commentDTO) {
        if(commentService.addComment(request, commentDTO, id))
            return ResponseEntity.ok(new MessageResponse("Comment Created"));
        else
            return ResponseEntity.badRequest().body(new MessageResponse("Can't create a comment"));

    }



    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteComment(HttpServletRequest request, @PathVariable long id){
        commentService.deleteCommentById(request, id);
        return ResponseEntity.ok(new MessageResponse("Comment DELETED"));
    }
}
