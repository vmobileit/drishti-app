package org.ei.telemedicine.view.activity;

import android.test.ActivityInstrumentationTestCase2;

import org.ei.telemedicine.Context;
import org.ei.telemedicine.util.DrishtiSolo;
import org.ei.telemedicine.util.FakeDrishtiService;
import org.ei.telemedicine.util.FakeUserService;
import org.ei.telemedicine.view.activity.LoginActivity;
import org.ei.telemedicine.view.activity.NativeHomeActivity;

import java.util.Date;

import static org.ei.telemedicine.domain.LoginResponse.SUCCESS;
import static org.ei.telemedicine.util.FakeContext.setupService;
import static org.ei.telemedicine.util.Wait.waitForFilteringToFinish;
import static org.ei.telemedicine.util.Wait.waitForProgressBarToGoAway;


public class NativeHomeActivityTest extends ActivityInstrumentationTestCase2<NativeHomeActivity>
{
    private DrishtiSolo solo;
    private FakeUserService userService;

    public NativeHomeActivityTest()
    {
        super(NativeHomeActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        FakeDrishtiService drishtiService = new FakeDrishtiService(String.valueOf(new Date().getTime() - 1));
        userService = new FakeUserService();

        setupService(drishtiService, userService, 100000).updateApplicationContext(getActivity());
        Context.getInstance().session().setPassword("password");

        solo = new DrishtiSolo(getInstrumentation(), getActivity());
    }




    public void ignoringTestShouldGoBackToLoginScreenWhenLoggedOutWithAbilityToLogBackIn() throws Exception {
        userService.setupFor("user", "password", false, false, SUCCESS);

        solo.logout();
        solo.assertCurrentActivity("Should be in Login screen.", LoginActivity.class);

        userService.setupFor("user", "password", false, false, SUCCESS);
        solo.assertCanLogin("user", "password");
    }

    @Override
    public void tearDown() throws Exception {
        waitForFilteringToFinish();
        waitForProgressBarToGoAway(getActivity());
        solo.finishOpenedActivities();
    }

}
