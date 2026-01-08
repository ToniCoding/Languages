package com.ad02.security.service;

import com.ad02.security.dto.LoginRequest;
import com.ad02.security.dto.LoginResponse;
import com.ad02.security.entity.Usuario;
import com.ad02.security.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public LoginResponse register(LoginRequest request) {
        if (usuarioRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("El usuario con este email ya existe");
        }

        Usuario usuario = new Usuario();
        usuario.setEmail(request.getEmail());
        usuario.setPassword(passwordEncoder.encode(request.getPassword()));
        usuario.setRole("ROLE_USER");

        if (request.getNombre() != null) {
            usuario.setNombre(request.getNombre());
        }
        if (request.getApellidos() != null) {
            usuario.setApellidos(request.getApellidos());
        }

        usuario.setEnabled(true);

        usuarioRepository.save(usuario);

        String token = jwtService.generateToken(usuario);

        return LoginResponse.builder()
                .token(token)
                .email(usuario.getEmail())
                .nombre(usuario.getNombre())
                .apellidos(usuario.getApellidos())
                .role(usuario.getRole())
                .message("Usuario registrado exitosamente")
                .build();
    }

    public LoginResponse login(LoginRequest request) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);

            Usuario usuario = usuarioRepository.findByEmail(request.getEmail())
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

            String token = jwtService.generateToken(usuario);

            return LoginResponse.builder()
                    .token(token)
                    .email(usuario.getEmail())
                    .nombre(usuario.getNombre())
                    .apellidos(usuario.getApellidos())
                    .role(usuario.getRole())
                    .message("Login exitoso")
                    .build();

        } catch (Exception e) {
            throw new RuntimeException("Error en el login: " + e.getMessage());
        }
    }
}