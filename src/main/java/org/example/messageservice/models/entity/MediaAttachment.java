package org.example.messageservice.models.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class MediaAttachment {
    private String id;
    private String type;
    private  String url;
}
