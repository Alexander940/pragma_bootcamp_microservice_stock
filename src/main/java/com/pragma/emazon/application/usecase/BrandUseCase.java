package com.pragma.emazon.application.usecase;

import com.pragma.emazon.application.exception.MandatoryParameterException;
import com.pragma.emazon.application.exception.StringTooLongException;
import com.pragma.emazon.application.util.BrandUtil;
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
        //This exception is thrown if the brand name or description is too long
        if(BrandUtil.isBrandNameTooLong(brand.getName()) || BrandUtil.isBrandDescriptionTooLong(brand.getDescription())){
            throw new StringTooLongException();
        }

        //This exception is thrown if the brand description is empty
        if(brand.getDescription().isEmpty() || brand.getDescription() == null){
            throw new MandatoryParameterException();
        }

        return brandPersistencePort.saveBrand(brand);
    }
}
