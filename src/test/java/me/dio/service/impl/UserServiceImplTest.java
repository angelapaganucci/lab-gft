package me.dio.service.impl;

import me.dio.domain.model.Account;
import me.dio.domain.model.User;
import me.dio.domain.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @Test
    void deveBuscarUsuarioPorId() {
        User user = new User();
        user.setId(1L);
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        User result = userService.findById(1L);
        assertEquals(1L, result.getId());
    }

    @Test
    void deveCriarUsuario() {
        User user = new User();
        Account account = new Account();
        user.setId(1L);
        user.setAccount(account);
        account.setNumber("123");
        when(userRepository.save(user)).thenReturn(user);
        User result = userService.create(user);
        assertEquals(1L, result.getId());
    }

    @Test
    void deveApresentarExceptionQuandoNumeroDaContaJaExistir() {
        User user = new User();
        Account account = new Account();
        user.setId(1L);
        user.setAccount(account);
        account.setNumber("123");
        when(userRepository.existsByAccount_Number("123")).thenReturn(true);
        assertThrows(IllegalArgumentException.class, () -> userService.create(user));

    }
}