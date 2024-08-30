package com.airbnb.service;

import com.airbnb.dto.LoginDto;
import com.airbnb.dto.PropertyUserdto;
import com.airbnb.entity.PropertyUser;
import com.airbnb.repository.FavouriteRepository;
import com.airbnb.repository.PropertyUserRepository;
import com.airbnb.repository.ReviewRepository;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private PropertyUserRepository userRepository;
    private ReviewRepository reviewRepository;
    private FavouriteRepository favouriteRepository;


    private JWTService jwtService;
    public UserService(PropertyUserRepository userRepository, ReviewRepository reviewRepository, FavouriteRepository favouriteRepository, JWTService jwtService) {
        this.userRepository = userRepository;
        this.reviewRepository = reviewRepository;
        this.favouriteRepository = favouriteRepository;
        this.jwtService = jwtService;
    }

    public PropertyUser addUser(PropertyUserdto propertyUserdto){
        PropertyUser user = new PropertyUser();
        user.setFirstName(propertyUserdto.getFirstName());
        user.setLastName(propertyUserdto.getLastName());
        user.setUsername(propertyUserdto.getUsername());
        user.setEmail(propertyUserdto.getEmail());
        user.setPassword(BCrypt.hashpw(propertyUserdto.getPassword() , BCrypt.gensalt(10)));
        user.setUserRole("ROLE_USER");
        PropertyUser savedUser = userRepository.save(user);

        return savedUser;

    }
    public void deletePost(long id){
        reviewRepository.deleteById(id);
    }

    public void deleteFavourite(long id){
        favouriteRepository.deleteById(id);
    }



    public String verifyLogin(LoginDto loginDto) {

        Optional<PropertyUser> opUser = userRepository.findByUsername(loginDto.getUsername());

        if (opUser.isPresent()) {
            PropertyUser propertyUser = opUser.get();

            if (BCrypt.checkpw(loginDto.getPassword(), propertyUser.getPassword())) {
                return jwtService.generateToken(propertyUser);
            }
        }
        return null;
    }
}
// opuser optional user which is the java 8 feature and which will help to handle null pointer exception