package com.acme.animals.command;

import com.acme.animals.client.AnimalClient;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
public class AnimalCommand {
    @Autowired
    AnimalClient animalClient;

    @ShellMethod("Retrieve information about specified animal")
    public String animal(@ShellOption String animal) throws JsonProcessingException {
        return animalClient.getInformation(animal);
    }
}
