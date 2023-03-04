package service;

import DAO.ClientDAO;
import exceptions.DAOException;
import exceptions.ServiceException;
import models.DTO.ClientDTO;
import models.converters.ClientConverter;
import models.entity.Client;
import models.view.ClientView;

public class ClientService {
    private final ClientDAO clientDAO = new ClientDAO();

    public ClientDTO updateByClientID(ClientDTO clientDTO) throws ServiceException {
        try{
            return new ClientDTO(clientDAO.updateByClientID(ClientConverter.toEntity(clientDTO)));
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(),e);
        }
    }
    public ClientDTO selectByUserID(ClientView clientView) throws ServiceException {
        try{
            return clientDAO.getByUserID(clientView.getUserID());
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(),e);
        }
    }
    public ClientDTO register(ClientDTO clientDTO) throws ServiceException {
        try{
            return clientDAO.insert(ClientConverter.toEntity(clientDTO));
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(),e);
        }
    }
}
