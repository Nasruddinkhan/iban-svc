package com.mypractice.iban.service.entity.modal;

import javax.persistence.*;

@Table(name = "countries")
@Entity
public class Countries {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long countryId;

    @Column(name = "country_name", length = 50)
    private String countryName;

    @Column(name = "country_2alpha_code", length = 2)
    private String country2AlphaCode;

    @Column(name = "country_3alpha_code", length = 3)
    private String country3AlphaCode;

}
