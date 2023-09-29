package com.foro.apiforo.domain.answer;

import com.foro.apiforo.domain.topic.Topic;
import com.foro.apiforo.domain.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name = "answers")
@Entity(name =  "Answer")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topic_id")
    private Topic topic;

    private String message;

    @Column(name = "creation_date")
    private LocalDateTime creationDate;

    private boolean flag;

    public Answer(DataAnswerInsert dataAnswerInsert, User user, Topic topic){
        this.flag = true;
        this.user = user;
        this.topic = topic;
        this.message = dataAnswerInsert.message();
        this.creationDate = dataAnswerInsert.creationDate();
    }

    public void updateAnswer(DataAnswerUpdate dataAnswerUpdate){
        if (dataAnswerUpdate.message() !=null){
            this.message = dataAnswerUpdate.message();
        }
    }

    public void disableAnswer(){
        this.flag = false;
    }

}
