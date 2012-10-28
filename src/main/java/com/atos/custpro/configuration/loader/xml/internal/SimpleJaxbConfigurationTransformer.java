package com.atos.custpro.configuration.loader.xml.internal;

import com.atos.custpro.configuration.domain.FileStructureConfiguration;
import com.atos.custpro.configuration.loader.FileStructureWrapper;
import com.atos.custpro.configuration.loader.xml.exception.XmlParsingException;
import com.atos.custpro.configuration.loader.xml.generated.Affix;
import com.atos.custpro.configuration.loader.xml.generated.Configuration;
import com.atos.custpro.configuration.loader.xml.generated.LiteralAffix;
import com.atos.custpro.configuration.loader.xml.generated.RegexAffix;

/**
 * Simple implementation of {@link JaxbConfigurationTransformer}.
 * @author atos
 * @since 1.0
 */
public class SimpleJaxbConfigurationTransformer implements JaxbConfigurationTransformer {

    @Override
    public FileStructureWrapper transform(final Configuration jaxbConfiguration) throws XmlParsingException {
        String name = jaxbConfiguration.getName();
        FileStructureConfiguration configuration = new FileStructureConfiguration(jaxbConfiguration.getKeyValueSeparator(),
                jaxbConfiguration.getLineTerminator(), jaxbConfiguration.getCharset());
        transformAffixes(jaxbConfiguration, configuration);
        return new FileStructureWrapper(name, configuration);
    }

    private void transformAffixes(final Configuration jaxbConfiguration, final FileStructureConfiguration configuration) throws XmlParsingException {
        for (Affix affix : jaxbConfiguration.getRegexAffixOrLiteralAffix()) {
            switch (affix.getPlace()) {
            case FILE:
                configuration.setFileAffix(transformAffix(affix));
                break;
            case KEY:
                configuration.setKeyAffix(transformAffix(affix));
                break;
            case PROPERTY:
                configuration.setPropertyAffix(transformAffix(affix));
                break;
            case VALUE:
                configuration.setValueAffix(transformAffix(affix));
                break;
            default:
                throw new XmlParsingException("There was an unknown place configured in the XML file! '" + affix.getPlace().toString() + "'");
            }
        }
    }

    private com.atos.custpro.configuration.domain.Affix transformAffix(final Affix affix) {
        com.atos.custpro.configuration.domain.Affix result;
        if (affix instanceof RegexAffix) {
            RegexAffix currentAffix = (RegexAffix) affix;
            result = new com.atos.custpro.configuration.domain.affix.RegexAffix(currentAffix.getPrefix(), currentAffix.getSuffix(), currentAffix
                    .getNumOfCapturingGroupsInPrefix().intValue());
        } else {
            LiteralAffix currentAffix = (LiteralAffix) affix;
            result = new com.atos.custpro.configuration.domain.affix.LiteralAffix(currentAffix.getPrefix(), currentAffix.getSuffix());
        }
        return result;
    }

}
