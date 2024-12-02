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

    @Bean
    public ModelMapper getModelMapper() {
        return new ModelMapper();
    }
}
