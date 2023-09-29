package com.foro.apiforo.domain.topic.validations;

import com.foro.apiforo.domain.topic.DataTopicInsert;
import com.foro.apiforo.domain.topic.DataTopicUpdate;

public interface TopicsValidator {
    public void validar(DataTopicInsert dataTopicInsert, DataTopicUpdate dataTopicUpdate);
}
