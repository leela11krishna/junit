package edu.learning;

import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
@SelectPackages("edu.learning.testing") 
public class JUnit5TestSuiteExample 
{
}