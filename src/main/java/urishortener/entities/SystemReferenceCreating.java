package urishortener.entities;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

//for Delete
public class SystemReferenceCreating {
    @NotNull
    private String referenceUser;

    private int lifetime;

    public String getReferenceUser() {
        return referenceUser;
    }

    public void setReferenceUser(String referenceUser) {
        this.referenceUser = referenceUser;
    }

    public int getLifetime() {
        return lifetime;
    }

    public void setLifetime(int lifetime) {
        this.lifetime = lifetime;
    }
}

