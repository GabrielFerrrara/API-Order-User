# Use the official MySQL 8 image as the base image
FROM mysql:8

# Environment variables for MySQL configuration
ENV MYSQL_DATABASE db_microsservicos
ENV MYSQL_USER admin
ENV MYSQL_PASSWORD pass
ENV MYSQL_ROOT_PASSWORD pass

# Copy custom SQL scripts to initialize the database
COPY init.sql /docker-entrypoint-initdb.d/

# Expose the MySQL port
EXPOSE 3306