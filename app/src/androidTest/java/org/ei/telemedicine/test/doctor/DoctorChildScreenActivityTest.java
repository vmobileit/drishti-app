package org.ei.telemedicine.test.doctor;


import android.test.ActivityInstrumentationTestCase2;
import android.widget.EditText;

import org.ei.telemedicine.doctor.DoctorChildScreenActivity;
import org.ei.telemedicine.doctor.DoctorFormDataConstants;
import org.ei.telemedicine.R;
import org.json.JSONObject;

public class DoctorChildScreenActivityTest  extends ActivityInstrumentationTestCase2<DoctorChildScreenActivity> {

    DoctorChildScreenActivity doctorChildScreenActivity;
    EditText et_mother_name, et_child_name, et_reporting_date;


    public DoctorChildScreenActivityTest(){
        super(DoctorChildScreenActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        doctorChildScreenActivity = getActivity();
        setUpViews();


    }

    private void setUpViews() {
        et_mother_name = (EditText) doctorChildScreenActivity.findViewById(R.id.et_mother_name);
        et_reporting_date = (EditText) doctorChildScreenActivity.findViewById(R.id.et_reporting_date);
        et_child_name = (EditText) doctorChildScreenActivity.findViewById(R.id.et_child_name);
    }


    public void testDataSetToViews(){
        String wife_name_string = "wife_name";
        String child_name_string = "child_name";
        String child_report_child_disease_date_string = "child_report_child_disease_date";


        JSONObject jsondata = new JSONObject();
        try {
            jsondata.put(DoctorFormDataConstants.wife_name, wife_name_string);
            jsondata.put(DoctorFormDataConstants.child_name, child_name_string);
            jsondata.put(DoctorFormDataConstants.child_report_child_disease_date, child_report_child_disease_date_string);
        }catch (Exception e){
            e.printStackTrace();
        }

        doctorChildScreenActivity.setDatatoViews(jsondata.toString());


        assertEquals(et_mother_name.getText().toString(), wife_name_string);
        assertEquals(et_child_name.getText().toString(),child_name_string);
        assertEquals(et_reporting_date.getText().toString(),child_report_child_disease_date_string);

    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
}
