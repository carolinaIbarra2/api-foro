package com.foro.apiforo.domain.topic;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

//queries para la base de datos
public interface TopicRepository extends JpaRepository<Topic, Long> {

    Page<Topic> findByFlagTrue(Pageable pageable);

    //Verifica si el topic esta activo
    @Query("""
            select t.flag from Topic t
            where t.id =:idTopic
            """)
    Boolean findActiveById(Long idTopic);

    //Verifica si ya existe un mensaje igual
    @Query("""
            SELECT EXISTS(
                SELECT t FROM Topic t
                WHERE t.message =:message
                AND (:id IS NULL OR t.id != :id)
            ) AS duplicate_message
            """)
    Boolean findDuplicateMessage(String message, Long id);

    //Verifica si ya existe un titulo igual
    @Query("""
            SELECT EXISTS(
                SELECT t FROM Topic t
                WHERE t.tittle =:tittle
                AND (:id IS NULL OR t.id != :id)
            ) AS duplicate_tittle
            """)
    Boolean findDuplicateTittle(String tittle, Long id);

}
