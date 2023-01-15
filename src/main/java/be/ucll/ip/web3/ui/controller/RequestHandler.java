package be.ucll.ip.web3.ui.controller;

import be.ucll.ip.web3.domain.service.AppService;
import be.ucll.ip.web3.domain.service.UserServiceDBSQL;
import be.ucll.ip.web3.domain.service.UserServiceInMemory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class RequestHandler {
    protected AppService service;

    public abstract String handleRequest (HttpServletRequest request, HttpServletResponse response);

    public AppService getService() {
        return service;
    }

    public void setService(AppService service) {
        this.service = service;
    }
}

