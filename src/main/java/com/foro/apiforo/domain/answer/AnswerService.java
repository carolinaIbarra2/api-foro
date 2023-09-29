package com.foro.apiforo.domain.answer;

import com.foro.apiforo.domain.topic.Topic;
import com.foro.apiforo.domain.topic.TopicRepository;
import com.foro.apiforo.domain.user.User;
import com.foro.apiforo.domain.user.UserRepository;
import com.foro.apiforo.infra.errors.IntegrityValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnswerService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private AnswerRepository answerRepository;

    public DataAnswerResponse insertAnswer(DataAnswerInsert dataAnswerInsert) {
        if (!userRepository.findById(dataAnswerInsert.userId()).isPresent()){
            throw new IntegrityValidation("This id for the user was not found");
        }
        if (!topicRepository.findById(dataAnswerInsert.topicId()).isPresent()){
            throw new IntegrityValidation(("This id for the topic was not found"));
        }
        User user = userRepository.findById(dataAnswerInsert.userId()).get();
        Topic topic = topicRepository.findById(dataAnswerInsert.topicId()).get();
        Answer answer = answerRepository.save(new Answer(dataAnswerInsert, user, topic));
        return new DataAnswerResponse(answer);
    }



}
