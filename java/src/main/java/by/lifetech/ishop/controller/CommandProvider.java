package by.lifetech.ishop.controller;

import by.lifetech.ishop.controller.command.Command;
import by.lifetech.ishop.controller.command.impl.RegistrationCommand;
import by.lifetech.ishop.controller.command.impl.SignInCommand;
import by.lifetech.ishop.controller.command.impl.WrongRequestCommand;

import java.util.HashMap;
import java.util.Map;

final class CommandProvider {
    private final Map<CommandName, Command> repository = new HashMap<>();

    CommandProvider(){
        repository.put(CommandName.SIGNIN, new SignInCommand());
        repository.put(CommandName.REGISTRATION, new RegistrationCommand());
        repository.put(CommandName.WRONG_REQUEST, new WrongRequestCommand());
    }

    Command getCommand(String name){
        CommandName commandName = null;
        Command command = null;

        try {
            commandName = CommandName.valueOf(name.toUpperCase());
            command = repository.get(commandName);
        } catch (IllegalArgumentException | NullPointerException e) {
            // log
            command = repository.get(CommandName.WRONG_REQUEST);
        }
        return command;

    }
}
