package com.hcc.controllers;

import com.hcc.entities.Assignment;
import com.hcc.entities.User;
import com.hcc.model.AssignmentResponseDto;
import com.hcc.services.AssignmentService;
import com.hcc.services.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/assignments")
public class AssignmentController {
    private final AssignmentService assignmentService;

    @Autowired
    public AssignmentController(AssignmentService assignmentService) {
        this.assignmentService = assignmentService;
    }

    @GetMapping("/")
    public ResponseEntity<List<AssignmentResponseDto>> getAssignmentByUser() {
        return ResponseEntity.ok(assignmentService.getAssignmentsByUser());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AssignmentResponseDto> getAssignmentById(@PathVariable("id") Long id) {
        AssignmentResponseDto assignmentResponseDto = assignmentService.getAssignmentById(id);
        return ResponseEntity.ok(assignmentResponseDto);
    }

    @PostMapping
    public ResponseEntity<Assignment> addAssignment( @RequestBody Assignment newAssignment) {
        try {
            Assignment createdAssignment = assignmentService.addAssignment(newAssignment);
            return new ResponseEntity<>(createdAssignment, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<AssignmentResponseDto> putAssignmentById(@RequestBody Assignment assignment, @PathVariable("id") Long id) {
        return ResponseEntity.ok(assignmentService.updateAssignment(assignment, id));
    }

}
