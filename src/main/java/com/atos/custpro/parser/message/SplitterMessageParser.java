package com.atos.custpro.parser.message;

import com.atos.custpro.configuration.domain.FileStructureConfiguration;
import com.atos.custpro.configuration.domain.exception.InvalidFileStructureException;
import com.atos.custpro.parser.message.exception.InvalidMessageException;

/**
 * {@link MessageParser} implementation that uses {@link String#
 * split(String)} during the parsing process.
 * during the parsing process.
 * @author atos
 * @since 1.0
 */
public class SplitterMessageParser implements MessageParser {

    @Override
    public KeyValuePair parseMessage(final String message, final FileStructureConfiguration configuration) throws InvalidFileStructureException {
        String cleanedMessage = configuration.getPropertyAffix().removeFromTarget(message);
        String[] keyValue = cleanedMessage.split(configuration.getKeyValueSeparator());
        validateMessage(keyValue);
        String key = configuration.getKeyAffix().removeFromTarget(keyValue[0]);
        String value = configuration.getValueAffix().removeFromTarget(keyValue[1]);
        return new KeyValuePair(key, value);
    }

    private void validateMessage(final String[] keyValue) throws InvalidMessageException {
        if (keyValue.length != 2) {
            throw new InvalidMessageException(keyValue);
        }

    }

}
