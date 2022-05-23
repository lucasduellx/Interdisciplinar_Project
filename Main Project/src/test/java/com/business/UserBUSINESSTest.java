package com.business;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserBUSINESSTest {
    @Test
    void testCheckPass() {

        Boolean result = UserBUSINESS.checkPass("TesteSenha123@");

        assertEquals(result, true);

    }

    @Test
    void testCheckUser() throws Exception {

        Boolean result = UserBUSINESS.checkUser("admin");

        assertEquals(result, true);

    }
}
