package com.spoteditor.backend.user.service;

import com.spoteditor.backend.global.exception.UserException;
import com.spoteditor.backend.place.entity.Place;
import com.spoteditor.backend.place.repository.PlaceRepository;
import com.spoteditor.backend.placelog.entity.PlaceLog;
import com.spoteditor.backend.user.entity.User;
import com.spoteditor.backend.user.repository.UserRepository;
import com.spoteditor.backend.user.service.dto.UserResult;
import com.spoteditor.backend.user.service.dto.UserUpdateCommand;
import com.spoteditor.backend.user.service.dto.UserUpdateResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.spoteditor.backend.global.response.ErrorCode.DELETED_USER;
import static com.spoteditor.backend.global.response.ErrorCode.NOT_FOUND_USER;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    @Override
    @Transactional
    public UserResult getUser(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserException(NOT_FOUND_USER));

        if(user.isDeleted()) {
            throw new UserException(DELETED_USER);
        }

        Long follower = 0L;
        Long following = 0L;
        List<PlaceLog> placeLogs = List.of();
        List<PlaceLog> bookmarkPlaceLogs = List.of();
        List<Place> bookmarkPlaces = List.of();

        return new UserResult(user, follower, following, placeLogs, bookmarkPlaceLogs, bookmarkPlaces);
    }

    @Override
    @Transactional
    public UserUpdateResult updateUser(Long userId, UserUpdateCommand command) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserException(NOT_FOUND_USER));

        if(user.isDeleted()) {
            throw new UserException(DELETED_USER);
        }

        user.update(command);
        userRepository.save(user);

        return new UserUpdateResult(user);
    }

    @Override
    @Transactional
    public void deleteUser(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserException(NOT_FOUND_USER));

        if(user.isDeleted()) {
            throw new UserException(DELETED_USER);
        }

        user.softDelete();
        userRepository.save(user);
    }
}
