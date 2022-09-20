package com.api.entity.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class VideoResponse {
    private Long id;
    private String name;
    private byte[] data;

    public VideoResponse(Long id, String name){
        this.id = id;
        this.name = name;
    }
}
