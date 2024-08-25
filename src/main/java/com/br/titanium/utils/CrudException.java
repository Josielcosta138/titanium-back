package com.br.titanium.utils;

import java.util.ArrayList;
import java.util.List;

public class CrudException extends Exception{

    private List<String> messageList = new ArrayList<>();

    public CrudException(String mensagem){
        super(mensagem);
    }

    public CrudException(List<String> mensagens){
        this.messageList = mensagens;
    }


    public List<String> getMessages(){
        if (messageList.isEmpty()){
            messageList.add(super.getMessage());
        }
        return messageList;
    }

    public String getMessage(){
        if (messageList.isEmpty()) {
            return super.getMessage();
        }
        return messageList.toString();
    }

}
