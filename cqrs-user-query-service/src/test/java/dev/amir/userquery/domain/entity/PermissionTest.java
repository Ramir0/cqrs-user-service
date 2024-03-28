package dev.amir.userquery.domain.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PermissionTest {

    @Test
    void testPermissionCreation() {
        Permission permission = new Permission();
        permission.setId("d64a9c98-906f-11ee-b9d1-0242ac120002");
        permission.setName("View Users");

        assertEquals("d64a9c98-906f-11ee-b9d1-0242ac120002", permission.getId());
        assertEquals("View Users", permission.getName());
    }
}