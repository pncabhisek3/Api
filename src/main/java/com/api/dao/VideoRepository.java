package com.api.dao;

import com.api.entity.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VideoRepository extends JpaRepository<Video, Long> {
    Video findByName(String name);

    boolean existsByName(String name);

    @Query(nativeQuery = true, value="SELECT id, name FROM video")
    List<Object[]> getShallowVideoDetails();
}
