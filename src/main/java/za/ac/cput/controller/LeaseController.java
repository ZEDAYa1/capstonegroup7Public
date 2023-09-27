package za.ac.cput.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import za.ac.cput.domain.Lease;
import za.ac.cput.factory.LeaseFactory;
import za.ac.cput.service.LeaseService;
import java.util.Set;

@RestController
@RequestMapping("capstonegroup7/lease/")
@Slf4j
public class LeaseController {

    private final LeaseService leaseService;

    @Autowired
    LeaseController(LeaseService leaseService){
        this.leaseService = leaseService;
    }

    @PostMapping("save")
    public ResponseEntity<Lease> save(@RequestBody Lease lease){
        log.info("Save request: {}", lease);
        Lease validatedLease;
        try{
            validatedLease = LeaseFactory.createLease(
                    lease.getLeaseId(),
                    lease.getTerms(),
                    lease.getStartDate(),
                    lease.getEndDate()
            );
        } catch (IllegalArgumentException e){
            log.info("Save request error: {}", e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        Lease save = leaseService.save(validatedLease);
        return ResponseEntity.ok(save);
    }

    @GetMapping("read/{id}")
    public ResponseEntity<Lease> read(@PathVariable String id){
        log.info("Read request: {}", id);
        Lease lease = this.leaseService.read(id);
        return ResponseEntity.ok(lease);
    }

    @PostMapping("update")
    public Lease update(@RequestBody Lease lease){
        return leaseService.update(lease);
    }

    @DeleteMapping("delete{id}")
    public ResponseEntity<Void> delete(@PathVariable String id){
        log.info("Delete request: {}", id);
        this.leaseService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("all")
    public ResponseEntity<Set<Lease>> findAll(){
        Set<Lease> lease = this.leaseService.findAll();
        return ResponseEntity.ok(lease);
    }
}
