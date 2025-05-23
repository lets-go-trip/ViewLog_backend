package com.trip.viewlog.review.application;

import com.trip.viewlog.attraction.application.outputport.AttractionRepository;
import com.trip.viewlog.attraction.domain.Attraction;
import com.trip.viewlog.global.application.S3Service;
import com.trip.viewlog.global.domain.File;
import com.trip.viewlog.global.entity.FileEntity;
import com.trip.viewlog.global.entity.enumeration.FileType;
import com.trip.viewlog.global.infrastructure.FileJpaRepository;
import com.trip.viewlog.review.application.outputport.ReviewRepository;
import com.trip.viewlog.review.controller.inputport.ReviewService;
import com.trip.viewlog.review.controller.request.ReviewRequest;
import com.trip.viewlog.review.domain.Review;
import com.trip.viewlog.user.application.outputport.UserRepository;
import com.trip.viewlog.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final AttractionRepository attractionRepository;
    private final S3Service s3Service;
    private final FileJpaRepository fileJpaRepository;

    @Override
    public Review getById(Long id) {
        Review review = reviewRepository.findById(id);
        List<FileEntity> fileEntities = fileJpaRepository.findByFileTypeAndTargetId(FileType.REVIEW, id);
        for (FileEntity fileEntity : fileEntities) {
            review.addFile(fileEntity.toModel());
        }
        return review;
    }

    @Override
    public List<Review> getAllByUser(String oauthInfo) {
        User user = userRepository.findByOauthInfo(oauthInfo).orElse(null);
        List<Review> reviews = reviewRepository.findAllByUser(user);
        for (Review review : reviews) {
            List<FileEntity> fileEntities = fileJpaRepository.findByFileTypeAndTargetId(FileType.REVIEW, review.getId());
            for (FileEntity fileEntity : fileEntities) {
                review.addFile(fileEntity.toModel());
            }
        }
        return reviews;
    }

    @Override
    public List<Review> getAllByAttraction(Long attractionId) {
        Attraction attraction = attractionRepository.findById(attractionId).orElse(null);
        List<Review> reviews = reviewRepository.findAllByAttraction(attraction);
        for (Review review : reviews) {
            List<FileEntity> fileEntities = fileJpaRepository.findByFileTypeAndTargetId(FileType.REVIEW, review.getId());
            for (FileEntity fileEntity : fileEntities) {
                review.addFile(fileEntity.toModel());
            }
        }
        return reviews;
    }

    @Override
    @Transactional
    public Review create(String oauthInfo, ReviewRequest reviewRequest, List<MultipartFile> images) {
        User user = userRepository.findByOauthInfo(oauthInfo).orElse(null);
        Attraction attraction = attractionRepository.findById(reviewRequest.getAttractionId()).orElse(null);
        Review review = Review.from(user, attraction, reviewRequest.getContent());
        Review savedReview = reviewRepository.save(review);

        if (images != null) {
            List<String> fileNames = s3Service.uploadFile(images);
            for (int i = 0; i < images.size(); i++) {
                File file = File.builder()
                        .fileName(fileNames.get(i))
                        .originalName(images.get(i).getOriginalFilename())
                        .fileType(FileType.REVIEW)
                        .targetId(savedReview.getId())
                        .build();
                savedReview.addFile(fileJpaRepository.save(FileEntity.from(file)).toModel());
            }
        }
        return savedReview;
    }

    @Override
    public Review update(Long reviewId, ReviewRequest reviewRequest, List<MultipartFile> images) {
        Review review = reviewRepository.findById(reviewId);
        review = review.update(reviewRequest.getContent());
        List<FileEntity> fileEntities = fileJpaRepository.findByFileTypeAndTargetId(FileType.REVIEW, reviewId);

        for (FileEntity fileEntity : fileEntities) {
            s3Service.deleteFile(fileEntity.getFileName());
            fileJpaRepository.delete(fileEntity);
        }

        if (images != null) {
            List<String> fileNames = s3Service.uploadFile(images);
            for (int i = 0; i < images.size(); i++) {
                File file = File.builder()
                        .fileName(fileNames.get(i))
                        .originalName(images.get(i).getOriginalFilename())
                        .fileType(FileType.REVIEW)
                        .targetId(review.getId())
                        .build();
                review.addFile(fileJpaRepository.save(FileEntity.from(file)).toModel());
            }
        }
        return reviewRepository.save(review);
    }

    @Override
    public void delete(Long reviewId) {
        Review review = reviewRepository.findById(reviewId);
        List<FileEntity> fileEntities = fileJpaRepository.findByFileTypeAndTargetId(FileType.REVIEW, review.getId());
        for (FileEntity fileEntity : fileEntities) {
            s3Service.deleteFile(fileEntity.getFileName());
            fileJpaRepository.delete(fileEntity);
        }
        reviewRepository.delete(review);
    }
}
