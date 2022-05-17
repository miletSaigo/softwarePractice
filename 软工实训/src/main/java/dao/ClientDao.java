package dao;

import po.Client;

import java.util.List;

public interface ClientDao {
    public List<Client> listClient(Integer clientId, String clientName);
    public int saveClient(String clientName);
    public int removeClient(int clientId);

    public Client getClientByIdByPass(Integer clientId,String password);
    public Client getClientById(Integer clientId);
    public int updateClient(Client client);
    public int updateClientByPassword(Integer clientId,String password);
}
