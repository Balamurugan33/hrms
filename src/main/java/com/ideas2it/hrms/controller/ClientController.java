package com.ideas2it.hrms.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ideas2it.hrms.common.ClientConstants;
import com.ideas2it.hrms.exception.AppException;
import com.ideas2it.hrms.model.Client;
import com.ideas2it.hrms.service.ClientService;
import com.ideas2it.hrms.service.impl.ClientServiceImpl;

/**
 * Used to perform the different action on client 
 * using client service class 
 * 
 * @version 1
 * @author Balamurugan M
 */
public class ClientController {
    
    private String CLIENT_VIEW = "clientDisplay";;
    private String ADMINMENU = "adminMenu";
    private String CLIENTCREATE = "clientCreateForm";
    private String ERROR_PAGE = "error";
    private ClientService clientService = new ClientServiceImpl();
    
    @PostMapping("/createClient")
    public ModelAndView createClient(@ModelAttribute("client") Client client) {
        ModelMap model = null;
        try {
            if(!clientService.isClientExist(client.getEmailId())) {
                if (clientService.createClient(client)) {
                    model.addAttribute(ClientConstants.LABEL_MESSAGE,
                        ClientConstants.MSG_CREATE_SUCCESS);
                } else {
                    model.addAttribute(ClientConstants.LABEL_MESSAGE, 
                        ClientConstants.MSG_CREATE_FAIL);
                }
            }
            model.addAttribute(ClientConstants.LABEL_MESSAGE, 
                ClientConstants.MSG_ALREADY_EXIST);
            return displayClients(model);
        } catch (AppException appException) {
             return new ModelAndView(ERROR_PAGE, ClientConstants.LABEL_MESSAGE, 
                 appException.getMessage());
        }
    }
    
    @PostMapping("/updateClient")
    public ModelAndView updateClient(@ModelAttribute("client") Client client) {
        ModelMap model = null;
        try {
            if (clientService.updateClient(client)) {
                model.addAttribute(ClientConstants.LABEL_MESSAGE,
                    ClientConstants.MSG_UPDATE_SUCCESS);
            } else {
                model.addAttribute(ClientConstants.LABEL_MESSAGE, 
                    ClientConstants.MSG_UPDATE_FAIL);
            }
            return displayClients(model);
        } catch (AppException appException) {
             return new ModelAndView(ERROR_PAGE, ClientConstants.LABEL_MESSAGE, 
                 appException.getMessage());
        }
    }
    
    @PostMapping("/deleteClient")
    public ModelAndView deleteClient(HttpServletRequest request, 
            ModelMap model) {
        try {
            if (clientService.deleteClient(Integer.parseInt(
                    request.getParameter("id")))) {
                model.addAttribute(ClientConstants.LABEL_MESSAGE, 
                    ClientConstants.MSG_DELETE_SUCCESS);
            } else {
                model.addAttribute(ClientConstants.LABEL_MESSAGE,
                    ClientConstants.MSG_DELETE_FAIL);
            }
            return displayClients(model);
        } catch (AppException appException) {
             return new ModelAndView(ERROR_PAGE, ClientConstants.LABEL_MESSAGE, 
                 appException.getMessage());
        }
    }
    
    @GetMapping("/displayClient")
    public ModelAndView displayClients(ModelMap model) {
        try {
            return new ModelAndView(CLIENT_VIEW, ClientConstants.LABEL_CLIENTS, 
                clientService.displayClients());
        } catch (AppException appException) {
            return new ModelAndView(ERROR_PAGE, ClientConstants.LABEL_MESSAGE, 
                appException.getMessage());
        }
    }
}