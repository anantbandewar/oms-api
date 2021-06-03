package com.assignment.oms.service;

import com.assignment.oms.domain.User;
import com.assignment.oms.dto.UserDetails;
import com.assignment.oms.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ObjectMapper mapper;


    @Override
    public UserDetails getUserDetails(Long userId) {
        UserDetails response = null;

        try {
            Optional<User> user = userRepository.findById(userId);
            if (user.isPresent()) {
                response = mapper.convertValue(user, UserDetails.class);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return response;
    }
}
