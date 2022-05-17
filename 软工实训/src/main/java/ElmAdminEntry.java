import java.util.Scanner;

import po.Admin;
import view.AdminView;
import view.ClientView;
import view.impl.AdminViewImpl;
import view.impl.ClientViewImpl;

public class ElmAdminEntry {

    public void work() {
        Scanner input = new Scanner(System.in);

        System.out.println("---------------------------------------------------------");
        System.out.println("|\t\t\t xx银行客户管理系统  \t\t\t|");
        System.out.println("---------------------------------------------------------");

        AdminView adminView = new AdminViewImpl();
        ClientView clientView = new ClientViewImpl();
        Admin admin = adminView.login();

        //登录
        if(admin!=null) {
            int menu = 0;
            while(menu!=6) {
                //输出主菜单
                System.out.println("\n========= 1.显示所有客户=2.搜索客户=3.新建客户=4.删除客户=5.编辑客户信息=6.退出系统 =========");
                System.out.println("请输入你的选择：");
                menu = input.nextInt();
                switch(menu) {
                    case 1:
                        clientView.listClientAll();
                        break;
                    case 2:
                        clientView.listClient();
                        break;
                    case 3:
                        clientView.saveClient();
                        break;
                    case 4:
                        clientView.removeClient();
                        break;
                    case 5:
                        clientView.editClientForAdmin();
                        break;
                    case 6:
                        System.out.println("------------------------感谢使用xx银行客户管理系统-----------------------");
                        break;
                    default:
                        System.out.println("没有这个选项！\n");
                        break;
                }
            }

        }else {
            System.out.println("\n管理员名称或密码输入错误!\n");
        }
    }

    public static void main(String[] args) {
        new ElmAdminEntry().work();
    }

}
