package com.api.service;

import com.api.dao.VideoRepository;
import com.api.entity.Video;
import com.api.entity.response.VideoResponse;
import com.api.exceptions.ApiException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class VideoService {

    private final VideoRepository videoRepository;

    public Video getVideo(String name) {
        if (!videoRepository.existsByName(name)) {
            log.info("ERROR: Video not found by name");
            throw new ApiException(ApiException.Code.FETCH_ERROR, "Video not found by name");
        }
        return videoRepository.findByName(name);
    }

    public List<VideoResponse> getShallowVideoDetails() {
        List<Object[]> dbOb = videoRepository.getShallowVideoDetails();
        return (CollectionUtils.isEmpty(dbOb)) ? Collections.EMPTY_LIST
                : dbOb.parallelStream().map(o->{
                  VideoResponse vr =  new VideoResponse(Long.parseLong(String.valueOf(o[0])), String.valueOf(o[1]));
                  return vr;
                }).collect(Collectors.toList());

    }

    public void saveVideo(MultipartFile file, String name) throws ApiException {
        if (videoRepository.existsByName(name)) {
            log.info("ERROR: Video not found by name");
            throw new ApiException(ApiException.Code.FETCH_ERROR, "Video not found by name");
        }
        Video newVid = null;
        try {
            newVid = new Video(name, file.getBytes());
        } catch (IOException e) {
            log.info("ERROR: Video not saved.", e);
            throw new ApiException(ApiException.Code.SAVE_ERROR);
        }
        videoRepository.save(newVid);
    }
}
