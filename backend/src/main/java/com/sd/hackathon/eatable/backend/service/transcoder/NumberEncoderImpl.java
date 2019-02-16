package com.sd.hackathon.eatable.backend.service.transcoder;

import com.sd.hackathon.eatable.backend.util.ValueCharConverter;
import org.springframework.stereotype.Service;

@Service
public class NumberEncoderImpl implements NumberEncoder {
    @Override
    public String encode(long value) {
        StringBuffer buffer = new StringBuffer();
        long temp = value;
        do {
            buffer.append(ValueCharConverter.valueToChar((byte) (temp % ValueCharConverter.getBase())));
            temp /= ValueCharConverter.getBase();
        } while (temp > 0);
        buffer.trimToSize();
        return buffer.toString();
    }
}
