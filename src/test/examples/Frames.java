package test.examples;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import test.base.Base;

public class Frames extends Base {

    @Tag("frameSwitching()")
    @DisplayName("frameSwitching()")
    @Test
    public void frameSwitching() {
        driver.get("https://the-internet.herokuapp.com/nested_frames");

        // "BOTTOM"
        driver.switchTo().frame(1);

        // Move up one context
        driver.switchTo().parentFrame();

        // ""
        driver.switchTo().frame("frame-top");

        // "LEFT"
        driver.switchTo().frame("frame-left");

        // Move back to top context
        driver.switchTo().defaultContent();
    }

}

