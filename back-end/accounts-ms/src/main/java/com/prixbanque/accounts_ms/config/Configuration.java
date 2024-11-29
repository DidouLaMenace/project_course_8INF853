package com.prixbanque.accounts_ms.config;

import com.prixbanque.accounts_ms.dto.AccountDTO;
import com.prixbanque.accounts_ms.http.AuthClient;
import com.prixbanque.accounts_ms.model.Account;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

@org.springframework.context.annotation.Configuration
public class Configuration {

    @Autowired
    private AuthClient authClient;

    @Bean
    public ModelMapper getModelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        // Custom mapping from Account to AccountDTO
        modelMapper.addMappings(new PropertyMap<Account, AccountDTO>() {
            @Override
            protected void configure() {
                map().setEmail(authClient.getEmail(source.getUserId()).getBody());
                map().setLastName(source.getLastName());
                map().setFirstName(source.getFirstName());
                map().setUserId(source.getUserId());
            }
        });

        return modelMapper;
    }
}
