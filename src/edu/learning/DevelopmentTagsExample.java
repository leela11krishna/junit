package edu.learning;

import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.IncludeTags;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.runner.RunWith;

//@IncludeTags example
/**
 * Run the test cases for only included tags, discard others
 * 
 * @author lgolla2
 *
 */
@RunWith(JUnitPlatform.class)
@SelectPackages("edu.learning.testing")
@IncludeTags("development") // @ExcludeTags is also available
public class DevelopmentTagsExample {
}
