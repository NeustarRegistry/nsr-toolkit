package neustar.registry.jtoolkit2;

import java.io.IOException;
import java.util.logging.FileHandler;

/**
 * A trivial subclass of FileHandler intended to provide support for configuration specific to Registry help-desk
 * logging. The configuration properties are set via <code>neustar.registry.jtoolkit2.SupportHandler.*</code> parameters
 * in the logging parameters file. See the documentation of the <code>FileHandler</code> for a description of the
 * available parameters.
 *
 * This handler also initialises the ToolkitRuntime.
 *
 * @see neustar.registry.jtoolkit2.ToolkitRuntime
 */
public class SupportHandler extends FileHandler {

    static {
        ToolkitRuntime.init();
    }

    /**
     * Instantiates a new Support handler.
     *
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     */
    public SupportHandler() throws IOException {
        super();
    }
}
