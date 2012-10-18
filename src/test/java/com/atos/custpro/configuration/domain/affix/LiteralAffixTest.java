package com.atos.custpro.configuration.domain.affix;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.atos.custpro.configuration.domain.Affix;
import com.atos.custpro.configuration.domain.exception.InvalidFileStructureException;

/**
 * Unit test of {@link LiteralAffix}.
 * @author atos
 *
 */
public class LiteralAffixTest {

    @Test
    public void testLiteralAffixDefaultConstructorShouldReturnEmptyAffix() {
        //GIVEN
        //WHEN
        LiteralAffix literalAffix = new LiteralAffix();
        //THEN
        Assert.assertTrue(literalAffix.getPrefix().isEmpty());
        Assert.assertTrue(literalAffix.getSuffix().isEmpty());
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testLiteralAffixWhenPrefixIsNullShouldThrowException() {
        //GIVEN
        String prefix = null;
        String suffix = "end#";
        //WHEN
        Affix literalAffix = new LiteralAffix(prefix, suffix);
        literalAffix.getClass();
        //THEN throws exception
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testLiteralAffixWhenSuffixIsNullShouldThrowException() {
        //GIVEN
        String prefix = "#start";
        String suffix = null;
        //WHEN
        Affix literalAffix = new LiteralAffix(prefix, suffix);
        literalAffix.getClass();
        //THEN throws exception
    }

    @Test
    public void testPutAffixToTargetWithPrefixAndSuffixShouldReturnAffixedString() {
        //GIVEN
        String prefix = "#start";
        String suffix = "end#";
        Affix underTest = new LiteralAffix(prefix, suffix);
        String target = "input";
        //WHEN
        String result = underTest.putAffixToTarget(target);
        String expected = "#startinputend#";
        //THEN
        Assert.assertEquals(result, expected);
    }

    @Test
    public void testPutAffixToTargetWhenSuffixIsEmptyShouldReturnPrefixedString() {
        //GIVEN
        String prefix = "#start";
        String suffix = "";
        Affix underTest = new LiteralAffix(prefix, suffix);
        String target = "input";
        //WHEN
        String result = underTest.putAffixToTarget(target);
        String expected = "#startinput";
        //THEN
        Assert.assertEquals(result, expected);
    }

    @Test
    public void testPutAffixToTargetWhenPrefixIsEmptyShouldReturnSuffixedString() {
        //GIVEN
        String prefix = "";
        String suffix = "end#";
        Affix underTest = new LiteralAffix(prefix, suffix);
        String target = "input";
        //WHEN
        String result = underTest.putAffixToTarget(target);
        String expected = "inputend#";
        //THEN
        Assert.assertEquals(result, expected);
    }

    @Test
    public void testPutAffixToTargetWhenTargetIsEmptyShouldReturnAffix() {
        //GIVEN
        String prefix = "#start";
        String suffix = "end#";
        Affix underTest = new LiteralAffix(prefix, suffix);
        String target = "";
        //WHEN
        String result = underTest.putAffixToTarget(target);
        String expected = "#startend#";
        //THEN
        Assert.assertEquals(result, expected);
    }

    @Test
    public void testPutAffixToTargetWhenTargetIsNullShouldReturnAffix() {
        //GIVEN
        String prefix = "#start";
        String suffix = "end#";
        Affix underTest = new LiteralAffix(prefix, suffix);
        String target = null;
        //WHEN
        String result = underTest.putAffixToTarget(target);
        String expected = "#startend#";
        //THEN
        Assert.assertEquals(result, expected);
    }

    @Test
    public void testRemoveFromTargetWhenTargetHasTheAffixShouldReturnTargetWithoutAffix() throws InvalidFileStructureException {
        //GIVEN
        String prefix = "#start";
        String suffix = "end#";
        Affix underTest = new LiteralAffix(prefix, suffix);
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
        Affix underTest = new LiteralAffix(prefix, suffix);
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
        Affix underTest = new LiteralAffix(prefix, suffix);
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
        Affix underTest = new LiteralAffix(prefix, suffix);
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
        Affix underTest = new LiteralAffix(prefix, suffix);
        String target = "#startinputend#end#";
        String expected = "inputend#";
        //WHEN
        String result = underTest.removeFromTarget(target);
        //THEN throws exception
        Assert.assertEquals(result, expected);
    }

    @Test
    public void testRemoveFromTargetWhenTargetHasThePrefixTwiceShouldReturnTargetWithoutAffix() throws InvalidFileStructureException {
        //GIVEN
        String prefix = "#start";
        String suffix = "end#";
        Affix underTest = new LiteralAffix(prefix, suffix);
        String target = "#start#startinputend#";
        String expected = "#startinput";
        //WHEN
        String result = underTest.removeFromTarget(target);
        //THEN throws exception
        Assert.assertEquals(result, expected);
    }

    @Test
    public void testRemoveFromTargetWhenTargetHasTheAffixTwiceShouldReturnTargetWithoutAffix() throws InvalidFileStructureException {
        //GIVEN
        String prefix = "#start";
        String suffix = "end#";
        Affix underTest = new LiteralAffix(prefix, suffix);
        String target = "#start#startinputend#end#";
        String expected = "#startinputend#";
        //WHEN
        String result = underTest.removeFromTarget(target);
        //THEN throws exception
        Assert.assertEquals(result, expected);
    }
}
