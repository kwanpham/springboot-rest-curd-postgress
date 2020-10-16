package com.quandev.springbootrestcurdpostgress.repository;

import java.util.List;

import com.quandev.springbootrestcurdpostgress.model.CustomTutorial;
import com.quandev.springbootrestcurdpostgress.model.Tutorial;
import com.quandev.springbootrestcurdpostgress.model.TutorialEnum;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;


public interface TutorialRepository extends JpaRepository<Tutorial, Long> {
    List<Tutorial> findByPublished(boolean published);

    List<Tutorial> findByTitleContaining(String title);

    @Transactional
    @Modifying
    @Query("update Tutorial t set t.status = ?1 where t.id = ?2")
    void deleteToturial(TutorialEnum tutorialEnum, long id);

    @Query("select t from Tutorial t where " +
            "(:title is null or t.title = :title)" +
            "and (:description is null or t.description = :description)" +
            "and (:published is null or t.published = :published)" +
            "and (:status is null or t.status = :status)")
    Page<Tutorial> findDysnamic(String title,
                                String description,
                                Boolean published,
                                TutorialEnum status,
                                Pageable pageable);

    @Query(value = "SELECT SUM(count_book) AS sumCount , SUM(id) AS sumId  FROM tutorials" , nativeQuery = true)
    CustomTutorial getCustom();

}