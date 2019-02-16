package com.sd.hackathon.eatable.backend.document;

import lombok.*;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SequenceNumber {
    @Id
    private String id;

    private String sequenceName;
    private long nextNumber;
}
