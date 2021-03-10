package dev.ghimire.services;

import dev.ghimire.entities.Manager;

public interface ManagerService {

    Manager getManagerByUsernamePassword(String username, String password);
    Manager getManagerById(int id);

}
