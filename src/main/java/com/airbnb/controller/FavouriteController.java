package com.airbnb.controller;


import com.airbnb.entity.Favourite;
import com.airbnb.entity.PropertyUser;
import com.airbnb.repository.FavouriteRepository;
import com.airbnb.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/favourite")
public class FavouriteController {
    private FavouriteRepository favouriteRepository;
    private UserService userService;

    public FavouriteController(FavouriteRepository favouriteRepository, UserService userService) {
        this.favouriteRepository = favouriteRepository;
        this.userService = userService;
    }

    @DeleteMapping("/{id}")

    public ResponseEntity<?> deleteFavourite(@PathVariable long id){
        userService.deleteFavourite(id);
        return new ResponseEntity<>("Favourite Deleted successfully" , HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Favourite> addFavourite(
            @RequestBody Favourite favourite ,
            @AuthenticationPrincipal PropertyUser user
            ){
        favourite.setPropertyUser(user);
        Favourite savedFavourite = favouriteRepository.save(favourite);
        return new ResponseEntity<>(savedFavourite , HttpStatus.CREATED);
    }

}
