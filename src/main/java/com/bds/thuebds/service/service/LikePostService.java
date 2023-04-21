package com.bds.thuebds.service.service;

import com.bds.thuebds.dto.LikePostDTO;
import com.bds.thuebds.entity.LikePostEntity;
import com.bds.thuebds.entity.NotificationEntity;
import com.bds.thuebds.entity.PostEntity;
import com.bds.thuebds.entity.UserEntity;
import com.bds.thuebds.mapper.LikePostMapper;
import com.bds.thuebds.repository.LikePostRepository;
import com.bds.thuebds.repository.NotificationRepository;
import com.bds.thuebds.repository.PostApartmentRepository;
import com.bds.thuebds.repository.UserRepository;
import com.bds.thuebds.service.iService.ILikePostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class LikePostService implements ILikePostService {

	@Autowired
	LikePostRepository likePostRepository;

	@Autowired
	PostApartmentRepository postRepository;

	@Autowired
	NotificationRepository notificationRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	LikePostMapper likePostMapper;

	@Override
	public LikePostDTO saveNewLike(LikePostDTO likePostDTO) {
//		increasing post total like
		PostEntity postEntity = postRepository.findById(likePostDTO.getPostId()).orElseThrow(EntityNotFoundException::new);
		postEntity.setTotalLike(postEntity.getTotalLike()+1);
//		save notification
		NotificationEntity notification = new NotificationEntity();
		notification.setSenderId(likePostDTO.getUserId());
		notification.setReceiverId(postEntity.getAuthorId());
		UserEntity user = userRepository.getUserEntityByUserId(likePostDTO.getUserId());
		notification.setNotificationContent(user.getFullName() + " has like your post " + postEntity.getPostTitle());
		notification.setPostId(likePostDTO.getPostId());
		notificationRepository.save(notification);

		return likePostMapper.entityToDTO(likePostRepository.save(likePostMapper.dtoToEntity(likePostDTO)));
	}

	@Override
	public LikePostDTO updateLike(LikePostDTO likePostDTO) {
		LikePostEntity oldLike = likePostRepository.findById(likePostDTO.getLikePostId()).orElseThrow(EntityNotFoundException::new);
		return likePostMapper.entityToDTO(likePostMapper.updateLike(oldLike, likePostMapper.dtoToEntity(likePostDTO)));
	}

	@Override
	public List<LikePostDTO> getAllLikeOfPost(Long postId) {
		return likePostRepository.getLikePostEntitiesByPostId(postId)
			.stream()
			.map(likePostEntity -> likePostMapper.entityToDTO(likePostEntity))
			.collect(Collectors.toList());
	}

	@Override
	public List<LikePostDTO> getAllLikeByUserId(Long userId) {
		return likePostRepository.getLikePostEntitiesByUserId(userId)
				.stream()
				.map(likePostEntity -> likePostMapper.entityToDTO(likePostEntity))
				.collect(Collectors.toList());
	}
}
