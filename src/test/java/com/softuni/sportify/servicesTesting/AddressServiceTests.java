package com.softuni.sportify.servicesTesting;

import com.softuni.sportify.domain.entities.Address;
import com.softuni.sportify.domain.models.service_models.AddressServiceModel;
import com.softuni.sportify.exceptions.CreateException;
import com.softuni.sportify.exceptions.UpdateException;
import com.softuni.sportify.repositories.AddressRepository;
import com.softuni.sportify.services.AddressService;
import com.softuni.sportify.services.AddressServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.Validation;
import javax.validation.Validator;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class AddressServiceTests {

    private AddressService addressService;

    @Autowired
    private AddressRepository addressRepository;

    private ModelMapper modelMapper = new ModelMapper();

    private Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Before
    public void init() {
        this.addressService = new AddressServiceImpl(this.addressRepository, this.modelMapper, this.validator);
    }

    @Test
    public void createAddress_whenAddressServiceModelIsValid_addressCreated() throws CreateException {

        AddressServiceModel addressServiceModel = new AddressServiceModel();
        addressServiceModel.setCountry("Netherlands");
        addressServiceModel.setCity("Rotterdam");
        addressServiceModel.setStreet("Vondelpark 1");
        addressServiceModel.setDetails("Center of Amsterdam");

        AddressServiceModel actualAddress = this.addressService.createAddress(addressServiceModel);
        AddressServiceModel expectedAddress = this.modelMapper
                .map(this.addressRepository.findAll().get(0), AddressServiceModel.class);
        assertEquals(expectedAddress.getId(), actualAddress.getId());
    }

    @Test(expected = Exception.class)
    public void createAddress_emptyModel_throwsCreateException() throws CreateException {

        AddressServiceModel addressServiceModel = new AddressServiceModel();
        this.addressService.createAddress(addressServiceModel);
    }

    @Test
    public void editAddress_whenAddressServiceModelIsValid_addressCreated() throws UpdateException {

        Address address = new Address();
        address.setCountry("Bulgaria");
        address.setCity("Sofia");
        address.setStreet("Tzar Boris 3");
        address.setDetails("Do ritualnata");
        this.addressRepository.saveAndFlush(address);

        AddressServiceModel addressServiceModel = new AddressServiceModel();
        addressServiceModel.setId(address.getId());
        addressServiceModel.setCountry("Netherlands");
        addressServiceModel.setCity("Amsterdam");
        addressServiceModel.setStreet("Vondelpark 1");
        addressServiceModel.setDetails("Center of Amsterdam");

        AddressServiceModel actual = this.addressService.editAddress(addressServiceModel);
        AddressServiceModel expected = this.modelMapper
                .map(this.addressRepository.findAll().get(0), AddressServiceModel.class);
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getCountry(), actual.getCountry());
        assertEquals(expected.getCity(), actual.getCity());
        assertEquals(expected.getStreet(), actual.getStreet());
        assertEquals(expected.getDetails(), actual.getDetails());
    }

    @Test(expected = Exception.class)
    public void editAddress_addressServiceModelNotValidDetails_throwsCreateException() throws UpdateException {

        AddressServiceModel addressServiceModel = new AddressServiceModel();
        addressServiceModel.setId("");
        addressServiceModel.setCountry("");
        addressServiceModel.setCity("");
        addressServiceModel.setStreet("");
        addressServiceModel.setDetails("");

        AddressServiceModel actual = this.addressService.editAddress(addressServiceModel);
    }
}
