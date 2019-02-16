package com.sd.hackathon.eatable.backend.document;

import lombok.*;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UrlCode {
    @Id
    private String id;

    private String code;
    private String url;
    private Long hitCount;
}
