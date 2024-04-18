package ru.sobse.userservice.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.jwt;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class ControllerIT {
    @Autowired
    MockMvc mockMvc;
    JwtGrantedAuthoritiesConverter grantedAuthoritiesConverter;

    @BeforeEach
    public void beforeEach() {
        grantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
        grantedAuthoritiesConverter.setAuthorityPrefix("");
        grantedAuthoritiesConverter.setAuthoritiesClaimName("groups");
    }

    @Test
    @Sql("/sql/users_roles.sql")
    public void getUsersReturnsUsersList() throws Exception{
        //arrange
        var requestBuilder = MockMvcRequestBuilders.get("/admin/get-users")
                .with(jwt().jwt(builder -> builder.claim("groups", "ROLE_ADMIN"))
                        .authorities(grantedAuthoritiesConverter));

        //act
        mockMvc.perform(requestBuilder)
                //assert
                .andDo(print())
                .andExpectAll(status().isOk(),
                        content().contentType(MediaType.APPLICATION_JSON),
                        content().json("""
                        [
                            {
                                "name": "user"
                            },
                            {
                                "name": "admin"
                            },
                            {
                                "name": "usertest"
                            }
                        ]
                        """));
    }

    @Test
    public void adminOkTest() throws Exception{
        //arrange
        var requestBuilder = MockMvcRequestBuilders.get("/admin/ping")
                .with(jwt().jwt(builder -> builder.claim("groups", "ROLE_ADMIN"))
                        .authorities(grantedAuthoritiesConverter));
        //act
        mockMvc.perform(requestBuilder)
                //assert
                .andDo(print())
                .andExpectAll(status().isOk(),
                        content().string("ADMIN OK"));
    }

    @Test
    public void userOKTest() throws Exception{
        //arrange
        var requestBuilder = MockMvcRequestBuilders.get("/user/ping")
                .with(jwt().jwt(builder -> builder.claim("groups", "ROLE_USER"))
                        .authorities(grantedAuthoritiesConverter));

        //act
        mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpectAll(status().isOk(),
                        content().string("USER OK"));

    }

    @Test
    public void roleAdminNotAuthorized() throws Exception {
        //arrange
        var requestBuilder = MockMvcRequestBuilders.get("/admin/ping")
                .with(jwt().jwt(builder -> builder.claim("groups", ""))
                        .authorities(grantedAuthoritiesConverter));

        //act
        mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpectAll(status().isForbidden());
    }

    @Test
    public void roleUserNotAuthorized() throws Exception {
        //arrange
        var requestBuilder = MockMvcRequestBuilders.get("/user/ping")
                .with(jwt().jwt(builder -> builder.claim("groups", ""))
                        .authorities(grantedAuthoritiesConverter));

        //act
        mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpectAll(status().isForbidden());
    }
}



