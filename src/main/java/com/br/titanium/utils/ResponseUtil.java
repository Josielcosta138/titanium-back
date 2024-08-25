package com.br.titanium.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ResponseUtil {

    public ResponseUtil(List<String> messages) {
    }

    public static Map<String, Object> responseMap(Object mensagem){
        Map<String, Object> response = new HashMap<>();
        response.put("messages", mensagem);

        return response;
    }

    public static String formatMessages(List<String> messages) {
        return messages.stream().collect(Collectors.joining("; "));
    }

}
