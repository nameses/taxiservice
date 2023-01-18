package service;

import DAO.ClientDAO;
import models.entity.Client;

public class ClientService {
    private final ClientDAO clientDAO = new ClientDAO();

    public Boolean register(Client client){
        try{
            return clientDAO.insert(client);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
