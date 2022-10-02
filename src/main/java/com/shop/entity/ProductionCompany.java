package com.shop.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
public class ProductionCompany {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long pcoId;
    private String name;

    @OneToMany(mappedBy = "prodPco")
    @JsonIgnore
    private Set<Products> pcoPod;

    public ProductionCompany() {
    }

    public ProductionCompany(Long pcoId, String name) {
        this.pcoId = pcoId;
        this.name = name;
    }

    public Long getPcoId() {
        return pcoId;
    }

    public void setPcoId(Long pcoId) {
        this.pcoId = pcoId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Products> getPcoPod() {
        return pcoPod;
    }

    public void setPcoPod(Set<Products> pcoPod) {
        this.pcoPod = pcoPod;
    }
}
