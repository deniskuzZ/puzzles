package org.treasury.utils.encoder;

import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * Created by kuzmende on 7/11/15.
 */
public class LauncherTest {

    @Test
    public void testEncodeNumbers() throws IOException, URISyntaxException {
        final String[] args = {"dictionary.txt", "input.txt"};
        Launcher.main(args);
    }
}
