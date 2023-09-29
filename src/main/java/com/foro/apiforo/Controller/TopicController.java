package com.foro.apiforo.Controller;

import com.foro.apiforo.domain.topic.*;
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
@ResponseBody
@RequestMapping("/topics")
public class TopicController {

    @Autowired
    private TopicRepository topicRepository;
    @Autowired
    private TopicService service;

    @PostMapping
    public ResponseEntity<DataTopicResponse> insertTopic(@Valid @RequestBody DataTopicInsert dataTopicInsert,
                                                         UriComponentsBuilder uriComponentsBuilder) {
        DataTopicResponse responseDataTopic = service.insertTopic(dataTopicInsert);
        URI url = uriComponentsBuilder.path("/topics/{id}").buildAndExpand(responseDataTopic.id()).toUri();
        return ResponseEntity.created(url).body(responseDataTopic);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataTopicResponse> responseDataTopic(@PathVariable Long id) {
        Topic topic = topicRepository.getReferenceById(id);
        return ResponseEntity.ok(new DataTopicResponse(topic));
    }

    @GetMapping
    public ResponseEntity<Page<DataTopicResponse>> listTopics(@PageableDefault(size = 10, sort = "creationDate", direction = Sort.Direction.DESC ) Pageable pageable) {
        return ResponseEntity.ok(topicRepository.findByFlagTrue(pageable).map(DataTopicResponse::new));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DataTopicResponse> updateTopic(@RequestBody @Valid DataTopicUpdate dataTopicUpdate){
        DataTopicResponse dataTopicResponse = service.UpdateTopic(dataTopicUpdate);
        return ResponseEntity.ok(dataTopicResponse);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> deleteTopic(@PathVariable Long id){
        Topic topic = topicRepository.getReferenceById(id);
        topic.disableTopic();
        return ResponseEntity.noContent().build();
    }
}
