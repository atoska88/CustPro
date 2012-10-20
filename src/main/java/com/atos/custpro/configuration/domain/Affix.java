package com.atos.custpro.configuration.domain;

import com.atos.custpro.configuration.domain.exception.InvalidFileStructureException;

/**
 * The affix is constructed from two strings which are the prefix and
 * suffix. The prefix stands before, the suffix stands after a
 * character sequence. This interface does not support infixes which
 * can be inside the a character sequence.
 * The interface contains some helper methods that can be used for
 * creating affixed strings or to remove it from a string. Note that
 * it is not sure to all implementations implements these methods.
 * @author atos
 *
 */
public interface Affix {

    /**
     * Returns the prefix of this Affix.
     * @return the prefix
     */
    String getPrefix();

    /**
     * Returns the suffix of this Affix.
     * @return the suffix
     */
    String getSuffix();

    /**
     * Bounds the given string with this Affix.
     * @param target the target string, can be null.
     * @return the target string bounded by this affix. First the
     * prefix, the target and the suffix. If the target was null or
     * empty then the prefix and affix will be returned.
     *
     */
    String putAffixToTarget(String target);

    /**
     * Removes this affix from the target string.
     * @param target the target string
     * @return the target string without the prefix and suffix
     * @throws InvalidFileStructureException if the target string is
     * not bounded with this affix
     */
    String removeFromTarget(String target) throws InvalidFileStructureException;

    /**
     * Validates if the given target String is bounded with this
     * affix.
     * @param target the target string
     * @throws InvalidFileStructureException if the target string is
     * not bounded with this affix
     */
    void validateTarget(String target) throws InvalidFileStructureException;

}
