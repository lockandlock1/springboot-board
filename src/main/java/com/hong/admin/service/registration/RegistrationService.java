package com.hong.admin.service.registration;

import com.hong.admin.domain.registration.RegistrationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RegistrationService {

    private final RegistrationRepository registrationRepository;


}
