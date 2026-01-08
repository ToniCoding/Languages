package com.ad02.controller;

import com.ad02.security.entity.Usuario;
import com.ad02.security.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@PreAuthorize("isAuthenticated()")
public class PerfilController {

    private final UsuarioService usuarioService;

    @GetMapping("/perfil")
    public ResponseEntity<Usuario> getPerfil(@AuthenticationPrincipal Usuario usuario) {
        return ResponseEntity.ok(usuario);
    }
}