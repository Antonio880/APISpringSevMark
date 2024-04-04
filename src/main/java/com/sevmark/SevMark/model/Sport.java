package com.sevmark.SevMark.model;

import jakarta.persistence.*;


@Entity
@Table(name = "marks")
public class Sport {
    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToOne
    @JoinColumn(name = "local_id")
    private Local local;

    public Sport(Long id, String name, Local local) {
        this.id = id;
        this.name = name;
        this.local = local;
    }

    public Sport() {}

    public Local getLocal(){
        return local;
    }

    public void setLocal(Local local){
        this.local = local;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
