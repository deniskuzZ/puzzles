package org.treasury.utils.encoder;

import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

/**
 * Created by kuzmende on 7/11/15.
 */
public class NumberEncoderTest {

    private final NumberEncoder numberEncoder;

    public NumberEncoderTest() throws URISyntaxException, IOException {
        URL dictionaryUrl = getClass().getClassLoader().getResource("test_dictionary.txt");
        Path dictionaryFile = Paths.get(dictionaryUrl.toURI());

        numberEncoder = new NumberEncoder(
                Files.readAllLines(dictionaryFile, Charset.defaultCharset()));
    }

    @Test
    public void test(){
        assertTrue(numberEncoder.encode("112").isEmpty());
        assertEquals(numberEncoder.encode("5624-82"), asList("mir Tor", "Mix Tor"));
        assertEquals(numberEncoder.encode("4824"), asList("Tor 4", "fort", "Torf"));
        assertEquals(numberEncoder.encode("10/783--5"), asList("je Bo\" da", "je bo\"s 5", "neu o\"d 5"));
        assertEquals(numberEncoder.encode("381482"), singletonList("so 1 Tor"));
        assertEquals(numberEncoder.encode("04824"), asList("0 Tor 4", "0 fort", "0 Torf"));
    }

}
