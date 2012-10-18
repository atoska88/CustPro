package com.atos.custpro.configuration.domain.affix;

import com.atos.custpro.configuration.domain.exception.InvalidFileStructureException;

/**
 * Contains a prefix and a suffix and some helper methods for handling
 * affixes.
 * @author atos
 *
 */
public class LiteralAffix extends AbstractAffix {

    /**
     * Constructs an empty affix with empty prefix and suffix.
     */
    public LiteralAffix() {
        super();
    }

    /**
     * Constructs a new affix with the given parameters.
     * @param prefix the prefix, that is before a character sequence
     * @param suffix the suffix, that is after a character sequence
     */
    public LiteralAffix(final String prefix, final String suffix) {
        super(prefix, suffix);
    }

    @Override
    public String putAffixToTarget(final String target) {
        String checkedTarget = target == null ? "" : target;
        return getPrefix() + checkedTarget + getSuffix();
    }

    @Override
    public String removeFromTarget(final String target) throws InvalidFileStructureException {
        validateTarget(target);
        return target.substring(getPrefix().length(), target.lastIndexOf(getSuffix()));
    }

    @Override
    public void validateTarget(final String target) throws InvalidFileStructureException {
        if (!isPrefixed(target)) {
            throw new InvalidFileStructureException("The target string does not starts with this prefix!");
        }
        if (!isSuffixed(target)) {
            throw new InvalidFileStructureException("The target string does not ends with this affix!");
        }
    }

    private boolean isSuffixed(final String target) {
        return target.endsWith(getSuffix());
    }

    private boolean isPrefixed(final String target) {
        return target.startsWith(getPrefix());
    }
}
