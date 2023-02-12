package by.goncharov.kameleoontask.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VoteDTO {
    private Long id;

    private Integer value;

    private Long quoteId;
}
