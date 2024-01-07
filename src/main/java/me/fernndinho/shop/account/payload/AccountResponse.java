package me.fernndinho.shop.account.payload;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import me.fernndinho.shop.account.models.AccountType;

@Data
public class AccountResponse {
    private String token;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String type;

    public AccountResponse(String token, AccountType type) {
        if(type.equals(AccountType.ADMIN)) {
            this.type = AccountType.ADMIN.name();
        }
        this.token = token;
    }
}
