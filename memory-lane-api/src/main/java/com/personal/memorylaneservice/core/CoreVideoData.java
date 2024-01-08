package com.personal.memorylaneservice.core;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CoreVideoData {

    private String title;

    private String description;

    private String publicId;

    private String url;
}
