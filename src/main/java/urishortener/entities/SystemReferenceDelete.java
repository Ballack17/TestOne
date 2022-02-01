package urishortener.entities;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SystemReferenceDelete {

    @NotNull
    private String referenceShort;

}

