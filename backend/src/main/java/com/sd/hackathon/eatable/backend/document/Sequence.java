package com.sd.hackathon.eatable.backend.document;

import lombok.*;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Sequence {
    @Id
    private String name;
    private long next;
}
