package com.personal.memorylaneservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "videos")
public class VideoData {

    @Id
    private ObjectId id;

    private String title;

    private String description;

    private String publicId;

    private String url;
}
