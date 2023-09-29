package com.foro.apiforo.domain.answer;

import com.foro.apiforo.domain.topic.Topic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
    Page<Answer> findByFlagTrue(Pageable pageable);

    @Query("""
            select a.flag from Answer a    
            where a.id =:idAnswer        
            """)
    Boolean findActiveById(Long idAnswer);

}

//selecciona columna flag de la tabla topic(t)
//donde el identificador de topic sea igual al identificador que enviamos por parametro