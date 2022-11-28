package com.fliurkevych.pdp.pdpspringcore.controller;

import com.fliurkevych.pdp.pdpspringcore.facade.BookingFacade;
import com.fliurkevych.pdp.pdpspringcore.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Oleh Fliurkevych
 */
@RestController
@RequestMapping("/users")
public class UserController {

  private final BookingFacade bookingFacade;

  @Autowired
  public UserController(BookingFacade bookingFacade) {
    this.bookingFacade = bookingFacade;
  }

  @GetMapping
  public ResponseEntity<List<User>> getAllUsers() {
    return ResponseEntity.ok(bookingFacade.getAllUsers());
  }

  @GetMapping(path = "/{userId}")
  public ResponseEntity<User> getUserById(@PathVariable("userId") Long userId) {
    return ResponseEntity.ok(bookingFacade.getUserById(userId));
  }

  @GetMapping(path = "/search/email/{email}")
  public ResponseEntity<User> getUserByEmail(@PathVariable("email") String email) {
    return ResponseEntity.ok(bookingFacade.getUserByEmail(email));
  }

  @GetMapping(path = "/search/name/{name}")
  public ResponseEntity<List<User>> searchUsersByName(@PathVariable("name") String name,
    @PageableDefault Pageable pageable) {
    return ResponseEntity.ok(bookingFacade.getUsersByName(name, pageable));
  }

  @PostMapping(path = "/")
  public ResponseEntity<User> createUser(@RequestBody User user) {
    return ResponseEntity.ok(bookingFacade.createUser(user));
  }

  @PutMapping(path = "/")
  public ResponseEntity<User> updateUser(@RequestBody User user) {
    return ResponseEntity.ok(bookingFacade.updateUser(user));
  }

  @DeleteMapping(path = "/{userId}")
  public ResponseEntity<Boolean> deleteUser(@PathVariable Long userId) {
    return ResponseEntity.ok(bookingFacade.deleteUser(userId));
  }

}
