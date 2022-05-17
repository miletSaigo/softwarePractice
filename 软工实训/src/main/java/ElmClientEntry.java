import po.Admin;
import po.Client;
import view.AdminView;
import view.ClientView;
import view.impl.AdminViewImpl;
import view.impl.ClientViewImpl;

import java.util.Scanner;

public class ElmClientEntry {

    public void work() {
        Scanner input = new Scanner(System.in);

        System.out.println("---------------------------------------------------------");
        System.out.println("|\t\t\t xx银行客户系统  \t\t\t|");
        System.out.println("---------------------------------------------------------");

        ClientView clientView = new ClientViewImpl();

        //商家登录
        Client client = clientView.login();

        if(client!=null) {
            int menu = 0;
            while(menu!=5) {
                //输出一级菜单
                System.out.println("\n=======1.查看客户信息=2.编辑客户信息=3.更新密码=4.存取现金=5.退出系统=======");
                System.out.println("请输入你的选择：");
                menu = input.nextInt();

                switch(menu) {
                    case 1:
                        clientView.showClient(client.getClientId());
                        break;
                    case 2:
                        clientView.editClient(client.getClientId());
                        break;
                    case 3:
                        clientView.updateClientByPassword(client.getClientId());
                        break;
                    case 4:
                        clientView.editMoney(client.getClientId());
                        break;
                    case 5:
                        System.out.println("------------------------感谢使用xx银行客户系统-----------------------");
                        break;
                    default:
                        System.out.println("没有这个选项！\n");
                        break;
                }
            }
        }else {
            System.out.println("客户编号或密码输入错误！");
        }

    }



    public static void main(String[] args) {
        new ElmClientEntry().work();
    }
}
