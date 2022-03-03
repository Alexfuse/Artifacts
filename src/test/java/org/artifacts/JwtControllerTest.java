package org.artifacts;

import static org.assertj.core.api.Assertions.assertThat;

import org.artifacts.controller.JwtAuthenticationController;
import org.artifacts.entity.DAOUser;
import org.artifacts.entity.UserDTO;
import org.artifacts.entity.jtw.JwtRequest;
import org.artifacts.entity.jtw.JwtResponse;
import org.artifacts.services.JwtUserDetailsService;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class JwtControllerTest {
    @InjectMocks
    JwtAuthenticationController jwtAuthenticationController;

    @Mock
    JwtUserDetailsService jwtUserDetailsService;

    @Ignore("not ready yet")
    @Test
    public void createAuthenticationTokenTest() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        when(jwtUserDetailsService.loadUserByUsername("test"));

        JwtRequest jwtRequest = new JwtRequest("test","test");

        ResponseEntity responseLoadUserByUsername= jwtAuthenticationController.createAuthenticationToken(jwtRequest);

        assertThat(responseLoadUserByUsername.getStatusCodeValue()).isBetween(200,300);
        assertThat(responseLoadUserByUsername.getBody()).isNotNull().isExactlyInstanceOf(JwtResponse.class).hasFieldOrProperty("jwttoken");
    }

    @Test
    public void saveUserTest() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        when(jwtUserDetailsService.save(any(UserDTO.class))).thenReturn(new DAOUser());

        UserDTO userDTO = new UserDTO();
        userDTO.setUsername("test");
        userDTO.setPassword("test");

        ResponseEntity response = jwtAuthenticationController.saveUser(userDTO);

        assertThat(response.getStatusCodeValue()).isBetween(200,300);
        assertThat(response.getBody()).isNotNull();
    }
}
