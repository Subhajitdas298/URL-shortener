package com.sd.hackathon.eatable.backend.document;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UrlCode {
    @Id
    private String code;
    @Indexed(unique = true)
    private String url;
    private Long hitCount;
}
