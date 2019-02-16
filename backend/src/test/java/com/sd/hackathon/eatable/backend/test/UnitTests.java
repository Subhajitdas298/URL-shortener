package com.sd.hackathon.eatable.backend.test;

import com.sd.hackathon.eatable.backend.service.transcoder.NumberDecoder;
import com.sd.hackathon.eatable.backend.service.transcoder.NumberDecoderImpl;
import com.sd.hackathon.eatable.backend.service.transcoder.NumberEncoder;
import com.sd.hackathon.eatable.backend.service.transcoder.NumberEncoderImpl;
import com.sd.hackathon.eatable.backend.util.ValueCharConverter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UnitTests {
    /*
     Transcoder testing
     */
    // Testing ValueCodeMap
    @Test
    public void valueToCharTest() throws Exception{
        assertEquals('a', ValueCharConverter.valueToChar((byte)0));
        assertEquals('z', ValueCharConverter.valueToChar((byte)25));
        assertEquals('A', ValueCharConverter.valueToChar((byte)26));
        assertEquals('Z', ValueCharConverter.valueToChar((byte)51));
        assertEquals('0', ValueCharConverter.valueToChar((byte)52));
        assertEquals('9', ValueCharConverter.valueToChar((byte)61));
    }

    @Test
    public void charToValueTest() throws Exception{
        assertEquals(0, ValueCharConverter.charToValue('a'));
        assertEquals(25, ValueCharConverter.charToValue('z'));
        assertEquals(26, ValueCharConverter.charToValue('A'));
        assertEquals(51, ValueCharConverter.charToValue('Z'));
        assertEquals(52, ValueCharConverter.charToValue('0'));
        assertEquals(61, ValueCharConverter.charToValue('9'));
    }

    // Testing encoder
    @Test
    public void encoderTest() throws Exception{
        NumberEncoder encoder = new NumberEncoderImpl();
        assertEquals("a", encoder.encode(0));
        assertEquals("ab", encoder.encode(62));
        assertEquals("b9", encoder.encode(3783));
    }

    // Testing decoder
    @Test
    public void decoderTest() throws Exception{
        NumberDecoder decoder = new NumberDecoderImpl();
        assertEquals(0, decoder.decode("a"));
        assertEquals(62, decoder.decode("ab"));
        assertEquals(3783, decoder.decode("b9"));
    }
}
