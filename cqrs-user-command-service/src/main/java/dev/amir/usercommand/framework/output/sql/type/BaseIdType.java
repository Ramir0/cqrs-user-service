package dev.amir.usercommand.framework.output.sql.type;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.type.SqlTypes;
import org.hibernate.usertype.UserType;

abstract class BaseIdType<T extends Serializable> implements UserType<T> {
    @Override
    public int getSqlType() {
        return SqlTypes.VARCHAR;
    }

    @Override
    public boolean equals(T id1, T id2) {
        return Objects.equals(id1, id2);
    }

    @Override
    public int hashCode(T id) {
        return id.hashCode();
    }

    @Override
    public T nullSafeGet(ResultSet resultSet, int position, SharedSessionContractImplementor session, Object owner)
            throws SQLException {
        String value = resultSet.getString(position);
        return resultSet.wasNull() ? null : newInstance(value);
    }

    @Override
    public void nullSafeSet(PreparedStatement statement, T value, int index, SharedSessionContractImplementor session)
            throws SQLException {
        if (value == null) {
            statement.setNull(index, SqlTypes.VARCHAR);
        } else {
            statement.setString(index, value.toString());
        }
    }

    @Override
    public T deepCopy(T value) {
        return value;
    }

    @Override
    public boolean isMutable() {
        return false;
    }

    @Override
    public Serializable disassemble(T value) {
        return value;
    }

    abstract T newInstance(String value);
}
