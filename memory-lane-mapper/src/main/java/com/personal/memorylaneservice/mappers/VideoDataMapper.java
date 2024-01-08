package com.personal.memorylaneservice.mappers;

import com.personal.memorylaneservice.core.CoreVideoData;
import com.personal.memorylaneservice.model.VideoData;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface VideoDataMapper {

    CoreVideoData convert(VideoData videoData);

    List<CoreVideoData> convertList(List<VideoData> videoDataList);
}
