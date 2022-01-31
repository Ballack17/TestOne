package urishortener.entities;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

//for Delete
public class SystemReference1 {
    @NotNull
    private String referenceShort;

    public String getReferenceShort() {
        return referenceShort;
    }

    public void setReferenceShort(String referenceShort) {
        this.referenceShort = referenceShort;
    }
}

