package com.atos.custpro.parser;

import java.util.Properties;

import com.atos.custpro.configuration.domain.FileStructureConfiguration;
import com.atos.custpro.configuration.domain.exception.InvalidFileStructureException;
import com.atos.custpro.parser.message.KeyValuePair;
import com.atos.custpro.parser.message.MessageParser;

/**
 * {@link StringParser} implementation that reads the input line by
 * line and creates the key-value pairs from the lines.
 * @author atos
 * @since 1.0
 *
 */
public class LineStringParser implements StringParser {

    private boolean failOnUnparsedMessage = false;
    private final MessageParser messageParser;

    /**
     * Constructs a new instance with the given parameter.
     * @param messageParser parses single lines into key value pairs
     */
    public LineStringParser(final MessageParser messageParser) {
        this.messageParser = messageParser;
    }

    @Override
    public Properties parse(final String wholeInput, final FileStructureConfiguration configuration) throws InvalidFileStructureException {
        String cleanedInput = configuration.getFileAffix().removeFromTarget(wholeInput);
        Properties result = new Properties();
        if (!cleanedInput.isEmpty()) {
            String[] messages = cleanedInput.split(configuration.getLineTerminator());
            for (String messageLine : messages) {
                tryToParseMessage(configuration, result, messageLine);
            }
        }
        return result;

    }

    private void tryToParseMessage(final FileStructureConfiguration configuration, final Properties result, final String messageLine)
        throws InvalidFileStructureException {
        try {
            KeyValuePair parseMessage = messageParser.parseMessage(messageLine, configuration);
            result.put(parseMessage.getKey(), parseMessage.getValue());
        } catch (InvalidFileStructureException exception) {
            if (failOnUnparsedMessage) {
                throw exception;
            }
        }
    }

    void setFailOnUnparsedMessage(final boolean failOnUnparsedMessage) {
        this.failOnUnparsedMessage = failOnUnparsedMessage;
    }
}
