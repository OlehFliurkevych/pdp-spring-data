package com.fliurkevych.pdp.pdpspringcore.service;

import static com.fliurkevych.pdp.pdpspringcore.util.UserTestUtils.EMAIL_1;
import static com.fliurkevych.pdp.pdpspringcore.util.UserTestUtils.EMAIL_2;
import static com.fliurkevych.pdp.pdpspringcore.util.UserTestUtils.NAME_1;
import static com.fliurkevych.pdp.pdpspringcore.util.UserTestUtils.NAME_2;
import static com.fliurkevych.pdp.pdpspringcore.util.UserTestUtils.USER_ID_1;
import static com.fliurkevych.pdp.pdpspringcore.util.UserTestUtils.USER_ID_2;
import static org.mockito.Mockito.when;

import com.fliurkevych.pdp.pdpspringcore.exception.NotFoundException;
import com.fliurkevych.pdp.pdpspringcore.exception.ValidationException;
import com.fliurkevych.pdp.pdpspringcore.model.User;
import com.fliurkevych.pdp.pdpspringcore.storage.UserStorage;
import com.fliurkevych.pdp.pdpspringcore.util.UserTestUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * @author Oleh Fliurkevych
 */
@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

  public static final Pageable PAGEABLE = PageRequest.of(1, 10);

  @Mock
  private UserStorage userStorage;
  @InjectMocks
  private UserService userService;

  @Test
  public void getUserByIdTest() {
    var user = UserTestUtils.buildUser(USER_ID_1, NAME_1, EMAIL_1);

    when(userStorage.getUserById(USER_ID_1)).thenReturn(Optional.of(user));

    var result = userService.getUserById(USER_ID_1);
    Assertions.assertNotNull(result);
    Assertions.assertEquals(USER_ID_1, result.getId());
  }

  @Test
  public void getUserByIdShouldThrowNotFoundExceptionTest() {
    when(userStorage.getUserById(USER_ID_1)).thenReturn(Optional.empty());

    Assertions.assertThrows(NotFoundException.class, () -> userService.getUserById(USER_ID_1));
  }

  @Test
  public void getUserByEmailTest() {
    var user = UserTestUtils.buildUser(USER_ID_1, NAME_1, EMAIL_1);

    when(userStorage.getUserByEmail(EMAIL_1)).thenReturn(Optional.of(user));

    var result = userService.getUserByEmail(EMAIL_1);
    Assertions.assertNotNull(result);
    Assertions.assertEquals(EMAIL_1, result.getEmail());
  }

  @Test
  public void getUserByEmailShouldThrowNotFoundExceptionTest() {
    when(userStorage.getUserByEmail(EMAIL_1)).thenReturn(Optional.empty());

    Assertions.assertThrows(NotFoundException.class, () -> userService.getUserByEmail(EMAIL_1));
  }

  @Test
  public void getUsersByNameTest() {
    var user1 = UserTestUtils.buildUser(USER_ID_1, NAME_1, EMAIL_1);
    var user2 = UserTestUtils.buildUser(USER_ID_2, NAME_2, EMAIL_2);

    when(userStorage.getUsersByName("name 11", PAGEABLE)).thenReturn(List.of(user1, user2));

    var result = userService.getUsersByName("name 11", PAGEABLE);
    Assertions.assertNotNull(result);
    Assertions.assertEquals(2, result.size());
  }

  @Test
  public void createUserTest() {
    var user1 = UserTestUtils.buildUser(USER_ID_1, NAME_1, EMAIL_1);
    var userDto = UserTestUtils.buildUserDto(USER_ID_1, NAME_1, EMAIL_1);

    when(userStorage.exists(USER_ID_1)).thenReturn(false);
    when(userStorage.save(ArgumentMatchers.any(User.class))).thenReturn(user1);

    var result = userService.create(userDto);
    Assertions.assertNotNull(result);
    Assertions.assertEquals(USER_ID_1, result.getId());
  }

  @Test
  public void createUserShouldThrowValidationTest() {
    var userDto = UserTestUtils.buildUserDto(USER_ID_1, NAME_1, EMAIL_1);
    when(userStorage.exists(USER_ID_1)).thenReturn(true);

    Assertions.assertThrows(ValidationException.class, () -> userService.create(userDto));
  }

  @Test
  public void updateUserTest() {
    var userDto = UserTestUtils.buildUserDto(USER_ID_1, NAME_1, EMAIL_2);
    var userBefore = UserTestUtils.buildUser(USER_ID_1, NAME_1, EMAIL_1);
    var userAfter = UserTestUtils.buildUser(USER_ID_1, NAME_1, EMAIL_2);

    when(userStorage.getUserById(USER_ID_1)).thenReturn(Optional.of(userBefore));
    when(userStorage.update(ArgumentMatchers.any(User.class))).thenReturn(userAfter);

    var result = userService.update(userDto);
    Assertions.assertNotNull(result);
    Assertions.assertEquals(USER_ID_1, result.getId());
    Assertions.assertEquals(EMAIL_2, result.getEmail());
  }

  @Test
  public void updateUserShouldThrowNotFoundTest() {
    var user1 = UserTestUtils.buildUserDto(USER_ID_1, NAME_1, EMAIL_1);
    when(userStorage.getUserById(USER_ID_1)).thenReturn(Optional.empty());

    Assertions.assertThrows(NotFoundException.class, () -> userService.update(user1));
  }

  @Test
  public void deleteUserTest() {
    var user1 = UserTestUtils.buildUser(USER_ID_1, NAME_1, EMAIL_1);

    when(userStorage.getUserById(USER_ID_1)).thenReturn(Optional.of(user1));
    when(userStorage.delete(USER_ID_1)).thenReturn(true);

    var result = userService.delete(USER_ID_1);
    Assertions.assertTrue(result);
  }

  @Test
  public void deleteUserShouldThrowNotFoundTest() {
    when(userStorage.getUserById(USER_ID_1)).thenReturn(Optional.empty());

    Assertions.assertThrows(NotFoundException.class, () -> userService.delete(USER_ID_1));
  }

}
