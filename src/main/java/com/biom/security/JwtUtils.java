package com.biom.security;

import com.biom.entity.Role;
import com.biom.security.context.AuthContextImpl;
import io.jsonwebtoken.Claims;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class JwtUtils {
    public static AuthContextImpl generate(Claims claims) {
        AuthContextImpl authContext = new AuthContextImpl();
        authContext.setEmail(claims.getSubject());
        authContext.setRoles(getRoles(claims));
        return authContext;
    }

    private static Set<Role> getRoles(Claims claims) {
        List<Role> roles = claims.get("roles", List.class);
        return new HashSet<>(roles);
    }
}
