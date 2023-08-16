package com.serasa.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.serasa.user.application.service.UserService;
import com.serasa.user.domain.exception.UserInvalidException;
import com.serasa.user.domain.exception.UserNotFoundException;
import com.serasa.user.domain.model.User;
import com.serasa.user.domain.repository.JpaUserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@SpringBootTest
class UserServiceTest {

    @Mock
    private JpaUserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAll() {
        List<User> userList = new ArrayList<>();
        userList.add(new User(1L, "John"));
        userList.add(new User(2L, "Jane"));

        when(userRepository.findAll()).thenReturn(userList);

        List<User> result = userService.findAll();

        assertEquals(2, result.size());
        assertEquals("John", result.get(0).getName());
        assertEquals("Jane", result.get(1).getName());

        verify(userRepository, times(1)).findAll();
        verifyNoMoreInteractions(userRepository);
    }

    @Test
    void testFindById() {
        Long userId = 1L;
        User user = new User(userId, "John");

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        User result = userService.findById(userId);

        assertNotNull(result);
        assertEquals(userId, result.getId());
        assertEquals("John", result.getName());

        verify(userRepository, times(1)).findById(userId);
        verifyNoMoreInteractions(userRepository);
    }

    @Test
    void testFindById_UserNotFound() {
        Long userId = 1L;

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> userService.findById(userId));

        verify(userRepository, times(1)).findById(userId);
        verifyNoMoreInteractions(userRepository);
    }

    @Test
    void testSave() {
        User user = new User(1L, "John");

        when(userRepository.save(user)).thenReturn(user);

        User result = userService.save(user);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("John", result.getName());

        verify(userRepository, times(1)).save(user);
        verifyNoMoreInteractions(userRepository);
    }

    @Test
    void testSave_UserInvalidException() {
        User user = new User(1L, null);

        when(userRepository.save(user)).thenThrow(new RuntimeException());

        assertThrows(UserInvalidException.class, () -> userService.save(user));

        verify(userRepository, times(1)).save(user);
        verifyNoMoreInteractions(userRepository);
    }

    @Test
    void testUpdateUser() {
        Long userId = 1L;
        User existingUser = new User(userId, "John");
        User updatedUser = new User(userId, "Jane");

        when(userRepository.findById(userId)).thenReturn(Optional.of(existingUser));
        when(userRepository.save(updatedUser)).thenReturn(updatedUser);

        User result = userService.updateUser(userId, updatedUser);

        assertNotNull(result);
        assertEquals(userId, result.getId());
        assertEquals("Jane", result.getName());

        verify(userRepository, times(1)).findById(userId);
        verify(userRepository, times(1)).save(updatedUser);
        verifyNoMoreInteractions(userRepository);
    }

    @Test
    void testUpdateUser_UserNotFound() {
        Long userId = 1L;
        User updatedUser = new User(userId, "Jane");

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> userService.updateUser(userId, updatedUser));

        verify(userRepository, times(1)).findById(userId);
        verifyNoMoreInteractions(userRepository);
    }

    @Test
    void testDeleteUser() {
        Long userId = 1L;

        when(userRepository.findById(userId)).thenReturn(Optional.of(new User(userId, "John")));

        assertDoesNotThrow(() -> userService.deleteUser(userId));

        verify(userRepository, times(1)).findById(userId);
        verify(userRepository, times(1)).deleteById(userId);
        verifyNoMoreInteractions(userRepository);
    }

    @Test
    void testDeleteUser_UserNotFound() {
        Long userId = 1L;

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> userService.deleteUser(userId));

        verify(userRepository, times(1)).findById(userId);
        verifyNoMoreInteractions(userRepository);
    }
}