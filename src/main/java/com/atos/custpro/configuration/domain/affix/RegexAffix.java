package com.atos.custpro.configuration.domain.affix;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.atos.custpro.configuration.domain.Affix;
import com.atos.custpro.configuration.domain.exception.InvalidFileStructureException;

/**
 * Affix that works with regular expressions as its prefix and suffix.
 * The {@link Affix#putAffixToTarget(String)} method is not
 * implemented by this implementation and throws an
 * {@link UnsupportedOperationException} only. The target string can
 * contain regular expressions, too. This implementation is fully
 * case sensitive according to the {@link Pattern} and {@link Matcher}
 * implementations.
 * @author atos
 *
 */
public class RegexAffix extends AbstractAffix {

    private static final String REGEX_ANY_PATTERN = "([\\w\\W]*)";
    private static final String AFFIXED_TARGET_IN_GROUP_PATTERN = "%s(%s)%s";

    private final int groupIndex;
    private final String patternString;

    /**
     * Constructs a new affix with the given two regular expressions.
     * If the prefix and suffix are only literals, the {@link
     * LiteralAffix} should be used for performance boost.
     * @param prefix the prefix that is the starting of a string, can
     * be a regular expression or a literal
     * @param suffix the prefix that is the ending of a string, can
     * be a regular expression or a literal
     * @param numberOfGroupsInPrefix the number of capturing groups
     * in the prefix. Needed for knowing which group contains the
     * bounded string.
     */
    public RegexAffix(final String prefix, final String suffix, final int numberOfGroupsInPrefix) {
        super(prefix, suffix);
        this.groupIndex = numberOfGroupsInPrefix + 1;
        this.patternString = createRegex();
    }

    /**
     * Constructs a new empty regex.
     */
    public RegexAffix() {
        this("", "", 0);
    }

    @Override
    public String putAffixToTarget(final String target) {
        throw new UnsupportedOperationException("Regular expression affixes cannot be put onto target strings!");
    }

    @Override
    public String removeFromTarget(final String target) throws InvalidFileStructureException {
        Matcher matcher = getMatchingMatcher(target);
        return matcher.group(groupIndex);
    }

    private String createRegex() {
        return String.format(AFFIXED_TARGET_IN_GROUP_PATTERN, getPrefix(), REGEX_ANY_PATTERN, getSuffix());
    }

    @Override
    public void validateTarget(final String target) throws InvalidFileStructureException {
        getMatchingMatcher(target);
    }

    private Matcher getMatchingMatcher(final String target) throws InvalidFileStructureException {
        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(target);
        if (!matcher.matches()) {
            throw new InvalidFileStructureException("The target string is not bounded with the affix!");
        }
        return matcher;
    }

}
