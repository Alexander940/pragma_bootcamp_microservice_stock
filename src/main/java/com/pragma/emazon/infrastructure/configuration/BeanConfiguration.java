package com.pragma.emazon.infrastructure.configuration;

import com.pragma.emazon.domain.api.IBrandServicePort;
import com.pragma.emazon.domain.api.ICategoryServicePort;
import com.pragma.emazon.domain.spi.IBrandPersistencePort;
import com.pragma.emazon.domain.spi.ICategoryPersistencePort;
import com.pragma.emazon.domain.usecase.BrandUseCase;
import com.pragma.emazon.domain.usecase.CategoryUseCase;
import com.pragma.emazon.infrastructure.out.jpa.adapter.BrandJpaAdapter;
import com.pragma.emazon.infrastructure.out.jpa.adapter.CategoryJpaAdapter;
import com.pragma.emazon.infrastructure.out.jpa.mapper.BrandEntityMapper;
import com.pragma.emazon.infrastructure.out.jpa.mapper.CategoryEntityMapper;
import com.pragma.emazon.infrastructure.out.jpa.mapper.PageAdapterMapper;
import com.pragma.emazon.infrastructure.out.jpa.repository.IBrandRepository;
import com.pragma.emazon.infrastructure.out.jpa.repository.ICategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {

    private final ICategoryRepository categoryRepository;
    private final CategoryEntityMapper categoryEntityMapper;
    private final PageAdapterMapper pageAdapterMapper;
    private final IBrandRepository brandRepository;
    private final BrandEntityMapper brandEntityMapper;

    @Bean
    public ICategoryPersistencePort categoryPersistencePort() {
        return new CategoryJpaAdapter(categoryRepository, categoryEntityMapper, pageAdapterMapper);
    }

    @Bean
    public ICategoryServicePort categoryServicePort() {
        return new CategoryUseCase(categoryPersistencePort());
    }

    @Bean
    public IBrandPersistencePort brandPersistencePort() {
        return new BrandJpaAdapter(brandRepository, brandEntityMapper, pageAdapterMapper);
    }

    @Bean
    public IBrandServicePort brandServicePort() {
        return new BrandUseCase(brandPersistencePort());
    }
}
