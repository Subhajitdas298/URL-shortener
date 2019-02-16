package com.sd.hackathon.eatable.backend.service.transcoder;

import com.sd.hackathon.eatable.backend.util.ValueCharConverter;
import org.springframework.stereotype.Service;

@Service
public class NumberDecoderImpl implements NumberDecoder {
    @Override
    public long decode(String code) {
        char[] chars = code.toCharArray();
        long temp = 0;
        long multiplier = 1;
        for(int i = 0; i < chars.length; i++){
            temp += multiplier * ValueCharConverter.charToValue(chars[i]);
            multiplier *= ValueCharConverter.getBase();
        }
        return temp;
    }
}
