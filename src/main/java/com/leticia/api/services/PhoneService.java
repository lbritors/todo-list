package com.leticia.api.services;

import com.leticia.api.domain.phone.Phone;
import com.leticia.api.domain.phone.CreatePhoneDTO;
import com.leticia.api.domain.phone.PhoneType;
import com.leticia.api.repositories.PhoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PhoneService {

    @Autowired
    private PhoneRepository phoneRepository;

    public Phone createPhone(CreatePhoneDTO data) {
        return null;
    }

    public void normalizePhoneTypes(Phone phone) {
        phone.setType(PhoneType.valueOf(phone.getType().toString().toLowerCase()));
    }

    public void processPhones(List<Phone> phones) {
        phones.forEach(this::normalizePhoneTypes);
    }
}
