package com.bds.thuebds.service.service;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.bds.thuebds.constant.AmazonKeys;
import com.bds.thuebds.dto.PostDTO;
import com.bds.thuebds.entity.PostEntity;
import com.bds.thuebds.mapper.PostMapper;
import com.bds.thuebds.repository.PostApartmentRepository;
import com.bds.thuebds.service.iService.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import javax.persistence.EntityNotFoundException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Transactional
public class PostService implements IPostService {

	@Autowired
	PostApartmentRepository postRepository;

	@Autowired
	PostMapper postMapper;

	private AmazonS3 s3Client;

	@PostConstruct
	private void initializeAmazon() {
		this.s3Client = new AmazonS3Client(new BasicAWSCredentials(AmazonKeys.ACCESS_KEY, AmazonKeys.SECRET_KEY));
	}

	@Override
	public void saveNewPost(PostDTO postDTO, List<MultipartFile> images, List<MultipartFile> videos) throws IOException {
		PostEntity postEntity = postMapper.dtoToEntity(postDTO);
		postEntity.setTotalLike(0);
		if (images.size() != 0) {
			List<String> imageUrls = new ArrayList<>();
			for (MultipartFile multipartFile : images) {
				imageUrls.add(getFileUrls(multipartFile));
			}
			postEntity.setImageUrls(imageUrls);
		}
		if (videos.size() != 0) {
			List<String> videoUrls = new ArrayList<>();
			for (MultipartFile multipartFile : videos) {
				videoUrls.add(getFileUrls(multipartFile));
			}
			postEntity.setVideoUrls(videoUrls);
		}
		postRepository.save(postEntity);
	}

	@Override
	public PostDTO updatePost(PostDTO postDTO) {
		PostEntity oldPost = postRepository.findById(postDTO.getPostId()).orElseThrow(EntityNotFoundException::new);
		return postMapper.entityToDto(
			postRepository.save(
				postMapper.updatePost(oldPost, postMapper.dtoToEntity(postDTO))
			));
	}

	@Override
	public List<PostDTO> getAllPost(Pageable pageable) {
		return postRepository.findAll(pageable)
			.stream()
			.map(postEntity -> postMapper.entityToDto(postEntity))
			.filter(postDTO -> !postDTO.isDeleted())
			.sorted(Comparator.comparing(PostDTO::getTotalLike).reversed())
			.collect(Collectors.toList());
	}

	@Override
	public List<PostDTO> getPostByAuthorId(Pageable pageable, Long authorId) {
		return postRepository.getPostEntitiesByAndAuthorId(pageable, authorId)
				.stream()
				.map(postEntity -> postMapper.entityToDto(postEntity))
				.filter(postDTO -> !postDTO.isDeleted())
				.sorted(Comparator.comparing(PostDTO::getTotalLike).reversed())
				.collect(Collectors.toList());
	}

	@Override
	public PostDTO getPostById(Long postId) {
		return postMapper.entityToDto(postRepository.findById(postId).orElseThrow(EntityNotFoundException::new));
	}

	@Override
	public Integer getTotalPost() {
		return Math.toIntExact(postRepository.count());
	}

	private File convertMultipartToFile(MultipartFile file) throws IOException {
		File convertFile = new File(Objects.requireNonNull(file.getOriginalFilename()));
		FileOutputStream fos = new FileOutputStream(convertFile);
		fos.write(file.getBytes());
		fos.close();
		return convertFile;
	}

	private String getFileUrls(MultipartFile multipartFile) throws IOException {
		File fileConverted = convertMultipartToFile(multipartFile);
		String fileName = new Date().getTime() + "-" + multipartFile.getOriginalFilename().replace(" ", "_");
		s3Client
			.putObject(new PutObjectRequest(AmazonKeys.BUCKET_NAME, fileName, fileConverted)
				.withCannedAcl(CannedAccessControlList.PublicRead));
		return AmazonKeys.END_POINT_URL + "/" + AmazonKeys.BUCKET_NAME + "/" + fileName;
	}
}
