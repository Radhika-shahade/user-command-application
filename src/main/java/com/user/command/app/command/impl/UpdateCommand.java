package com.user.command.app.command.impl;

import com.user.command.app.command.Command;
import com.user.command.app.command.validator.CommandValidator;
import com.user.command.app.model.Result;

public class UpdateCommand implements CommandValidator, Command {
    //update -id userId -n radhika -p 858476 -a nagpur -e rds@gmail.com
    @Override
    public Result execute(String[] attributes) throws Exception {
        if (validate(attributes)) {

        }
    }

    @Override
    public boolean validate(String[] attributes) throws Exception {
//            if (attributes.length != 9) {
//                throw new Exception("Please provide name : " +
//                        "For ex: \nupdate --id \"userId\" ");
//            }
        if (!attributes[0].equals("update")) {
            throw new Exception("Action is not update!");
        }
        if (!attributes[1].equals("--id")) {
            throw new Exception("write correct command and name");
        }
       return true;
    }

    private boolean validAttributes(String[] attributes,String attrName) {
        boolean isValid = true;
        int i = 3;
        while (isValid && i < attributes.length) {
            String attributeName = attributes[i];
            isValid = validAttributes(attributeName);
            i = i + 2;
            return isValid;
        }
        return isValid;
private boolean is
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
