package com.api.controller;

import com.api.entity.response.ApiResponse;
import com.api.entity.response.VideoRequest;
import com.api.entity.response.VideoResponse;
import com.api.service.VideoService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("video")
@RequiredArgsConstructor
@Api(tags = "Video-Controller")
public class VideoController {
    private final VideoService videoService;

    @PostMapping
    public ApiResponse<Void> saveVideo(@RequestParam("file") MultipartFile file, @RequestParam("name") String name){
        videoService.saveVideo(file, name);
        return new ApiResponse<>(true);
    }

    @GetMapping(value = "/{name}"/*, produces = "video/mp4"*/)
    public ResponseEntity<Resource> getVideoByName(@PathVariable("name") String name){
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("video/mp4"))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + name + "\"")
                .body(new ByteArrayResource(videoService.getVideo(name).getData()));
        //new ByteArrayResource(videoService.getVideo(name).getData()
//        Video video = videoService.getVideo(name);
//        return new ApiResponse<>(new VideoResponse(video.getId(), video.getName(), video.getData()));
    }

    @GetMapping("/names")
    public ApiResponse<List<VideoResponse>> getAllVideoNames(){
        return new ApiResponse<>(videoService.getShallowVideoDetails());
    }
}