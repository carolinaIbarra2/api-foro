package com.foro.apiforo.domain.topic.validations;

import com.foro.apiforo.domain.topic.DataTopicInsert;
import com.foro.apiforo.domain.topic.DataTopicUpdate;
import com.foro.apiforo.domain.topic.TopicRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DuplicateRecords implements TopicsValidator {

    @Autowired
    private TopicRepository repository;

    @Override
    public void validar(DataTopicInsert dataTopicInsert, DataTopicUpdate dataTopicUpdate) {
        Boolean duplicateMessage = null;
        Boolean duplicateTittle = null;

        if (dataTopicInsert != null) {
            duplicateMessage = repository.findDuplicateMessage(dataTopicInsert.message(), null);
            duplicateTittle = repository.findDuplicateTittle(dataTopicInsert.tittle(), null);
        }
        if (dataTopicUpdate != null) {
            duplicateMessage = repository.findDuplicateMessage(dataTopicUpdate.message(), dataTopicUpdate.id());
            duplicateTittle = repository.findDuplicateTittle(dataTopicUpdate.tittle(), dataTopicUpdate.id());
        }

        if (duplicateTittle && duplicateMessage) {
            throw new ValidationException("There is already a topic with this title and message.");
        }
        if (duplicateTittle) {
            throw new ValidationException("There is already a topic with this tittle.");
        }
        if (duplicateMessage) {
            throw new ValidationException("There is already a topic with this message.");
        }
    }
}
