package com.car.castel.Insurersservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@EnableScheduling
public class LicenceNoService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private LicenceNoRepository licenceNoRepository;

    public static final String BOOKING_HOST = "http://BOOKIING-SERVICE/booking-service/api";

    @Scheduled(fixedRate = 300000)
    public void updateBooking() {

        String fooResourceUrl = BOOKING_HOST +"/fake-claim";
        URI uri = UriComponentsBuilder.fromUriString(fooResourceUrl)
                .build()
                .toUri();

        List<String> collect = licenceNoRepository.findAll().stream().distinct().map(licenceNo -> licenceNo.getLicenceNo()).collect(Collectors.toList());
        ResponseEntity<Object> response = restTemplate.postForEntity(uri ,collect, Object.class);

    }


    /**
     * creating sample data to initialize the database for fraud insurance claims
     */
    public void createSampleData() {
        LicenceNo licenceNo1 = new LicenceNo();
        LicenceNo licenceNo2 = new LicenceNo();
        LicenceNo licenceNo3 = new LicenceNo();
        LicenceNo licenceNo4 = new LicenceNo();
        LicenceNo licenceNo5 = new LicenceNo();
        LicenceNo licenceNo6 = new LicenceNo();
        LicenceNo licenceNo7 = new LicenceNo();
        LicenceNo licenceNo8 = new LicenceNo();


        licenceNo1.setLicenceNo("B826456");
        licenceNo2.setLicenceNo("B523346");
        licenceNo3.setLicenceNo("B131450");
        licenceNo4.setLicenceNo("B567856");
        licenceNo5.setLicenceNo("B689880");
        licenceNo6.setLicenceNo("B567789");
        licenceNo7.setLicenceNo("B345678");
        licenceNo8.setLicenceNo("B131399");
        List<LicenceNo> licenceNoList = new ArrayList<>();
        licenceNoList.add(licenceNo1);
        licenceNoList.add(licenceNo2);
        licenceNoList.add(licenceNo3);
        licenceNoList.add(licenceNo4);
        licenceNoList.add(licenceNo5);
        licenceNoList.add(licenceNo6);
        licenceNoList.add(licenceNo7);
        licenceNoList.add(licenceNo8);

        List<LicenceNo> result = this.licenceNoRepository.saveAll(licenceNoList);
    }
}
