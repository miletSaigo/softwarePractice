package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.ClientDao;
import po.Client;
import util.DBUtil;

public class ClientDaoImpl implements ClientDao{
    private Connection con = null;
    private PreparedStatement pst = null;
    private ResultSet rs = null;

    @Override
    public List<Client> listClient(Integer clientId, String clientName) {
        List<Client> list = new ArrayList<>();
        StringBuffer sql = new StringBuffer("select * from client where 1=1 ");
        if(clientId!=null&&!clientId.equals("")) {
            sql.append(" and clientId like '%"+clientId+"%' ");
        }
        if(clientName!=null&&!clientName.equals("")) {
            sql.append(" and clientName like '%"+clientName+"%' ");
        }
        try {
            con = DBUtil.getConnection();
            pst = con.prepareStatement(sql.toString());
            rs = pst.executeQuery();
            while(rs.next()) {
                Client client = new Client();
                client.setClientId(rs.getInt("clientId"));
                client.setPassword(rs.getString("password"));
                client.setClientName(rs.getString("clientName"));
                client.setClientMoney(rs.getString("clientMoney"));
                client.setClientAddress(rs.getString("clientAddress"));
                client.setCredit(rs.getString("credit"));
                client.setClientService(rs.getString("clientService"));
                list.add(client);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(rs, pst, con);
        }
        return list;
    }

    @Override
    public int saveClient(String clientName) {
        int clientId = 0;
        String sql = "insert into dlient(clientName,password) values(?,'123')";
        try {
            con = DBUtil.getConnection();
            //设置返回自增长列值
            pst = con.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
            pst.setString(1, clientName);
            pst.executeUpdate();
            //获取自增长列值（一行一列）
            rs = pst.getGeneratedKeys();
            if(rs.next()) {
                clientId = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(rs, pst, con);
        }
        return clientId;
    }

    @Override
    public int removeClient(int clientId) {
        int result = 0;
        String delClientSql = "delete from client where clientId=?";
        try {
            con = DBUtil.getConnection();
            //开启一个事务
            con.setAutoCommit(false);

            pst = con.prepareStatement(delClientSql);
            pst.setInt(1, clientId);
            result = pst.executeUpdate();

            con.commit();
        } catch (SQLException e) {
            result = 0;
            try {
                con.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            DBUtil.close(null, pst, con);
        }
        return result;
    }

    @Override
    public Client getClientByIdByPass(Integer clientId,String password) {
        Client client = null;
        String sql = "select * from client where clientId=? and password=?";
        try {
            con = DBUtil.getConnection();
            pst = con.prepareStatement(sql.toString());
            pst.setInt(1, clientId);
            pst.setString(2, password);
            rs = pst.executeQuery();
            while(rs.next()) {
                client = new Client();
                client.setClientId(rs.getInt("clientId"));
                client.setPassword(rs.getString("password"));
                client.setClientName(rs.getString("clientName"));
                client.setClientMoney(rs.getString("clientMoney"));
                client.setClientAddress(rs.getString("clientAddress"));
                client.setCredit(rs.getString("credit"));
                client.setClientService(rs.getString("clientService"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(rs, pst, con);
        }
        return client;
    }

    @Override
    public Client getClientById(Integer clientId) {
        Client client = null;
        String sql = "select * from client where clientId=?";
        try {
            con = DBUtil.getConnection();
            pst = con.prepareStatement(sql.toString());
            pst.setInt(1, clientId);
            rs = pst.executeQuery();
            while(rs.next()) {
                client = new Client();
                client.setClientId(rs.getInt("clientId"));
                client.setPassword(rs.getString("password"));
                client.setClientName(rs.getString("clientName"));
                client.setClientMoney(rs.getString("clientMoney"));
                client.setClientAddress(rs.getString("clientAddress"));
                client.setCredit(rs.getString("credit"));
                client.setClientService(rs.getString("clientService"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(rs, pst, con);
        }
        return client;
    }

    @Override
    public int updateClient(Client client) {
        int result = 0;
        String sql = "update client set clientName=?,clientMoney=?,clientAddress=?,credit=?,clientService=? where clientId=?";
        try {
            con = DBUtil.getConnection();
            pst = con.prepareStatement(sql);
            pst.setString(1, client.getClientName());
            pst.setString(2, client.getClientMoney());
            pst.setString(3, client.getClientAddress());
            pst.setString(4, client.getCredit());
            pst.setString(5, client.getClientService());
            pst.setInt(6, client.getClientId());
            result = pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(null, pst, con);
        }
        return result;
    }

    @Override
    public int updateClientByPassword(Integer clientId,String password) {
        int result = 0;
        String sql = "update business set password=? where clientId=?";
        try {
            con = DBUtil.getConnection();
            pst = con.prepareStatement(sql);
            pst.setString(1, password);
            pst.setInt(2, clientId);
            result = pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(null, pst, con);
        }
        return result;
    }
}
