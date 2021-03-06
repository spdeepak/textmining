package com.textmining.core.util;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:/spring/applicationContext.xml" })
public class StringUtilsTest {

    @Resource
    private StringUtils stringUtils;

    @Test
    public void testFetchCountOfWordsBySize() {
        String[] string = { "Hello how are you?", "I am fine. How about you?", "Me too." };
        Map<Integer, Long> map = stringUtils.fetchCountOfWordsBySize(string);
        assertThat(1L, is(equalTo(map.get(1))));
        assertThat(2L, is(equalTo(map.get(2))));
        assertThat(6L, is(equalTo(map.get(3))));
        assertThat(1L, is(equalTo(map.get(4))));
        assertThat(2L, is(equalTo(map.get(5))));
    }

    @Test
    public void testFetchWordsBasedOnSize() {
        String[] string = { "Hello how are you?", "I am fine. How about you?", "Me too." };
        assertEquals(2, stringUtils.fetchWordsBasedOnSize(string, 2).size());
        assertThat(1L, is(equalTo(stringUtils.fetchWordsBasedOnSize(string, 2).get("am"))));
        assertThat(1L, is(equalTo(stringUtils.fetchWordsBasedOnSize(string, 2).get("me"))));
    }

    @Test
    public void testNumberOfSentences() {
        String[] str = { "Hello.you.Hello.you.", "Hello.you.Hello.you." };
        assertEquals(8, stringUtils.numberOfSentences(str));
    }

    @Test
    public void testSimilarWordCountArray() {
        String[] str = { "Hello how do you do! ' ' ", "I am fine how are you " };
        assertEquals(8, stringUtils.similarWordCount(str).size());
        assertEquals(1, stringUtils.similarWordCount(str).get("hello").intValue());
        assertEquals(2, stringUtils.similarWordCount(str).get("how").intValue());
        assertEquals(2, stringUtils.similarWordCount(str).get("do").intValue());
        assertEquals(2, stringUtils.similarWordCount(str).get("you").intValue());
        assertEquals(1, stringUtils.similarWordCount(str).get("i").intValue());
        assertEquals(1, stringUtils.similarWordCount(str).get("am").intValue());
        assertEquals(1, stringUtils.similarWordCount(str).get("fine").intValue());
        assertEquals(1, stringUtils.similarWordCount(str).get("are").intValue());
    }

    @Test
    public void testWordCount() {
        String[] string = { "Hello how do you do! ' ' ", "I am fine how are you?" };
        assertThat(stringUtils.totalWordCount(string), is(equalTo(11L)));
    }

}
