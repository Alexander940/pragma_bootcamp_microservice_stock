package com.pragma.emazon.infrastructure.configuration;

import com.pragma.emazon.domain.api.ICategoryServicePort;
import com.pragma.emazon.domain.spi.ICategoryPersistencePort;
import com.pragma.emazon.domain.usecase.CategoryUseCase;
import com.pragma.emazon.infrastructure.out.jpa.adapter.CategoryJpaAdapter;
import com.pragma.emazon.infrastructure.out.jpa.mapper.CategoryEntityMapper;
import com.pragma.emazon.infrastructure.out.jpa.repository.ICategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {

    private final ICategoryRepository categoryRepository;
    private final CategoryEntityMapper categoryEntityMapper;

    @Bean
    public ICategoryPersistencePort categoryPersistencePort() {
        return new CategoryJpaAdapter(categoryRepository, categoryEntityMapper);
    }

    @Bean
    public ICategoryServicePort categoryServicePort() {
        return new CategoryUseCase(categoryPersistencePort());
    }
}
