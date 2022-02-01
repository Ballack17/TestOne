package urishortener.entities;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SystemUser {
    @NotNull
    private String username;

    @NotNull
    private String password;

    @NotNull
    private String matchingPassword;

}
