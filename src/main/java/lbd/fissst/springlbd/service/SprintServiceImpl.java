package lbd.fissst.springlbd.service;

import lbd.fissst.springlbd.Entity.Sprint;
import lbd.fissst.springlbd.repository.SprintRepository;
import lbd.fissst.springlbd.service.exception.SprintNotValidException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class SprintServiceImpl implements SprintService{

    @Autowired
    SprintRepository sprintRepository;

    @Override
    @Transactional
    public Sprint save(Sprint sprint) {

        if(sprint.getName().isBlank()){
            throw new SprintNotValidException("name cannot be empty!");
        }
        if(sprint.getStatus() == null){
            throw new SprintNotValidException("invalid status!");
        }
        if(sprint.getDateStart().compareTo(sprint.getDateStart()) >= 0){
            throw new SprintNotValidException("Start date cannot be greater or equal to end date!");
        }

        return sprintRepository.save(sprint);
    }
}
