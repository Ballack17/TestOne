package urishortener.entities;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SystemReferenceCreating {
    @NotNull
    private String referenceUser;

    private int lifetime;

}

