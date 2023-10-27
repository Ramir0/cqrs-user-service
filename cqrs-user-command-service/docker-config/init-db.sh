#!/bin/bash
set -e

# Start SQL Server
/opt/mssql/bin/sqlservr &

# Wait for SQL Server to start
sleep 15

# Create the database if it doesn't exist
/opt/mssql-tools/bin/sqlcmd -S localhost -U SA -P ${MSSQL_SA_PASSWORD} -Q "CREATE DATABASE [${MSSQL_DB_NAME}]"

# Keep the container running
tail -f /dev/null
