package com.project.demorecord;


import android.content.Context;
import android.os.SystemClock;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.action.ViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.File;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isRoot;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Before
    public void strat(){
        File root = InstrumentationRegistry.getTargetContext().getFilesDir().getParentFile();
        String[] sharedPreferencesFileNames = new File(root, "shared_prefs").list();
        for (String fileName : sharedPreferencesFileNames) {
            InstrumentationRegistry.getTargetContext().getSharedPreferences(fileName.replace(".xml", ""), Context.MODE_PRIVATE).edit().clear().commit();
        }
    }

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void Null1Test() {
        onView(withId(R.id.editTExtName)).check(matches(withText("")));
        onView(withId(R.id.editTextAge)).check(matches(withText("")));

        onView(withId(R.id.buttonAdded)).perform(click());

        onView(withText("Please Enter user info")).check(matches(isDisplayed()));
    }

    @Test
    public void NoNameTest() {
        onView(withId(R.id.editTExtName)).check(matches(withText("")));
        onView(withId(R.id.editTextAge)).perform(replaceText("20"));

        onView(withId(R.id.buttonAdded)).perform(click());

        onView(withText("Please Enter user info")).check(matches(isDisplayed()));
    }

    @Test
    public void NoListTest() {
        onView(withId(R.id.buttonGotoList)).perform(click());
        onView(withText("Not Found")).check(matches(isDisplayed()));
    }

    @Test
    public void NoAgeTest() {
        onView(withId(R.id.editTextAge)).check(matches(withText("")));
        onView(withId(R.id.editTExtName)).perform(replaceText("Ying"));

        onView(withId(R.id.buttonAdded)).perform(click());

        onView(withText("Please Enter user info")).check(matches(isDisplayed()));
    }

    @Test
    public void NameAgeTest1() {
        onView(withId(R.id.editTExtName)).perform(replaceText("Ying"), closeSoftKeyboard());
        onView(withId(R.id.editTextAge)).perform(replaceText("20"), closeSoftKeyboard());

        onView(withId(R.id.buttonAdded)).perform(click());
        onView(withId(R.id.buttonGotoList)).perform(click());

        onView(withRecyclerView(R.id.list, 0)).check(matches(hasDescendant(withText("Ying"))));
        onView(withRecyclerView(R.id.list, 0)).check(matches(hasDescendant(withText("20"))));

        onView(withId(R.id.clearList)).perform(click());
    }

    @Test
    public void NameAgeTest2() {

        onView(withId(R.id.editTExtName)).perform(replaceText("Ying"), closeSoftKeyboard());
        onView(withId(R.id.editTextAge)).perform(replaceText("20"), closeSoftKeyboard());
        onView(withId(R.id.buttonAdded)).perform(click());

        onView(withId(R.id.editTExtName)).perform(replaceText("Ladarat"), closeSoftKeyboard());
        onView(withId(R.id.editTextAge)).perform(replaceText("20"), closeSoftKeyboard());
        onView(withId(R.id.buttonAdded)).perform(click());

        onView(withId(R.id.buttonGotoList)).perform(click());

        onView(withRecyclerView(R.id.list, 1)).check(matches(hasDescendant(withText("Ladarat"))));
        onView(withRecyclerView(R.id.list, 1)).check(matches(hasDescendant(withText("20"))));

    }

    @Test
    public void NameAgeTest3() {
        onView(withId(R.id.editTExtName)).perform(replaceText("Somkait"), closeSoftKeyboard());
        onView(withId(R.id.editTextAge)).perform(replaceText("80"), closeSoftKeyboard());

        onView(withId(R.id.buttonAdded)).perform(click());
        onView(withId(R.id.buttonGotoList)).perform(click());

        onView(withRecyclerView(R.id.list, 2)).check(matches(hasDescendant(withText("Somkait"))));
        onView(withRecyclerView(R.id.list, 2)).check(matches(hasDescendant(withText("80"))));
    }

    @Test
    public void NameAgeTest4() {
        onView(withId(R.id.editTExtName)).perform(replaceText("Prayoch"), closeSoftKeyboard());
        onView(withId(R.id.editTextAge)).perform(replaceText("60"), closeSoftKeyboard());

        onView(withId(R.id.buttonAdded)).perform(click());
        onView(withId(R.id.buttonGotoList)).perform(click());

        onView(withRecyclerView(R.id.list, 3)).check(matches(hasDescendant(withText("Prayoch"))));
        onView(withRecyclerView(R.id.list, 3)).check(matches(hasDescendant(withText("60"))));

        onView(withId(R.id.clearList)).perform(click());
    }

    @Test
    public void NameAgeTest5() {
        onView(withId(R.id.editTExtName)).perform(replaceText("Ying"), closeSoftKeyboard());
        onView(withId(R.id.editTextAge)).perform(replaceText("20"), closeSoftKeyboard());
        onView(withId(R.id.buttonAdded)).perform(click());

        SystemClock.sleep(2000);

        onView(withId(R.id.editTExtName)).perform(replaceText("Somkait"), closeSoftKeyboard());
        onView(withId(R.id.editTextAge)).perform(replaceText("80"), closeSoftKeyboard());
        onView(withId(R.id.buttonAdded)).perform(click());

        SystemClock.sleep(2000);

        onView(withId(R.id.editTExtName)).perform(replaceText("Prayoch"), closeSoftKeyboard());
        onView(withId(R.id.editTextAge)).perform(replaceText("60"), closeSoftKeyboard());
        onView(withId(R.id.buttonAdded)).perform(click());

        SystemClock.sleep(2000);

        onView(withId(R.id.editTExtName)).perform(replaceText("Prayoch"), closeSoftKeyboard());
        onView(withId(R.id.editTextAge)).perform(replaceText("50"), closeSoftKeyboard());

        SystemClock.sleep(2000);

        onView(withId(R.id.buttonAdded)).perform(click());
        onView(withId(R.id.buttonGotoList)).perform(click());

        onView(withRecyclerView(R.id.list, 3)).check(matches(hasDescendant(withText("Prayoch"))));
        onView(withRecyclerView(R.id.list, 3)).check(matches(hasDescendant(withText("50"))));
    }

    private Matcher<View> withRecyclerView(int id, int index) {
        return childAtPosition(childAtPosition(withId(id), index), 0);
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
