package com.ideas2it.hrms.controller;

import java.time.LocalDate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
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

import static com.ideas2it.hrms.common.ProjectConstants.MSG_CREATED;

/**
 * Used to perform the different action on client 
 * using client service class 
 * 
 * @version 1
 * @author Balamurugan M
 */
@Controller 
public class ClientController {
    
    private String ADMINMENU = "adminHome";
    private String ERROR_PAGE = "error";
    private ClientService clientService = new ClientServiceImpl();
    
    /**
     * Used to crate the new client 
     * 
     * @param client
     *        Get the client detail
     */
    @PostMapping("client/createClient")
    public ModelAndView createClient(@ModelAttribute("client") Client client, 
            HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView(
                "redirect:" + "/client/displayClient");
        HttpSession session = request.getSession(false);
        try {
            if(clientService.isClientExist(client.getEmailId())) {
                if (clientService.createClient(client)) {
                    session.setAttribute(ClientConstants.LABEL_MESSAGE,
                        ClientConstants.MSG_CREATE_SUCCESS);
                } else {
                    session.setAttribute(ClientConstants.LABEL_MESSAGE, 
                        ClientConstants.MSG_CREATE_FAIL);
                }
            }else {
                session.setAttribute(ClientConstants.LABEL_MESSAGE, 
                    ClientConstants.MSG_ALREADY_EXIST);
            }
            return modelAndView;
        } catch (AppException appException) {
             return new ModelAndView(ERROR_PAGE, ClientConstants.LABEL_MESSAGE, 
                 appException.getMessage());
        }
    }
    
    /**
     * Used to update the client details
     * 
     * @param client
     *        Get the updated client detail 
     */
    @PostMapping("client/updateClient")
    public ModelAndView updateClient(@ModelAttribute("client") Client client,
            ModelMap model) {
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
    
    /**
     * Used to remove the client
     */
    @PostMapping("client/deleteClient")
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
    
    /**
     * Used to display the all clients in the company
     */
    @GetMapping("client/displayClient")
    public ModelAndView displayClients(ModelMap model) {
        try {
            ModelAndView modelAndView 
            = new ModelAndView(ADMINMENU, "command", new Client());
            return modelAndView.addObject(ClientConstants.LABEL_CLIENTS, 
                clientService.displayClients());
        } catch (AppException appException) {
            return new ModelAndView(ERROR_PAGE, ClientConstants.LABEL_MESSAGE, 
                appException.getMessage());
        }
    }
    
    /**
     * Find the particular client using client id 
     */
    @PostMapping("client/searchClient")
    public ModelAndView serchClient(HttpServletRequest request, ModelMap model) {
        try {
            model.addAttribute("client", 
                clientService.searchClient(request.getParameter("id")));
            return displayClients(model);
        } catch (AppException appException) {
             return new ModelAndView(ERROR_PAGE, ClientConstants.LABEL_MESSAGE, 
                 appException.getMessage());
        }
    }
    
    /**
     * Used to calculate the net profit of the client
     */
    @PostMapping("client/netProfit")
    public ModelAndView getNetProfit(HttpServletRequest request, 
            ModelMap model) {
        try {
            Integer clientId = Integer.parseInt(request.getParameter("clientId"));
            LocalDate startDate = LocalDate.parse(request.getParameter("startDate"));
            LocalDate endDate = LocalDate.parse(request.getParameter("endDate"));            
            Client client = clientService.getClientById(clientId);
            Integer netProfit 
                = clientService.calculateNetProfit(client, startDate, endDate);
            model.addAttribute("clientProfit", netProfit);
            model.addAttribute("name", client.getName());
            
        } catch (AppException appException) {
            model.addAttribute(ClientConstants.LABEL_MESSAGE, 
                 appException.getMessage());
        }
        return displayClients(model);
    }
    
    /**
     * Get the all client net profit details 
     */
    @GetMapping("client/revenue")
    public ModelAndView getClientProfits(HttpServletRequest request) {
        try {
            ModelAndView modelAndView = new ModelAndView(ADMINMENU, "profits",
                    clientService.getClientProfits());
            return modelAndView.addObject("names", 
                    clientService.getClientNames());
        } catch (AppException appException) {
             return new ModelAndView(ERROR_PAGE, ClientConstants.LABEL_MESSAGE, 
                 appException.getMessage());
        }
    }
}