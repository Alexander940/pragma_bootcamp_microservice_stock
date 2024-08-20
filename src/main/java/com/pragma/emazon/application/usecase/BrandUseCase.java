package com.pragma.emazon.application.usecase;

import com.pragma.emazon.application.exception.MandatoryParameterException;
import com.pragma.emazon.application.exception.ObjectAlreadyExistsException;
import com.pragma.emazon.application.exception.StringTooLongException;
import com.pragma.emazon.application.util.StringUtil;
import com.pragma.emazon.domain.api.IBrandServicePort;
import com.pragma.emazon.domain.model.Brand;
import com.pragma.emazon.domain.spi.IBrandPersistencePort;

public class BrandUseCase implements IBrandServicePort {

    private final IBrandPersistencePort brandPersistencePort;

    public BrandUseCase(IBrandPersistencePort brandPersistencePort) {
        this.brandPersistencePort = brandPersistencePort;
    }

    @Override
    public Brand saveBrand(Brand brand) {
        //This exception is thrown if the brand name long is up to 50 characters
        if(StringUtil.assessHigherLength(brand.getName(), 50)){
            throw new StringTooLongException(brand.getName(), 50);
        }

        //This exception is thrown if the brand description long is up to 120 characters
        if(StringUtil.assessHigherLength(brand.getDescription(), 120)){
            throw new StringTooLongException(brand.getDescription(), 120);
        }

        //This exception is thrown if the brand description is empty or null
        if(brand.getDescription().isEmpty() || brand.getDescription() == null){
            throw new MandatoryParameterException("description");
        }

        //This exception is thrown if the brand name already exists
        if(findBrandByName(brand.getName()) != null){
            throw new ObjectAlreadyExistsException(brand, "name");
        }

        return brandPersistencePort.saveBrand(brand);
    }

    @Override
    public Brand findBrandByName(String name) {
        return brandPersistencePort.findBrandByName(name);
    }
}
