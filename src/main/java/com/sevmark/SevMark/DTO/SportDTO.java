package com.sevmark.SevMark.DTO;

import com.sevmark.SevMark.model.Local;

public class SportDTO {

    private Long id;
    private String name;
    private Local local;

    public SportDTO(Long id, String name, Local local) {
        this.id = id;
        this.name = name;
        this.local = local;
    }

    public SportDTO() {}

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
