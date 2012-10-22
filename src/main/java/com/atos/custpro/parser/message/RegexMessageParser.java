package com.atos.custpro.parser.message;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.atos.custpro.configuration.domain.Affix;
import com.atos.custpro.configuration.domain.FileStructureConfiguration;
import com.atos.custpro.configuration.domain.exception.InvalidFileStructureException;

/**
 * {@link MessageParser} implementation that uses regular expression
 * to parse the key and value.
 * @author atos
 * @since 1.0
 */
public class RegexMessageParser implements MessageParser {

    private static final String REGEX_ANYSTRING_PATTERN = "(.*)";
    private final StringBuilder stringBuilder = new StringBuilder();

    @Override
    public KeyValuePair parseMessage(final String message, final FileStructureConfiguration configuration) throws InvalidFileStructureException {
        Matcher matcher = createMatcher(message, configuration);
        if (!matcher.matches()) {
            throw new InvalidFileStructureException(message);
        }

        return new KeyValuePair(matcher.group(1), matcher.group(2));
    }

    private Matcher createMatcher(final String message, final FileStructureConfiguration configuration) {
        String pattern = createPattern(configuration);
        Matcher matcher = Pattern.compile(pattern).matcher(message);
        return matcher;
    }

    private String createPattern(final FileStructureConfiguration configuration) {
        Affix propertyAffix = configuration.getPropertyAffix();
        Affix keyAffix = configuration.getKeyAffix();
        Affix valueAffix = configuration.getValueAffix();
        stringBuilder.setLength(0);
        stringBuilder.append(propertyAffix.getPrefix()).append(keyAffix.getPrefix());
        stringBuilder.append(REGEX_ANYSTRING_PATTERN);
        stringBuilder.append(keyAffix.getSuffix()).append(configuration.getKeyValueSeparator()).append(valueAffix.getPrefix());
        stringBuilder.append(REGEX_ANYSTRING_PATTERN);
        stringBuilder.append(valueAffix.getSuffix()).append(propertyAffix.getSuffix());
        String pattern = stringBuilder.toString();
        return pattern;
    }

}
