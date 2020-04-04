package edu.learning;

import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.IncludeTags;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.runner.RunWith;

//@IncludeTags example
@RunWith(JUnitPlatform.class)
@SelectPackages("edu.learning.testing")
@IncludeTags("production")
public class ProductionTagsExample {
}
