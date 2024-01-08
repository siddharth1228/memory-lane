package com.personal.memorylaneservice.repo;

import com.personal.memorylaneservice.model.VideoData;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface VideosRepository extends MongoRepository<VideoData, ObjectId> {

    @Query("{'publicId' : ?0 }")
    VideoData getVideoDataByPublicId(String publicId);

    @Query(value = "{'publicId' : ?0 }", delete = true)
    void deleteVideoDataByPublicId(String publicId);

}
