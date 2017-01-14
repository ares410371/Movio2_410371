package cz.muni.fi.pv256.movio2.uco_410371;


import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import cz.muni.fi.pv256.movio2.uco_410371.movies.MoviesActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@RunWith(AndroidJUnit4.class)
public class MovioUITest {

    @Rule
    public ActivityTestRule<MoviesActivity> mActivityTestRule = new ActivityTestRule<>(MoviesActivity.class);

    @Test
    public void baseTest() {
        onView(withId(R.id.movies_recycler_view)).check(matches(isDisplayed()));

        onView(allOf(withId(R.id.action_toggle), withText(R.string.action_toogle_on)))
                .check(matches(isDisplayed()))
                .perform(click());

        onView(allOf(withId(R.id.action_toggle), withText(R.string.action_toggle_off)))
                .check(matches(isDisplayed()));

    }
//
//    @Test
//    public void detailTest() {
//        onView(withId(R.id.horizontal_recycler_view))
//                .check(matches(isDisplayed()))
//                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
//
//        onView(withId(R.id.image_movie_poster))
//                .check(matches(isDisplayed()));
//
//        onView(withId(R.id.image_movie_back_poster))
//                .check(matches(isDisplayed()));
//
//        onView(withId(R.id.text_movie_title_expanded))
//                .check(matches(isDisplayed()));
//    }

}
