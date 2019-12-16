package com.softuni.sportify.services;

import com.softuni.sportify.domain.entities.Address;
import com.softuni.sportify.domain.models.service_models.AddressServiceModel;
import com.softuni.sportify.exceptions.*;
import com.softuni.sportify.repositories.AddressRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.softuni.sportify.constants.ExceptionConstants.*;
import static org.apache.logging.log4j.ThreadContext.isEmpty;

@Service
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    private final ModelMapper modelMapper;
    private final Validator validator;

    @Autowired
    public AddressServiceImpl(AddressRepository addressRepository,
                              ModelMapper modelMapper,
                              Validator validator) {
        this.addressRepository = addressRepository;
        this.modelMapper = modelMapper;
        this.validator = validator;
    }

    @Override
    public AddressServiceModel createAddress(
             AddressServiceModel addressServiceModel) throws CreateException {

        Set<ConstraintViolation<AddressServiceModel>> validate = validator.validate(addressServiceModel);
        if(!validate.isEmpty()) {
            throw new CreateException(ADDRESS_CREATE_EXCEPTION_MSG);
        }
        Address address = this.modelMapper.map(addressServiceModel, Address.class);
        Address newAddress = this.addressRepository.saveAndFlush(address);

        return this.modelMapper.map(newAddress, AddressServiceModel.class);
    }

    @Override
    public AddressServiceModel editAddress(
            AddressServiceModel addressServiceModel) throws UpdateException {

        if(!validator.validate(addressServiceModel).isEmpty()) {
            throw new UpdateException(ADDRESS_UPDATE_EXCEPTION_MSG);
        }
        Address address = this.modelMapper.map(addressServiceModel, Address.class);
        Address updatedAddress = this.addressRepository.saveAndFlush(address);
        return this.modelMapper.map(updatedAddress, AddressServiceModel.class);
    }

    @Override
    public List<AddressServiceModel> findAll() {

        return this.addressRepository.findAll()
                .stream()
                .map(a -> this.modelMapper.map(a, AddressServiceModel.class))
                .collect(Collectors.toList());
    }
}
