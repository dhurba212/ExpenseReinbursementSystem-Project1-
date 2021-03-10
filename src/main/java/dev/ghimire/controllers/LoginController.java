package dev.ghimire.controllers;

import com.google.gson.Gson;
import dev.ghimire.daos.EmployeeDaoHibernateImpl;
import dev.ghimire.daos.ManagerDaoHibernateImpl;
import dev.ghimire.entities.Employee;
import dev.ghimire.entities.Manager;
import dev.ghimire.services.EmployeeService;
import dev.ghimire.services.EmployeeServiceImpl;
import dev.ghimire.services.ManagerService;
import dev.ghimire.services.ManagerServiceImpl;
import dev.ghimire.utils.JwtUtil;
import io.javalin.http.Handler;
import org.apache.log4j.Logger;

import javax.servlet.http.Cookie;

public class LoginController {
    Logger logger = Logger.getLogger(LoginController.class);

    private static EmployeeService employeeService = new EmployeeServiceImpl(new EmployeeDaoHibernateImpl());
    private static ManagerService managerService = new ManagerServiceImpl(new ManagerDaoHibernateImpl());
    public Handler verifyLoginHandler = ctx -> {
        Gson gson = new Gson();
        Manager manager = gson.fromJson(ctx.body(), Manager.class);
        Employee employee = gson.fromJson(ctx.body(),Employee.class);

        Manager manager1 = managerService.getManagerByUsernamePassword(manager.getUsername(),manager.getPassword());
        Employee employee1 = employeeService.getEmployeeByUsernamePassword(employee.getUsername(), employee.getPassword());
        if(manager1 != null)
        {
            String token = JwtUtil.generateToken("manager",manager1.getUsername(),manager1.getManagerId());
            ctx.cookie("Authorization",token);
            logger.info("manager "+ manager1.getUsername()+" is successfully logged in");
            ctx.result(token);
            ctx.status(200);
        }
        else if(employee1 != null)
        {
            String token = JwtUtil.generateToken("employee",employee1.getUsername(),employee1.getEmployeeId());
            ctx.cookie("Authorization",token);
            System.out.println(token);
            logger.info("employee "+employee1.getUsername()+" is successfully logged in.");
            ctx.result(token);
            ctx.status(200);
        }
        else
        {
            logger.error("username "+manager.getUsername()+ " isn't able to log in");
            ctx.result(" Username " +manager.getUsername()+ " wasn't able to log in ");
            ctx.status(401);
        }



    };
}
