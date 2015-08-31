package org.ei.telemedicine.test.doctor;


import android.test.ActivityInstrumentationTestCase2;
import android.widget.EditText;

import org.ei.telemedicine.R;
import org.ei.telemedicine.doctor.DoctorANCScreenActivity;
import org.ei.telemedicine.doctor.DoctorFormDataConstants;
import org.json.JSONObject;

public class DoctorANCScreenActivityTest extends ActivityInstrumentationTestCase2<DoctorANCScreenActivity> {

    DoctorANCScreenActivity doctorANCScreenActivity;
    EditText et_anc_num, et_woman_name, et_anc_visit_date, et_other_risks, et_bp_sys, et_bp_dia, et_temp, et_bloodGlucose, et_fetal;


    public DoctorANCScreenActivityTest(){
        super(DoctorANCScreenActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        doctorANCScreenActivity = getActivity();
        setUpViews();


    }

    private void setUpViews() {
        et_anc_num = (EditText) doctorANCScreenActivity.findViewById(R.id.et_anc_num);
        et_woman_name = (EditText) doctorANCScreenActivity.findViewById(R.id.et_woman_name);
        et_anc_visit_date = (EditText) doctorANCScreenActivity.findViewById(R.id.et_anc_visit_date);
        et_bp_sys = (EditText) doctorANCScreenActivity.findViewById(R.id.et_sysstolic);
        et_bp_dia = (EditText) doctorANCScreenActivity.findViewById(R.id.et_diastolic);
        et_temp = (EditText) doctorANCScreenActivity.findViewById(R.id.et_temperature);
        et_bloodGlucose = (EditText) doctorANCScreenActivity.findViewById(R.id.et_blood_glucose);
        et_fetal = (EditText) doctorANCScreenActivity.findViewById(R.id.et_fetal_movement);
        et_other_risks = (EditText) doctorANCScreenActivity.findViewById(R.id.et_other_risk_symptoms);
    }


    public void testDataSetToViews(){
        String et_anc_num_string = "et_anc_num_string";
        String et_woman_name_string = "et_woman_name_string";
        String et_anc_visit_date_string = "et_anc_visit_date_string";
        String et_bp_sys__string = "et_bp_sys__string";
        String et_temp_string = "et_temp";
        String bp_dia_string = "bp_dia";
        String et_bloodGlucose_string = "et_bloodGlucose";
        String et_fetal_string= "et_fetal";
        String risk_symptoms_string = "risk_symptoms";


        JSONObject jsondata = new JSONObject();
        try {
            jsondata.put(DoctorFormDataConstants.anc_visit_number, et_anc_num_string);
            jsondata.put(DoctorFormDataConstants.wife_name, et_woman_name_string);
            jsondata.put(DoctorFormDataConstants.anc_visit_date, et_anc_visit_date_string);
            jsondata.put(DoctorFormDataConstants.bp_sys, et_bp_sys__string);
            jsondata.put(DoctorFormDataConstants.bp_dia, bp_dia_string);
            jsondata.put(DoctorFormDataConstants.temp_data, et_temp_string);
            jsondata.put(DoctorFormDataConstants.blood_glucose, et_bloodGlucose_string);
            jsondata.put(DoctorFormDataConstants.fetal_data, et_fetal_string);
            jsondata.put(DoctorFormDataConstants.risk_symptoms, risk_symptoms_string);
        }catch (Exception e){
            e.printStackTrace();
        }

        doctorANCScreenActivity.setDatatoViews(jsondata.toString());



        assertEquals(et_anc_num.getText().toString(), et_anc_num_string);
        assertEquals(et_woman_name.getText().toString(),et_woman_name_string);
        assertEquals(et_anc_visit_date.getText().toString(),et_anc_visit_date_string);
        assertEquals(et_bp_sys.getText().toString(),et_bp_sys__string);
        assertEquals(et_bp_dia.getText().toString(),bp_dia_string);
        assertEquals(et_temp.getText().toString(),et_temp_string);
        assertEquals(et_bloodGlucose.getText().toString(),et_bloodGlucose_string);
        assertEquals(et_fetal.getText().toString(),et_fetal_string);
        assertEquals(et_other_risks.getText().toString(),risk_symptoms_string);

    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
}
