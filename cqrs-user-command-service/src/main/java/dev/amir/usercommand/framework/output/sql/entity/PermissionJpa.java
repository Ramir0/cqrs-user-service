package dev.amir.usercommand.framework.output.sql.entity;

import dev.amir.usercommand.domain.valueobject.permission.Name;
import dev.amir.usercommand.domain.valueobject.permission.PermissionId;
import dev.amir.usercommand.framework.output.sql.type.PermissionIdType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

@Data
@Entity
@Table(name = "permissions")
@NoArgsConstructor
@AllArgsConstructor
public class PermissionJpa {
    @Id
    @Type(PermissionIdType.class)
    private PermissionId id;
    private Name name;
}
