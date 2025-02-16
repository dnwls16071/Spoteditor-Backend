package com.spoteditor.backend.follow.repository;

import com.spoteditor.backend.follow.entity.Follow;
import com.spoteditor.backend.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FollowRepository extends JpaRepository<Follow, Long>, FollowRepositoryCustom {

	Optional<Follow> findFollowByFollowerAndFollowing(User follower, User following);

	void deleteByFollowerAndFollowing(User follower, User following);
}
