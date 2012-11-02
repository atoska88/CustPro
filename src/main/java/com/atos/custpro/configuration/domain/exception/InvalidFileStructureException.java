package com.atos.custpro.configuration.domain.exception;

import com.atos.custpro.configuration.domain.Affix;
import com.atos.custpro.exception.CustProException;

/**
 * Signals that the being validated file has structure errors.
 * @author atos
 *
 */
public class InvalidFileStructureException extends CustProException {

    private final String targetString;
    private final Affix affix;

    /**
     * Constructs a new exception with the given parameters.
     * @param affix the affix which had the prefix and suffix and the
     * matching failed for
     * @param targetString the target string which was tested for
     * matching
     */
    public InvalidFileStructureException(final Affix affix, final String targetString) {
        super("The target string is not bounded with the affix!");
        this.affix = affix;
        this.targetString = targetString;
    }

    public String getTargetString() {
        return targetString;
    }

    public Affix getAffix() {
        return affix;
    }

}
