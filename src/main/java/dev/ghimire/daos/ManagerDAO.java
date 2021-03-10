package dev.ghimire.daos;

import dev.ghimire.entities.Manager;

import java.util.Set;

public interface ManagerDAO {

    Manager getManagerById(int id);

    Set<Manager> getAllManager();

}
