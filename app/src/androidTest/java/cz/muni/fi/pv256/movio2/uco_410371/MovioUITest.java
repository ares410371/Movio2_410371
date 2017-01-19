package cz.muni.fi.pv256.movio2.uco_410371;

import android.os.SystemClock;
import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import cz.muni.fi.pv256.movio2.uco_410371.movies.MoviesActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@RunWith(AndroidJUnit4.class)
public class MovioUITest {

    @Rule
    public ActivityTestRule<MoviesActivity> mActivityTestRule = new ActivityTestRule<>(MoviesActivity.class);

    @Test
    public void toggleButtonTest() {
        onView(withId(R.id.movies_recycler_view)).check(matches(isDisplayed()));

        onView(allOf(withId(R.id.action_toggle), withText(R.string.action_toogle_on)))
                .check(matches(isDisplayed()))
                .perform(click());

        onView(allOf(withId(R.id.action_toggle), withText(R.string.action_toggle_off)))
                .check(matches(isDisplayed()));

    }

    @Test
    public void detailTest() {
        SystemClock.sleep(5000); // Not always suffice 5 seconds

        ViewInteraction recyclerView = onView(
                allOf(withId(R.id.horizontal_recycler_view),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.movies_recycler_view),
                                        0),
                                1),
                        isDisplayed()));
        recyclerView.check(matches(isDisplayed()));

        ViewInteraction toggleButton = onView(
                allOf(withId(R.id.action_toggle),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.action_bar),
                                        1),
                                0),
                        isDisplayed()));
        toggleButton.check(matches(isDisplayed()));

        recyclerView.perform(actionOnItemAtPosition(0, click()));

        ViewInteraction imageViewPoster = onView(withId(R.id.image_movie_poster));
        imageViewPoster.check(matches(isDisplayed()));

        ViewInteraction imageViewBackDrop = onView(withId(R.id.image_movie_back_poster));
        imageViewBackDrop.check(matches(isDisplayed()));

        ViewInteraction textViewTitle = onView(withId(R.id.text_movie_title_expanded));
        textViewTitle.check(matches(isDisplayed()));

        ViewInteraction floatingActionButton = onView(withId(R.id.fab_detail));
        floatingActionButton.perform(click());

        pressBack();

        ViewInteraction toggleButton2 = onView(allOf(
                withId(R.id.action_toggle), withText(R.string.action_toogle_on),isDisplayed()));
        toggleButton2.perform(click());

        ViewInteraction relativeLayout = onView(
                allOf(childAtPosition(
                        allOf(withId(R.id.movies_recycler_view),
                                childAtPosition(
                                        withId(R.id.movies_container),
                                        0)),
                        0),
                        isDisplayed()));
        relativeLayout.check(matches(isDisplayed()));

        ViewInteraction imageViewVerticalPoster = onView(
                allOf(withId(R.id.image_item_movie_vert_poster),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.movies_recycler_view),
                                        0),
                                0),
                        isDisplayed()));
        imageViewVerticalPoster.check(matches(isDisplayed()));

    }

    private static Matcher<View> childAtPosition(final Matcher<View> parentMatcher, final int position) {
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
