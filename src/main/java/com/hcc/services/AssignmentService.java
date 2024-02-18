package com.hcc.services;

import com.hcc.entities.Assignment;
import com.hcc.model.AssignmentResponseDto;
import com.hcc.repositories.AssignmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class AssignmentService {

    @Autowired
    AssignmentRepository assignmentRepository;

    public AssignmentResponseDto getAssignmentById(Long id) {
       Assignment assignmentEntity = assignmentRepository.findFirstById(id);
        return AssignmentResponseDto.builder()
                .withId(assignmentEntity.getId())
                .withGithubUrl(assignmentEntity.getGithubUrl())
                .build();
    }

    public List<AssignmentResponseDto> getAssignmentsByUser() {
        List<Assignment> assignmentEntities = assignmentRepository.findAll();
        List<AssignmentResponseDto> assignmentResponseDtoList = new ArrayList<>();

        for (Assignment assignmentEntity : assignmentEntities) {
        assignmentResponseDtoList.add(AssignmentResponseDto.builder()
                .withUser(assignmentEntity.getUser())
                .withGithubUrl(assignmentEntity.getGithubUrl())
                .build());
        }
        return assignmentResponseDtoList;
    }

    public Assignment addAssignment (Assignment newAssignment) {
        return assignmentRepository.save(newAssignment);
    }

    public AssignmentResponseDto updateAssignment (Assignment assignment, Long id) {
        Assignment assignmentEntity = assignmentRepository.findFirstById(id);
        assignmentEntity.setBranch(assignment.getBranch());
        assignmentEntity.setCodeReviewer(assignment.getCodeReviewer());
        assignmentEntity.setStatus(assignment.getStatus());

        return AssignmentResponseDto.builder()
                .withId(assignmentEntity.getId())
                .withGithubUrl(assignmentEntity.getGithubUrl())
                .build();
    }
}
