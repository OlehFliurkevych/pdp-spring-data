package com.fliurkevych.pdp.pdpspringcore.controller;

import com.fliurkevych.pdp.pdpspringcore.facade.BookingFacade;
import com.fliurkevych.pdp.pdpspringcore.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author Oleh Fliurkevych
 */
@Controller
@RequestMapping("/users")
public class UserController {

  private final BookingFacade bookingFacade;

  @Autowired
  public UserController(BookingFacade bookingFacade) {
    this.bookingFacade = bookingFacade;
  }

  @GetMapping
  public String getAllUsers(Model model) {
    model.addAttribute("users", bookingFacade.getAllUsers());
    return "user-list";
  }

  @GetMapping(path = "/{userId}")
  public String getUserById(@PathVariable("userId") Long userId, Model model) {
    model.addAttribute("users", List.of(bookingFacade.getUserById(userId)));
    return "user-list";
  }

  @GetMapping(path = "/search/email/{email}")
  public String getUserByEmail(@PathVariable("email") String email, Model model) {
    model.addAttribute("users", List.of(bookingFacade.getUserByEmail(email)));
    return "user-list";
  }

  @GetMapping(path = "/search/name/{name}")
  public String searchUsersByName(@PathVariable("name") String name,
    @PageableDefault Pageable pageable, Model model) {
    model.addAttribute("users", bookingFacade.getUsersByName(name, pageable));
    return "user-list";
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
