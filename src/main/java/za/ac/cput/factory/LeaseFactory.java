package za.ac.cput.factory;

import za.ac.cput.domain.Lease;
import za.ac.cput.util.Helper;
import java.time.LocalDate;


public class LeaseFactory {
    public static Lease createLease(String leaseId, String terms, LocalDate startDate, LocalDate endDate){
        if(Helper.isNullOrEmpty(terms)
        ||Helper.isDateNull(startDate)
        ||Helper.isDateNull(endDate)){
            return null;
        }


        leaseId = Helper.generateId();

        Lease lease = new Lease.Builder().setLeaseId(leaseId)
                .setTerms(terms)
                .setStartDate(startDate)
                .setEndDate(endDate)
                .build();
        return lease;
    }
}
