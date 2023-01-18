package models.converters;

import models.DTO.ClientDTO;
import models.DTO.RouteDTO;
import models.entity.Client;
import models.entity.Route;
import models.view.ClientView;
import models.view.RouteView;

public class ClientConverter {
    public static Client toEntity(ClientDTO clientDTO) {
        Client client = new Client();
        client.setClientID(clientDTO.getClientID());
        client.setUserID(clientDTO.getUserID());
        client.setBonusPoints(clientDTO.getBonusPoints());
        return client;
    }

    public static ClientDTO toDTO(ClientView clientView) {
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setClientID(clientView.getClientID());
        clientDTO.setUserID(clientView.getUserID());
        clientDTO.setBonusPoints(clientView.getBonusPoints());
        return clientDTO;
    }

    public static ClientDTO toDTO(Client client) {
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setClientID(client.getClientID());
        clientDTO.setUserID(client.getUserID());
        clientDTO.setBonusPoints(client.getBonusPoints());
        return clientDTO;
    }

    public static ClientView toView(ClientDTO clientDTO) {
        ClientView clientView = new ClientView();
        clientView.setClientID(clientDTO.getClientID());
        clientView.setUserID(clientDTO.getUserID());
        clientView.setBonusPoints(clientDTO.getBonusPoints());
        return clientView;
    }
}
