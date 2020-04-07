package com.example.coronastats.telegram.bot.dto;

import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.telegram.telegrambots.meta.api.objects.Update;

@Data @AllArgsConstructor @NoArgsConstructor @Builder(toBuilder = true)
public class Action {

    private String name;
    private String id;
    private String value;
    private String command;

    public static ActionBuilder builder() {
        return new ActionBuilder();
    }

    public static class ActionBuilder {

        private Action action = new Action();

        public ActionBuilder name(String name) {
            action.setName(name);
            return this;
        }

        public ActionBuilder value(String name) {
            action.setValue(name);
            return this;
        }


        public Action build() {
            return action;
        }

        public Action build(Update update) {
            String data = update.getCallbackQuery().getData();
            if (data == null) {
                return null;
            }
            Action action = Action.builder().name(update.getCallbackQuery().getData()).build();

            if (action == null) {
                return Action.builder().build();
            }
            return action;
        }
    }
}