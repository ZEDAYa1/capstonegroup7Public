/*
 * IAgentRepository.java
 * Author: Sibusiso Dwayi(220226466)
 * Date: 14 June 2023
 * */
package za.ac.cput.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.Property;

@Repository
public interface IPropertyRepository extends JpaRepository<Property, String> {

}