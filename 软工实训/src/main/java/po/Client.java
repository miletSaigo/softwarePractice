package po;

import lombok.Data;

@Data
public class Client {
    private Integer clientId;
    private String password;
    private String clientName;
    private String clientMoney;
    private String clientAddress;
    private String credit;
    private String clientService;
}
