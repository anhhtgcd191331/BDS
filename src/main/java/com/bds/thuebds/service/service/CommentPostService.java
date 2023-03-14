package com.bds.thuebds.service.service;

import com.bds.thuebds.dto.CommentPostDTO;
import com.bds.thuebds.entity.CommentPostEntity;
import com.bds.thuebds.entity.NotificationEntity;
import com.bds.thuebds.entity.PostEntity;
import com.bds.thuebds.entity.UserEntity;
import com.bds.thuebds.mapper.CommentPostMapper;
import com.bds.thuebds.repository.CommentPostRepository;
import com.bds.thuebds.repository.NotificationRepository;
import com.bds.thuebds.repository.PostApartmentRepository;
import com.bds.thuebds.repository.UserRepository;
import com.bds.thuebds.service.iService.ICommentPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CommentPostService implements ICommentPostService {

	@Autowired
	CommentPostRepository commentPostRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	PostApartmentRepository postRepository;

	@Autowired
	NotificationRepository notificationRepository;

	@Autowired
	CommentPostMapper commentPostMapper;

	@Override
	public CommentPostDTO saveNewComment(CommentPostDTO commentPostDTO) {
		//		save notification
		PostEntity postEntity = postRepository.findById(commentPostDTO.getCommentPostId())
			.orElseThrow(EntityNotFoundException::new);
		UserEntity commentary = userRepository.getUserEntityByUserId(commentPostDTO.getUserId());
		UserEntity authorOfPost = userRepository
			.getUserEntityByUserId(postEntity.getAuthorId());
		NotificationEntity notification = new NotificationEntity();
		notification.setSenderId(commentary.getId());
		notification.setReceiverId(authorOfPost.getId());
		notification.setNotificationContent(commentary.getFullName()+" has commented your post "+postEntity.getPostTitle());
		notificationRepository.save(notification);

		return commentPostMapper.entityToDTO(
			commentPostRepository.save(
				commentPostMapper.dtoToEntity(commentPostDTO)
			)
		);
	}

	@Override
	public CommentPostDTO updateComment(CommentPostDTO commentPostDTO) {
		CommentPostEntity oldComment = commentPostRepository.findById(commentPostDTO.getCommentPostId()).orElseThrow(EntityNotFoundException::new);
		return commentPostMapper.entityToDTO(
			commentPostRepository.save(
				commentPostMapper.updateCommentPost(oldComment,commentPostMapper.dtoToEntity(commentPostDTO))
			)
		);
	}

	@Override
	public void deleteComment(CommentPostDTO commentPostDTO) {
		commentPostRepository.delete(
			commentPostMapper.dtoToEntity(commentPostDTO)
		);
	}

	@Override
	public List<CommentPostDTO> getAllCommentOfPost(Long postId) {
		return commentPostRepository.getCommentPostEntitiesByPostId(postId)
			.stream().map(commentPostEntity -> commentPostMapper.entityToDTO(commentPostEntity))
			.collect(Collectors.toList());
	}
}
