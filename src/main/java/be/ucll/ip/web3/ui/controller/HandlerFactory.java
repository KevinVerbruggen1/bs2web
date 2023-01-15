package be.ucll.ip.web3.ui.controller;

import be.ucll.ip.web3.domain.service.AppService;
import be.ucll.ip.web3.domain.service.UserServiceDBSQL;
import be.ucll.ip.web3.domain.service.UserServiceInMemory;

public class HandlerFactory {

    public RequestHandler getHandler(String command, AppService service) {
        RequestHandler handler = null;
        try {
            Class handlerClass = Class.forName("be.ucll.ip.web3.ui.controller." + command);
            Object objectHandler = handlerClass.getConstructor().newInstance();
            handler = (RequestHandler) objectHandler;
            handler.setService(service);
        } catch (Exception e) {
            throw new RuntimeException("Deze pagina bestaat niet!");
        }
        return handler;
    }
}
