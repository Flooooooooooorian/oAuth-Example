package de.neuefische.backend;

public record MongoUser(
        String id,
        String username,
        String password
) {
}
