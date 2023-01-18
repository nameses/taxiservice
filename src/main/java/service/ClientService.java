package service;

import DAO.ClientDAO;
import exceptions.DAOException;
import exceptions.ServiceException;
import models.DTO.ClientDTO;
import models.converters.ClientConverter;
import models.entity.Client;

public class ClientService {
    private final ClientDAO clientDAO = new ClientDAO();

    public ClientDTO register(ClientDTO clientDTO) throws ServiceException {
        try{
            return clientDAO.insert(ClientConverter.toEntity(clientDTO));
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(),e);
        }
    }
}
