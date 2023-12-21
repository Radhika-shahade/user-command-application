package com.user.command.app.command.impl;

import com.user.command.app.command.Command;
import com.user.command.app.command.validator.CommandValidator;
import com.user.command.app.model.Result;
import com.user.command.app.model.User;
import com.user.command.app.store.InMemoryStore;

import java.util.List;

public class DeleteCommand implements Command, CommandValidator {
    //delete --id userId
    @Override
    public Result execute(String[] attributes) throws Exception {
        List<User> user =InMemoryStore.users;
          if(validate(attributes))
          {
             for(User userList : user)
             {
                 if(userList.getId().equals(attributes[2]))
                 {
                      user.remove(userList);
                     return Result.builder().message("user with"+ userList.getId()+"is deleted").build();
                 }
             }
          }
          return  Result.builder().message("user with id"+attributes[2]+ "is not found").build();
    }

    @Override
    public boolean validate(String[] attributes) throws Exception {
        if(attributes.length != 3) {
            throw new Exception("Please provide name : " +
                    "For ex: \ndelete --id \"userId\" ");
        }
        if(!attributes[1].equals("--id"))
        {
            throw new Exception("Please provide in this format : " +
                    "For ex: \ndelete --id \"userId\" ");
        }
        if(!attributes[0].equals("delete"))
        {
            throw new Exception("Action is not delete!");
        }
        return true;
    }
}
