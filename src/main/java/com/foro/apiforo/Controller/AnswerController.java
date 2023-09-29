package com.foro.apiforo.Controller;

import com.foro.apiforo.domain.answer.*;
import com.foro.apiforo.domain.topic.Topic;
import com.foro.apiforo.domain.topic.TopicRepository;
import com.foro.apiforo.domain.user.User;
import com.foro.apiforo.domain.user.UserRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/answers")
public class AnswerController {

    @Autowired
    private AnswerRepository answerRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TopicRepository topicRepository;
    @Autowired
    private AnswerService service;

    @PostMapping
    public ResponseEntity<DataAnswerResponse> insertAnswer(@Valid @RequestBody DataAnswerInsert dataAnswerInsert,
                                                           UriComponentsBuilder uriComponentsBuilder){
        DataAnswerResponse dataAnswerResponse= service.insertAnswer(dataAnswerInsert);
        URI url = uriComponentsBuilder.path("/topics/{id}").buildAndExpand(dataAnswerResponse.id()).toUri();
        return ResponseEntity.created(url).body(dataAnswerResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataAnswerResponse> responseDataTopic(@PathVariable Long id){
        Answer answer = answerRepository.getReferenceById(id);
        return ResponseEntity.ok(new DataAnswerResponse(answer));
    }

    @GetMapping
    public ResponseEntity<Page<DataAnswerResponse>> listAnswers(@PageableDefault(size = 10, sort = "creationDate", direction = Sort.Direction.DESC ) Pageable pageable) {
        return ResponseEntity.ok(answerRepository.findByFlagTrue(pageable).map(DataAnswerResponse::new));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DataAnswerResponse> updateAnswer(@RequestBody @Valid DataAnswerUpdate dataAnswerUpdate){
        Answer answer = answerRepository.getReferenceById(dataAnswerUpdate.id());
        answer.updateAnswer(dataAnswerUpdate);
        DataAnswerResponse dataAnswerResponse = new DataAnswerResponse(answer);
        return ResponseEntity.ok(dataAnswerResponse);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> deleteAnswer(@PathVariable Long id){
        Answer answer = answerRepository.getReferenceById(id);
        answer.disableAnswer();
        return ResponseEntity.noContent().build();
    }
}
