package com.user.command.app.command.impl;

import com.user.command.app.command.Command;
import com.user.command.app.command.validator.CommandValidator;
import com.user.command.app.model.Result;
import com.user.command.app.model.User;
import com.user.command.app.store.InMemoryStore;

import java.util.ArrayList;
import java.util.List;

public class ReadCommand implements Command, CommandValidator {
    //create -n radhika -p 858476 -a nagpur -e rds@gmail.com
    //read -n radhika
    List<User> list = new ArrayList<>();

    @Override
    public Result execute(String[] attributes) throws Exception {
        String attributeName = attributes[1];
        switch (attributeName){
            case "-n":
                return getUserByName(attributes);
            case "--id" :
                return getUserById(attributes);
            case "--all":
                return getAllUser(attributes);
            default:
                throw new Exception("please write correct attribute for finding with name:" + "read -n yourName"
                +"for finding with name: "+ "read --id UserId"+ "to get all user: "+ "--all ");
        }


    }
    private Result getUserById(String [] attribute) throws Exception {
        if(validate(attribute))
        {
            for (User userDetail : InMemoryStore.users) {
                if (userDetail.getId().equals(attribute[2])) {
                    list.add(userDetail);
                    return Result.builder().users(list).message("success").build();
                }
            }
        }
        return Result.builder().users(null).message("unable to find user with this id").build();
    }
    private Result getAllUser(String[]attribute) throws Exception {
        if(attribute.length==2 && attribute[0].equals("read")) {
            List<User>userList=InMemoryStore.users;
            return Result.builder().users(userList).message("Success").build();
        }
        return Result.builder().users(null).message("something went wrong").build();

    }

    private Result getUserByName(String[] attribute) throws Exception {
        if (validate(attribute)) {
            List<User> users = InMemoryStore.users;
            for (User userDetail : users) {
                if (userDetail.getName().equals(attribute[2])) {
                    list.add(userDetail);
                    return Result.builder().users(list).message("success").build();
                }
            }

        }

        return Result.builder().users(null).message("unable to find user with this name").build();
    }

    @Override
    public boolean validate(String[] attributes) throws Exception {
        if (attributes.length != 3) {
            throw new Exception("Please provide name : " +
                    "For ex: \nread -n \"Ramesh\" ");
        }
        if (!attributes[0].equals("read")) {
            throw new Exception("Action is not read!");
        }
//        if (!attributes[1].equals("-n")) {
//            throw new Exception("write correct command and name");

        return true;
    }


}
