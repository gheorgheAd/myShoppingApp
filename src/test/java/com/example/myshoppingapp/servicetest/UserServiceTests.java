package com.example.myshoppingapp.servicetest;

import com.example.myshoppingapp.exception.NoUserFoundException;
import com.example.myshoppingapp.model.User;
import com.example.myshoppingapp.repository.UserRepository;
import com.example.myshoppingapp.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTests {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void findAllTest() {
        // given
        User user1 = new User(
                "Angel",
                "Stanciu",
                "angel",
                "adina@gmail.com",
                "$2a$12$s17rpXnWZQxWzGVnz0UttOnLAMuHFizN6ruqnIV7J7GpQV7hKSTiC",
                "Romania, Bucuresti, Strada Gabroveni 030089",
                "+40721058124",
                "ROLE_ADMIN",
                true);

        User user2 = new User(
                "Angel",
                "Stanciu",
                "angel",
                "adina@gmail.com",
                "$2a$12$s17rpXnWZQxWzGVnz0UttOnLAMuHFizN6ruqnIV7J7GpQV7hKSTiC",
                "Romania, Bucuresti, Strada Gabroveni 030089",
                "+40721058124",
                "ROLE_ADMIN",
                true);

        List<User> usersList = List.of(user1, user2);
        when(userRepository.findAll()).thenReturn(usersList);

        // when
        List<User> actualUsers = userService.findAll();

        // then
        assertThat(actualUsers).hasSize(2);
        assertThat(usersList).isEqualTo(actualUsers);
    }

    @Test
    void saveTest() {
        // given
        User userToBeSaved = new User(
                "Angel",
                "Stanciu",
                "angel",
                "adina@gmail.com",
                "$2a$12$s17rpXnWZQxWzGVnz0UttOnLAMuHFizN6ruqnIV7J7GpQV7hKSTiC",
                "Romania, Bucuresti, Strada Gabroveni 030089",
                "+40721058124",
                "ROLE_ADMIN",
                true);

        when(userRepository.save(userToBeSaved)).thenReturn(userToBeSaved);

        // when
        User actualUser = userService.save(userToBeSaved);

        // then
        verify(userRepository).save(userToBeSaved);
        assertThat(userToBeSaved).isEqualTo(actualUser);
    }

    @Test
    void findByIdTest() throws NoUserFoundException {
        // given
        User userToBeFound = new User(
                "Angel",
                "Stanciu",
                "angel",
                "adina@gmail.com",
                "$2a$12$s17rpXnWZQxWzGVnz0UttOnLAMuHFizN6ruqnIV7J7GpQV7hKSTiC",
                "Romania, Bucuresti, Strada Gabroveni 030089",
                "+40721058124",
                "ROLE_ADMIN",
                true);

        when(userRepository.findById(anyInt())).thenReturn(Optional.of(userToBeFound));

        // when
        User actualUser = userService.findById(anyInt());

        // then
        assertThat(actualUser).isEqualTo(userToBeFound);
    }

    @Test
    void findById_ExceptionTest() {
        // given
        when(userRepository.findById(anyInt())).thenReturn(Optional.empty());

        // then
        assertThrows(NoUserFoundException.class,
                () -> userService.findById(anyInt()));
    }

    @Test
    void deleteByIdTest() throws NoUserFoundException {
        User userToBeFound = new User(
                "Angel",
                "Stanciu",
                "angel",
                "adina@gmail.com",
                "$2a$12$s17rpXnWZQxWzGVnz0UttOnLAMuHFizN6ruqnIV7J7GpQV7hKSTiC",
                "Romania, Bucuresti, Strada Gabroveni 030089",
                "+40721058124",
                "ROLE_ADMIN",
                true);

        when(userRepository.findById(anyInt())).thenReturn(Optional.of(userToBeFound));

        userService.deleteById(1);

        verify(userRepository, times(1)).deleteById(1);
    }

    @Test
    void deleteUserById_ExceptionTest() {
        // given
        when(userRepository.findById(anyInt())).thenReturn(Optional.empty());

        // then
        assertThrows(NoUserFoundException.class,
                () -> userService.deleteById(anyInt()));
    }
}
