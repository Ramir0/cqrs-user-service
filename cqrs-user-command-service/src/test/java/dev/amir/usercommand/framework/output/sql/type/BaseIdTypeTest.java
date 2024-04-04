package dev.amir.usercommand.framework.output.sql.type;

import dev.amir.usercommand.domain.valueobject.user.UserId;
import dev.amir.usercommand.util.RandomObject;
import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;
import org.hibernate.type.SqlTypes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

@ExtendWith(MockitoExtension.class)
class BaseIdTypeTest {
    @Spy
    private BaseIdType<Serializable> underTest;

    @BeforeEach
    void setUp() {
        openMocks(this);
    }

    @Test
    void test_GetSqlType_ShouldReturnVarCharNumber() {
        assertEquals(SqlTypes.VARCHAR, underTest.getSqlType());
    }

    @Test
    void test_Equals_ShouldReturnTrue() {
        UUID uuid = RandomObject.nextObject(UUID.class);
        Serializable serializable1 = new UserId(uuid);
        Serializable serializable2 = new UserId(uuid);

        assertTrue(underTest.equals(serializable1, serializable2));
    }

    @Test
    void test_Equals_ShouldReturnFalse() {
        Serializable serializable1 = new UserId();
        Serializable serializable2 = new UserId();

        assertFalse(underTest.equals(serializable1, serializable2));
    }

    @Test
    void test_HashCode_ShouldCallArgumentMethod() {
        Serializable serializable = new UserId();
        assertEquals(serializable.hashCode(), underTest.hashCode(serializable));
    }

    @Test
    void test_NullSafeGet_WhenValueIsNotNull() throws SQLException {
        ResultSet resultSet = mock(ResultSet.class);
        String uuidAsString = UUID.randomUUID().toString();
        when(resultSet.getString(anyInt())).thenReturn(uuidAsString);
        when(resultSet.wasNull()).thenReturn(false);
        Serializable serializable = new UserId(uuidAsString);
        when(underTest.newInstance(anyString())).thenReturn(serializable);

        Serializable actual = underTest.nullSafeGet(resultSet, 1, null, null);

        assertEquals(serializable, actual);
        verify(resultSet).getString(eq(1));
        verify(resultSet).wasNull();
        verify(underTest).newInstance(eq(uuidAsString));
    }

    @Test
    void test_NullSafeGet_WhenValueIsNull() throws SQLException {
        ResultSet resultSet = mock(ResultSet.class);
        String uuidAsString = UUID.randomUUID().toString();
        when(resultSet.getString(anyInt())).thenReturn(uuidAsString);
        when(resultSet.wasNull()).thenReturn(true);

        assertNull(underTest.nullSafeGet(resultSet, 1, null, null));

        verify(resultSet).getString(eq(1));
        verify(resultSet).wasNull();
        verify(underTest, never()).newInstance(any());
    }

    @Test
    void test_NullSafeSet_WhenValueIsNull() throws SQLException {
        PreparedStatement statement = mock(PreparedStatement.class);
        doNothing().when(statement).setNull(anyInt(), anyInt());

        underTest.nullSafeSet(statement, null, 1, null);

        verify(statement).setNull(eq(1), eq(SqlTypes.VARCHAR));
    }

    @Test
    void test_NullSafeSet_WhenValueIsNotNull() throws SQLException {
        PreparedStatement statement = mock(PreparedStatement.class);
        doNothing().when(statement).setString(anyInt(), anyString());
        Serializable serializable = new UserId();

        underTest.nullSafeSet(statement, serializable, 1, null);

        verify(statement).setString(eq(1), eq(serializable.toString()));
    }

    @Test
    void test_DeepCopy_ShouldReturnSameObject() {
        Serializable serializable = new UserId();

        Serializable actual = underTest.deepCopy(serializable);

        assertEquals(serializable, actual);
    }

    @Test
    void test_IsMutable_ShouldReturnFalse() {
        assertFalse(underTest.isMutable());
    }

    @Test
    void test_Disassemble_ShouldReturnSameObject() {
        Serializable serializable = new UserId();

        Serializable actual = underTest.disassemble(serializable);

        assertEquals(serializable, actual);
    }
}
