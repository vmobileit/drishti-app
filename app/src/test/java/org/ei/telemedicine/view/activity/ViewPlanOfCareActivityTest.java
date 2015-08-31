package org.ei.telemedicine.view.activity;

import android.test.ActivityInstrumentationTestCase2;

import java.util.Date;

import static org.ei.telemedicine.util.FakeContext.setupService;
import static org.ei.telemedicine.util.Wait.waitForFilteringToFinish;
import static org.ei.telemedicine.util.Wait.waitForProgressBarToGoAway;


public class ViewPlanOfCareActivityTest extends ActivityInstrumentationTestCase2<ViewPlanOfCareActivity> {

    private DrishtiSolo solo;
    private FakeUserService userService;

    public ViewPlanOfCareActivityTest(){
        super(ViewPlanOfCareActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        FakeDrishtiService drishtiService = new FakeDrishtiService(String.valueOf(new Date().getTime() - 1));
        userService = new FakeUserService();

        setupService(drishtiService, userService, 100000).updateApplicationContext(getActivity());

        solo = new DrishtiSolo(getInstrumentation(), getActivity());
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        waitForFilteringToFinish();
        waitForProgressBarToGoAway(getActivity());
        solo.finishOpenedActivities();
    }
}
