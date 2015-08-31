package org.ei.telemedicine.test.repository;

import android.test.AndroidTestCase;
import android.test.RenamingDelegatingContext;

import org.ei.telemedicine.doctor.DoctorData;
import org.ei.telemedicine.repository.DoctorRepository;
import org.ei.telemedicine.repository.Repository;
import org.ei.telemedicine.util.Session;

import java.util.Date;


public class DoctorRepositoryTest extends AndroidTestCase {
    private DoctorRepository repository;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        repository = new DoctorRepository();

        Session session = new Session().setPassword("password").setRepositoryName("drishti.db" + new Date().getTime());
        new Repository(new RenamingDelegatingContext(getContext(), "test_"), session, repository);


    }

    public void testInsertDoctor() throws Exception
    {
        String caseId = "Case_V";
        DoctorData doctorData = new DoctorData();
        doctorData.setCaseId(caseId);

        repository.add(doctorData);

        assertTrue(repository.isExistCaseId(caseId));
    }

    public void testUpdateDoctorDetails() throws Exception
    {
        String caseId = "Case_V";
        String pocInformation = "pocInformation";
        String pocPendingInfo = "pocPendingInfo";

        repository.updateDetails(caseId, pocInformation, pocPendingInfo);

        assertEquals(repository.getPocInfo(caseId), caseId);
    }


    public void testDeleteCaseID() throws Exception
    {
        String caseId = "Case_D";
        DoctorData doctorData = new DoctorData();
        doctorData.setCaseId(caseId);

        repository.add(doctorData);

        repository.deleteUseCaseId(caseId);

        assertFalse(repository.isExistCaseId(caseId));


    }

    public void testDeleteAllData() throws Exception
    {
        repository.deleteAll();

        assertNull(repository.allConsultantsData());
    }


}
