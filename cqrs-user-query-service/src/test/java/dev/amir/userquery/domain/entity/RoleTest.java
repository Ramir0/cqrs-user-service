package dev.amir.userquery.domain.entity;

import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RoleTest {

    @Test
    void testRoleCreation() {
        // Create permissions
        Permission permission1 = new Permission();
        Permission permission2 = new Permission();
        permission1.setId("d64a9c98-906f-11ee-b9d1-0242ac120002");
        permission1.setName("View Users");
        permission2.setId("f54bdf12-906f-11ee-b9d1-0242ac120002");
        permission2.setName("Create Users");


        Set<Permission> permissions = new HashSet<>();
        permissions.add(permission1);
        permissions.add(permission2);

        Role role = new Role();
        role.setId("9abef656-906f-11ee-b9d1-0242ac120002");
        role.setName("Administrator");
        role.setPermissions(permissions);

        assertEquals("9abef656-906f-11ee-b9d1-0242ac120002", role.getId());
        assertEquals("Administrator", role.getName());
        assertEquals("d64a9c98-906f-11ee-b9d1-0242ac120002", permission1.getId());
        assertEquals("View Users", permission1.getName());
        assertEquals("f54bdf12-906f-11ee-b9d1-0242ac120002", permission2.getId());
        assertEquals("Create Users", permission2.getName());
        assertEquals(2, role.getPermissions().size());
    }
}