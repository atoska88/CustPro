package com.atos.custpro.configuration.domain.affix;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.atos.custpro.configuration.domain.Affix;
import com.atos.custpro.configuration.domain.exception.InvalidFileStructureException;

/**
 * Unit test of class {@link RegexAffix}.
 * @author atos
 *
 */
public class RegexAffixTest {
    @Test
    public void testRegexAffixDefaultConstructorShouldReturnEmptyAffix() {
        //GIVEN
        //WHEN
        RegexAffix regexAffix = new RegexAffix();
        //THEN
        Assert.assertTrue(regexAffix.getPrefix().isEmpty());
        Assert.assertTrue(regexAffix.getSuffix().isEmpty());
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testRegexAffixWhenPrefixIsNullShouldThrowException() {
        //GIVEN
        String prefix = null;
        String suffix = "end#";
        //WHEN
        Affix literalAffix = new RegexAffix(prefix, suffix, 0);
        literalAffix.getClass();
        //THEN throws exception
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testRegexAffixWhenSuffixIsNullShouldThrowException() {
        //GIVEN
        String prefix = "#start";
        String suffix = null;
        //WHEN
        Affix literalAffix = new RegexAffix(prefix, suffix, 0);
        literalAffix.getClass();
        //THEN throws exception
    }

    @Test(expectedExceptions = UnsupportedOperationException.class)
    public void testPutAffixToTargetShouldThrowUnsupportedException() {
        //GIVEN
        String prefix = "#start";
        String suffix = "end#";
        Affix underTest = new RegexAffix(prefix, suffix, 0);
        String target = "input";
        //WHEN
        underTest.putAffixToTarget(target);
        //THEN throws exception
    }

    @Test
    public void testValidateTargetWhenTargetHasTheAffixShouldNotThrowException() throws InvalidFileStructureException {
        //GIVEN
        String prefix = "#start";
        String suffix = "end#";
        Affix underTest = new RegexAffix(prefix, suffix, 0);
        String target = "#startinputend#";
        //WHEN
        underTest.validateTarget(target);
        //THEN
    }

    @Test(expectedExceptions = InvalidFileStructureException.class)
    public void testValidateTargetWhenTargetDoesNotHaveThePrefixShouldThrowException() throws InvalidFileStructureException {
        //GIVEN
        String prefix = "#start";
        String suffix = "end#";
        Affix underTest = new RegexAffix(prefix, suffix, 0);
        String target = "#starinputend#";
        //WHEN
        underTest.validateTarget(target);
        //THEN throws exception
    }

    @Test
    public void testRemoveFromTargetWhenTargetHasTheAffixShouldReturnTargetWithoutAffix() throws InvalidFileStructureException {
        //GIVEN
        String prefix = "#start";
        String suffix = "end#";
        Affix underTest = new RegexAffix(prefix, suffix, 0);
        String target = "#startinputend#";
        String expected = "input";
        //WHEN
        String result = underTest.removeFromTarget(target);
        //THEN
        Assert.assertEquals(result, expected);
    }

    @Test
    public void testRemoveFromTargetWhenTargetIsTheAffixShouldReturnEmptyString() throws InvalidFileStructureException {
        //GIVEN
        String prefix = "#start";
        String suffix = "end#";
        Affix underTest = new RegexAffix(prefix, suffix, 0);
        String target = "#startend#";
        String expected = "";
        //WHEN
        String result = underTest.removeFromTarget(target);
        //THEN
        Assert.assertEquals(result, expected);
        Assert.assertTrue(result.isEmpty());
    }

    @Test(expectedExceptions = InvalidFileStructureException.class)
    public void testRemoveFromTargetWhenTargetDoesNotHaveThePrefixShouldThrowException() throws InvalidFileStructureException {
        //GIVEN
        String prefix = "#start";
        String suffix = "end#";
        Affix underTest = new RegexAffix(prefix, suffix, 0);
        String target = "#starinputend#";
        //WHEN
        underTest.removeFromTarget(target);
        //THEN throws exception
    }

    @Test(expectedExceptions = InvalidFileStructureException.class)
    public void testRemoveFromTargetWhenTargetDoesNotHaveTheSuffixShouldThrowException() throws InvalidFileStructureException {
        //GIVEN
        String prefix = "#start";
        String suffix = "end#";
        Affix underTest = new RegexAffix(prefix, suffix, 0);
        String target = "#startinputnd#";
        //WHEN
        underTest.removeFromTarget(target);
        //THEN throws exception
    }

    @Test
    public void testRemoveFromTargetWhenTargetHasTheSuffixTwiceShouldReturnTargetWithoutAffix() throws InvalidFileStructureException {
        //GIVEN
        String prefix = "#start";
        String suffix = "end#";
        Affix underTest = new RegexAffix(prefix, suffix, 0);
        String target = "#startinputend#end#";
        String expected = "inputend#";
        //WHEN
        String result = underTest.removeFromTarget(target);
        //THEN
        Assert.assertEquals(result, expected);
    }

    @Test
    public void testRemoveFromTargetWhenTargetHasThePrefixTwiceShouldReturnTargetWithoutAffix() throws InvalidFileStructureException {
        //GIVEN
        String prefix = "#start";
        String suffix = "end#";
        Affix underTest = new RegexAffix(prefix, suffix, 0);
        String target = "#start#startinputend#";
        String expected = "#startinput";
        //WHEN
        String result = underTest.removeFromTarget(target);
        //THEN
        Assert.assertEquals(result, expected);
    }

    @Test
    public void testRemoveFromTargetWhenTargetHasTheAffixTwiceShouldReturnTargetWithoutAffix() throws InvalidFileStructureException {
        //GIVEN
        String prefix = "#start";
        String suffix = "end#";
        Affix underTest = new RegexAffix(prefix, suffix, 0);
        String target = "#start#startinputend#end#";
        String expected = "#startinputend#";
        //WHEN
        String result = underTest.removeFromTarget(target);
        //THEN
        Assert.assertEquals(result, expected);
    }

    @Test
    public void testRemoveFromTargetWhenPrefixHasAnOptionalPartShouldReturnTargetWithoutAffix() throws InvalidFileStructureException {
        //GIVEN
        String prefix = "#start{0,1}";
        String suffix = "end#";
        Affix underTest = new RegexAffix(prefix, suffix, 0);
        String targetWithPart = "#startinputend#";
        String targetWithoutPart = "#starinputend#";
        String expected = "input";
        //WHEN
        String resultWithPart = underTest.removeFromTarget(targetWithPart);
        String resultWithoutPart = underTest.removeFromTarget(targetWithoutPart);
        //THEN
        Assert.assertEquals(resultWithPart, expected);
        Assert.assertEquals(resultWithoutPart, expected);
    }

    @Test
    public void testRemoveFromTargetWhenSuffixHasAnOptionalPartShouldReturnTargetWithoutAffix() throws InvalidFileStructureException {
        //GIVEN
        String prefix = "#start";
        String suffix = "end#{0,1}";
        Affix underTest = new RegexAffix(prefix, suffix, 0);
        String targetWithPart = "#startinputend#";
        String targetWithoutPart = "#startinputend";
        String expected = "input";
        //WHEN
        String resultWithPart = underTest.removeFromTarget(targetWithPart);
        String resultWithoutPart = underTest.removeFromTarget(targetWithoutPart);
        //THEN
        Assert.assertEquals(resultWithPart, expected);
        Assert.assertEquals(resultWithoutPart, expected);
    }

    @Test(expectedExceptions = InvalidFileStructureException.class)
    public void testRemoveFromTargetWhenPrefixHasAnOptionalPartAndTargetHasErrorShouldThrowException() throws InvalidFileStructureException {
        //GIVEN
        String prefix = "#start{0,1}";
        String suffix = "end#";
        Affix underTest = new RegexAffix(prefix, suffix, 0);
        String targetWithPart = "#startinputend#";
        String targetWithoutPart = "#starinputend";
        //WHEN
        underTest.removeFromTarget(targetWithPart);
        underTest.removeFromTarget(targetWithoutPart);
        //THEN throws exception
    }

    @Test
    public void testRemoveFromTargetWhenPrefixHasTwoGroupsShouldReturnTargetWithoutAffix() throws InvalidFileStructureException {
        //GIVEN
        String prefix = "(#)(start)";
        String suffix = "end#";
        Affix underTest = new RegexAffix(prefix, suffix, 2);
        String target = "#startinputend#";
        String expected = "input";
        //WHEN
        String result = underTest.removeFromTarget(target);
        //THEN
        Assert.assertEquals(result, expected);
    }

    @Test
    public void testRemoveFromTargetWhenPrefixAndSuffixHasTwoGroupsShouldReturnTargetWithoutAffix() throws InvalidFileStructureException {
        //GIVEN
        String prefix = "(#)(start)";
        String suffix = "(e)(nd#)";
        Affix underTest = new RegexAffix(prefix, suffix, 2);
        String target = "#startinputend#";
        String expected = "input";
        //WHEN
        String result = underTest.removeFromTarget(target);
        //THEN
        Assert.assertEquals(result, expected);
    }

    @Test
    public void testRemoveFromTargetWhenTargetContainsRegexShouldReturnTargetWithoutAffix() throws InvalidFileStructureException {
        //GIVEN
        String prefix = "(#)(start)";
        String suffix = "(e)(nd#)";
        Affix underTest = new RegexAffix(prefix, suffix, 2);
        String target = "#startin.*putend#";
        String expected = "in.*put";
        //WHEN
        String result = underTest.removeFromTarget(target);
        //THEN
        Assert.assertEquals(result, expected);
    }
}
