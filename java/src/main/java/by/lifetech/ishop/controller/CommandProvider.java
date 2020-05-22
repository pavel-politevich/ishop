package by.lifetech.ishop.controller;

import by.lifetech.ishop.controller.command.Command;
import by.lifetech.ishop.controller.command.impl.*;

import java.util.HashMap;
import java.util.Map;

final class CommandProvider {
    private final Map<CommandName, Command> repository = new HashMap<>();

    CommandProvider(){
        repository.put(CommandName.SIGNIN, new SignInCommand());
        repository.put(CommandName.SIGNOUT, new SignOutCommand());
        repository.put(CommandName.REGISTRATION, new RegistrationCommand());
        repository.put(CommandName.WRONG_REQUEST, new WrongRequestCommand());
        repository.put(CommandName.CHANGELOCALE, new ChangeLocaleCommand());
        repository.put(CommandName.GO_TO_LOGIN, new GoToLoginPageCommand());
        repository.put(CommandName.GO_TO_REGISTER, new GoToRegisterPageCommand());
        repository.put(CommandName.GO_TO_MAIN, new GoToMainPageCommand());
        repository.put(CommandName.GO_TO_CATALOG, new GoToCatalogPageCommand());
        repository.put(CommandName.GET_ITEMS, new GetItemsCommand());
        repository.put(CommandName.SHOW_ITEM, new ShowItemCommand());
        repository.put(CommandName.ADD_TO_CART, new AddToCartCommand());
        repository.put(CommandName.DEL_FROM_CART, new DelFromCartCommand());
        repository.put(CommandName.GET_CART, new GetCartCommand());
        repository.put(CommandName.ADD_REVIEW, new AddReviewCommand());
        repository.put(CommandName.CONFIRM_ORDER, new ConfirmOrderCommand());
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
