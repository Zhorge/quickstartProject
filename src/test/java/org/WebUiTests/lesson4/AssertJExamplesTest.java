package org.WebUiTests.lesson4;

import org.WebUiTests.lesson4.Functions;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class AssertJExamplesTest {

    @Test
    void assertJTest() {
        Assertions.assertThat(Functions.isPalindrome("1235321")).isTrue().isEqualTo(true);

        List<String> stringList = new ArrayList<>(Arrays.asList("test", "test1", "test2"));
        assertThat(stringList).containsAnyOf("test", "testtest");

        assertThat(2).isGreaterThan(1).isLessThan(10);
    }
}
