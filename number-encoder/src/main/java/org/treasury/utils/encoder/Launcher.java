package org.treasury.utils.encoder;

import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * Created by kuzmende on 7/11/15.
 */
public class Launcher {

    private static final Logger LOG = Logger.getLogger(Launcher.class);

    public static void main(String[] args) throws IOException, URISyntaxException {
        if (args.length != 2) return;

        URL dictionaryUrl = Launcher.class.getClassLoader().getResource(args[0]);
        Path dictionaryFile = Paths.get(dictionaryUrl.toURI());

        NumberEncoder numberEncoder = new NumberEncoder(
                Files.readAllLines(dictionaryFile, Charset.defaultCharset()));

        URL numberUrl = Launcher.class.getClassLoader().getResource(args[1]);
        Path numberFile = Paths.get(numberUrl.toURI());

        BufferedReader reader = Files.newBufferedReader(numberFile, Charset.defaultCharset());

        String phoneNumber;
        while ((phoneNumber = reader.readLine()) != null) {
            final List<String> results = numberEncoder.encode(phoneNumber);

            for (String encodedNumber : results) {
                LOG.info(phoneNumber + ": " + encodedNumber);
            }
        }
    }

}
