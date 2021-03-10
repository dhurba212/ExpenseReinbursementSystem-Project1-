package dev.ghimire.services;

import dev.ghimire.daos.ManagerDAO;
import dev.ghimire.entities.Manager;

import java.util.Set;

public class ManagerServiceImpl implements ManagerService{

    private  static ManagerDAO managerDAO;
    public ManagerServiceImpl(ManagerDAO managerDAO)
    {
        this.managerDAO = managerDAO;
    }
    @Override
    public Manager getManagerByUsernamePassword(String username,String password) {
        Set<Manager> managers = managerDAO.getAllManager();
        Manager manager = null;
        for(Manager m: managers)
        {
            if(m.getUsername().equals(username) && m.getPassword().equals(password))
            {
                manager=m;
            }
        }
        return manager;
    }

    @Override
    public Manager getManagerById(int id) {
        Manager manager = managerDAO.getManagerById(id);

        return manager;
    }
}
