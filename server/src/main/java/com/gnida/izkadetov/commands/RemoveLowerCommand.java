package com.gnida.izkadetov.commands;

import com.gnida.izkadetov.*;

import java.util.ArrayDeque;
import java.util.stream.Collectors;

public class RemoveLowerCommand extends FatherOfCommands{
    public RemoveLowerCommand(Collection collection, TbI_PROSTO_SUPER kryto) {

        super(collection, kryto);
    }

    @Override
    public MessageToClient executeCommand(Command command) {
        ArrayDeque<SpaceMarine> spc = collection.getObjects();
        try {
            int startSize = spc.size();
            if (startSize != 0) {
                spc.removeAll((spc.stream().filter(lil -> lil.getId() < command.getId())).collect(Collectors.toCollection(ArrayDeque::new)));
                collection.uptadeDateChange();
                return new MessageToClient("Удалено " + (startSize - spc.size()) + " элементов");
            } else return new MessageToClient("Коллекция пуста");
        } catch (Exception ex) {
            return new MessageToClient("Возникла ошибка!");
        }

    }
}
