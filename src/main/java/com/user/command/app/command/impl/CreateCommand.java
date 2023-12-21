package com.user.command.app.command.impl;

import com.user.command.app.command.Command;
import com.user.command.app.command.validator.CommandValidator;
import com.user.command.app.model.Result;
import com.user.command.app.model.User;
import com.user.command.app.store.InMemoryStore;

import java.util.Random;
import java.util.UUID;

public class CreateCommand implements Command, CommandValidator {
    //create -n radhika -p 858476 -a nagpur -e rds@gmail.com
    public Result execute(String[] attributes) throws Exception {
        if (validate(attributes)) {
//              Start processing
            User user = User.builder().id(UUID.randomUUID().toString()).build();
            for (int i = 1; i < attributes.length; i = i + 2) {
                String attrName = attributes[i];
                setAttributeValue(user, attrName, attributes[i + 1]);
            }
            InMemoryStore.users.add(user);
            return Result.builder().message("SUCCESS").users(InMemoryStore.users).build();
        }
        return Result.builder().message("Invalid command arguments").build();
    }

    private void setAttributeValue(User user, String attrName, String value) throws Exception {
        switch (attrName) {
            case "-n":
                user.setName(value);
                break;
            case "-a":
                user.setAddress(value);
                break;
            case "-p":
                user.setPhone(Long.valueOf(value));
                break;
            case "-e":
                user.setEmailId(value);
                break;
            default:
                throw new Exception("Invalid command attribute format!");
        }
    }


    public boolean validate(String[] attributes) throws Exception {
        if (attributes.length != 9) {
            throw new Exception("Please provide all attributes: " +
                    "For ex: \ncreate -n \"Ramesh\" -p 8989 -a " +
                    "\"Nagpur Kdk college cha maaage\" -e \"iamcoder@gmail.com\"");
        }
        if (!attributes[0].equals("create")) {
            throw new Exception("Action is not create!");
        }
        boolean isValid = true;
        int i = 1;
        while (isValid && i < attributes.length) {
            String attrName = attributes[i];
            isValid = validAttributes(attrName);
            i = i + 2;
        }
        return isValid;
    }

    private boolean validAttributes(String attrName) {
        switch (attrName) {
            case "-n":
                return true;
            case "-p":
                return true;
            case "-a":
                return true;
            case "-e":
                return true;
            default:
                return false;
        }
    }


}
