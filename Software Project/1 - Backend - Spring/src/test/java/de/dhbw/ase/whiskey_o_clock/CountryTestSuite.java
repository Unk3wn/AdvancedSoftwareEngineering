package de.dhbw.ase.whiskey_o_clock;
import org.junit.platform.suite.api.*;

@Suite
@SuiteDisplayName("A demo Test Suite for Country")
@SelectPackages("de.dhbw.ase.whiskey_o_clock")
@IncludeClassNamePatterns({"^.*Test?$"})
public class CountryTestSuite {
}
