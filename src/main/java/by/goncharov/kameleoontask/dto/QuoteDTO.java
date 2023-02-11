package by.goncharov.kameleoontask.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class QuoteDTO {
    private Long id;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private UserDTO user;
}
