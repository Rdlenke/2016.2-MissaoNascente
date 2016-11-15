package gov.jbb.missaonascente;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import gov.jbb.missaonascente.view.RegisterScreenActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasErrorText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;


@LargeTest
public class RegisterAcceptanceTest {
    @Rule
    public final ActivityTestRule<RegisterScreenActivity> register = new ActivityTestRule<>(RegisterScreenActivity.class);

    private RegisterScreenActivity activity;

    @Before
    public void setup(){
        activity = register.getActivity();
    }


    @Test
    public void registerUserNicknameInvalid(){
        onView(withId(R.id.nicknameEditText))
                .perform(typeText("aa"));
        onView(withId(R.id.passwordEditText))
                .perform(typeText("senha1234"));
        onView(withId(R.id.passwordConfirmEditText))
                .perform(typeText("senha1234"))
                .perform(closeSoftKeyboard());
        onView(withId(R.id.emailEditText))
                .perform(typeText("testuser@gmail.com"))
                .perform(closeSoftKeyboard());
        onView(withId(R.id.registerButton))
                .perform(click());
        onView(withId(R.id.nicknameEditText)).check(matches(hasErrorText(activity.getString(R.string.nicknameValidation))));
    }

    @Test
    public void registerUserPasswordInvalid(){
        new ActivityTestRule<>(RegisterScreenActivity.class);

        onView(withId(R.id.nicknameEditText))
                .perform(typeText("testuser"));
        onView(withId(R.id.passwordEditText))
                .perform(typeText("!*"));
        onView(withId(R.id.passwordConfirmEditText))
                .perform(typeText("senha1234"))
                .perform(closeSoftKeyboard());
        onView(withId(R.id.emailEditText))
                .perform(typeText("testuser@gmail.com"))
                .perform(closeSoftKeyboard());
        onView(withId(R.id.registerButton))
                .perform(click());
        onView(withId(R.id.passwordEditText)).check(matches(hasErrorText(activity.getString(R.string.passwordValidation))));
    }

    @Test
    public void registerUserConfirmPasswordInvalid(){
        new ActivityTestRule<>(RegisterScreenActivity.class);

        onView(withId(R.id.nicknameEditText))
                .perform(typeText("testuser"));
        onView(withId(R.id.passwordEditText))
                .perform(typeText("senha1234"));
        onView(withId(R.id.passwordConfirmEditText))
                .perform(typeText("!*"))
                .perform(closeSoftKeyboard());
        onView(withId(R.id.emailEditText))
                .perform(typeText("testuser@gmail.com"))
                .perform(closeSoftKeyboard());
        onView(withId(R.id.registerButton))
                .perform(click());
        onView(withId(R.id.passwordConfirmEditText)).check(matches(hasErrorText(activity.getString(R.string.passwordConfirmValidation))));
    }

    @Test
    public void registerUserEmailInvalid(){
        new ActivityTestRule<>(RegisterScreenActivity.class);

        onView(withId(R.id.nicknameEditText))
                .perform(typeText("testuser"));
        onView(withId(R.id.passwordEditText))
                .perform(typeText("senha1234"));
        onView(withId(R.id.passwordConfirmEditText))
                .perform(typeText("senha1234"))
                .perform(closeSoftKeyboard());
        onView(withId(R.id.emailEditText))
                .perform(typeText("t@"))
                .perform(closeSoftKeyboard());
        onView(withId(R.id.registerButton))
                .perform(click());
        onView(withId(R.id.emailEditText)).check(matches(hasErrorText(activity.getString(R.string.invalidEmail))));
    }
}
