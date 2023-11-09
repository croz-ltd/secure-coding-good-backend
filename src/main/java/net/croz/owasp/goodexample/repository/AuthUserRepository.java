package net.croz.owasp.goodexample.repository;

import net.croz.owasp.goodexample.entity.AuthUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class AuthUserRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public AuthUserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Optional<AuthUser> loadUserByUsername(String username) {
        final String query = "SELECT * FROM auth_user WHERE username = ?";

        final AuthUser userDetails =
            jdbcTemplate.queryForObject(query, new BeanPropertyRowMapper<>(AuthUser.class), username);

        return Optional.ofNullable(userDetails);
    }

    public void updateAuthPassword(AuthUser authUser) {
        final String query = "UPDATE auth_user SET password = ? WHERE id = ?";
        jdbcTemplate.update(query, authUser.getPassword(), authUser.getId());
    }

}
