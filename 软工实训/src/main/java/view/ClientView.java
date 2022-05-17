package view;

import po.Client;

public interface ClientView {
    public void listClientAll();
    public void listClient();
    public void saveClient();
    public void removeClient();
    public void editClientForAdmin();

    public Client login();
    public void showClient(Integer clientId);
    public void editClient(Integer clientId);
    public void updateClientByPassword(Integer clientId);
    public void editMoney(Integer clientId);
}
