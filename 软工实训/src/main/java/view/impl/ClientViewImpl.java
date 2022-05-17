package view.impl;

import dao.ClientDao;
import dao.impl.ClientDaoImpl;
import po.Client;
import view.ClientView;

import java.util.List;
import java.util.Scanner;

import static java.lang.Integer.*;
import static java.lang.Integer.valueOf;

public class ClientViewImpl implements ClientView {

    private Scanner input = new Scanner(System.in);

    @Override
    public void listClientAll() {
        ClientDao dao = new ClientDaoImpl();
        List<Client> list = dao.listClient(null, null);
        System.out.println("客户编号\t客户姓名\t客户地址\t客户余额\t客户信誉\t客户服务类型");
        for (Client c : list) {
            System.out.println(c.getClientId() + "\t" + c.getClientName() + "\t" + c.getClientMoney() + "\t" + c.getClientAddress() + "\t" + c.getCredit() + "\t" + c.getClientService());
        }
    }

    @Override
    public void listClient() {
        Integer clientId = 0;
        String clientName = "";

        String inputStr = "";
        System.out.println("是否需要输入客户编号(y/n)：");
        inputStr = input.next();
        if (inputStr.equals("y")) {
            System.out.println("请输入客户编号：");
            clientId = input.nextInt();
        }

        System.out.println("是否需要输入客户姓名(y/n)：");
        inputStr = input.next();
        if (inputStr.equals("y")) {
            System.out.println("请输入客户姓名：");
            clientName = input.next();
        }

        ClientDao dao = new ClientDaoImpl();
        List<Client> list = dao.listClient(clientId, clientName);
        System.out.println("客户编号\t客户姓名\t客户余额\t商家地址\t客户信誉\t客户服务类型");
        for (Client c : list) {
            System.out.println(c.getClientId() + "\t" + c.getClientName() + "\t" + c.getClientMoney() + "\t" + c.getClientAddress() + "\t" + c.getCredit() + "\t" + c.getClientService());
        }
    }

    @Override
    public void saveClient() {
        System.out.println("请输入客户姓名：");
        String clientName = input.next();
        ClientDao dao = new ClientDaoImpl();
        int clientId = dao.saveClient(clientName);
        if (clientId > 0) {
            System.out.println("新建客户成功！商家编号为：" + clientId);
        } else {
            System.out.println("新建客户失败！");
        }
    }

    @Override
    public void removeClient() {
        System.out.println("请输入客户编号：");
        int clientId = input.nextInt();

        ClientDao dao = new ClientDaoImpl();
        System.out.println("确认要删除吗(y/n)：");
        if (input.next().equals("y")) {
            int result = dao.removeClient(clientId);
            if (result == 1) {
                System.out.println("删除客户成功！");
            } else {
                System.out.println("删除客户失败！");
            }
        }
    }

    @Override
    public void editClientForAdmin(){
        System.out.println("请输入客户编号：");
        int clientId = input.nextInt();

        ClientDao dao = new ClientDaoImpl();
        Client client = dao.getClientById(clientId);
        System.out.println(client);

        String inputStr = "";
        System.out.println("是否修改客户信誉(y/n)：");
        inputStr = input.next();
        if (inputStr.equals("y")) {
            System.out.println("请输入新的信誉情况：");
            client.setCredit(input.next());
        }

        System.out.println("是否修改客户服务类型(y/n)：");
        inputStr = input.next();
        if (inputStr.equals("y")) {
            System.out.println("请输入新的服务类型：");
            client.setClientService(input.next());
        }
    }

    @Override
    public Client login() {
        System.out.println("请输入客户编号：");
        int clientId = input.nextInt();
        System.out.println("请输入密码：");
        String password = input.next();

        ClientDao dao = new ClientDaoImpl();
        return dao.getClientByIdByPass(clientId, password);
    }

    @Override
    public void showClient(Integer clientId) {
        ClientDao dao = new ClientDaoImpl();
        Client client = dao.getClientById(clientId);
        System.out.println(client);
    }

    @Override
    public void editClient(Integer clientId) {
        //先将商家信息查询出来显示，然后用户才能更新
        ClientDao dao = new ClientDaoImpl();
        Client client = dao.getClientById(clientId);
        System.out.println(client);

        String inputStr = "";
        System.out.println("是否修改客户姓名(y/n)：");
        inputStr = input.next();
        if (inputStr.equals("y")) {
            System.out.println("请输入新的用户姓名：");
            client.setClientName(input.next());
        }

        System.out.println("是否修改客户地址(y/n)：");
        inputStr = input.next();
        if (inputStr.equals("y")) {
            System.out.println("请输入新的客户地址：");
            client.setClientAddress(input.next());
        }

        int result = dao.updateClient(client);
        if (result > 0) {
            System.out.println("\n修改客户信息成功！\n");
        } else {
            System.out.println("\n修改客户信息失败！\n");
        }
    }

    @Override
    public void updateClientByPassword(Integer clientId) {
        ClientDao dao = new ClientDaoImpl();
        Client client = dao.getClientById(clientId);

        System.out.println("\n请输入旧密码：");
        String oldPass = input.next();
        System.out.println("\n请输入新密码：");
        String password = input.next();
        System.out.println("\n请再次输入新密码：");
        String beginPassword = input.next();

        if (!client.getPassword().equals(oldPass)) {
            System.out.println("\n旧密码输入错误！");
        } else if (!password.equals(beginPassword)) {
            System.out.println("\n两次输入密码不一致！");
        } else {
            int result = dao.updateClientByPassword(clientId, password);
            if (result > 0) {
                System.out.println("\n修改密码成功！");
            } else {
                System.out.println("\n修改密码失败！");
            }
        }
    }

    @Override
    public void editMoney(Integer clientId){
        ClientDao dao = new ClientDaoImpl();
        Client client = dao.getClientById(clientId);

        String inputStr = "";
        System.out.println("是否进行存款(y/n)：");
        inputStr = input.next();
        if (inputStr.equals("y")) {
            System.out.println("请放置您要存取的现金：");
            client.setClientName(client.getClientMoney()+input.next());
        }

        System.out.println("是否进行取款(y/n)：");
        inputStr = input.next();
        if (inputStr.equals("y")) {
            System.out.println("请输入您要取出的现金：");
            inputStr = input.next();
            if (parseInt(client.getClientMoney()) - parseInt(inputStr) < 0) {
                System.out.println("取款不能大于原有金额！");
            }else{
                client.setClientMoney(Integer.toString(parseInt(client.getClientMoney()) - parseInt(inputStr)));
                System.out.println("已成功取出!");
            }
        }
    }
}
