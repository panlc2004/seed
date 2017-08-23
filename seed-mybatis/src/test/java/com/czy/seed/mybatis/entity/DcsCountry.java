package com.czy.seed.mybatis.entity;

import javax.persistence.Id;

/**
 * Created by 003914[panlc] on 2017-08-23.
 */
public class DcsCountry {
    @Id
    private Long id;

    private String countryEnName = "";

    private String countryIataThree = "";

    private String countryIataTwo = "";

    private Integer passportvalid;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCountryEnName() {
        return countryEnName;
    }

    public void setCountryEnName(String countryEnName) {
        this.countryEnName = countryEnName;
    }

    public String getCountryIataThree() {
        return countryIataThree;
    }

    public void setCountryIataThree(String countryIataThree) {
        this.countryIataThree = countryIataThree;
    }

    public String getCountryIataTwo() {
        return countryIataTwo;
    }

    public void setCountryIataTwo(String countryIataTwo) {
        this.countryIataTwo = countryIataTwo;
    }

    public Integer getPassportvalid() {
        return passportvalid;
    }

    public void setPassportvalid(Integer passportvalid) {
        this.passportvalid = passportvalid;
    }
}
