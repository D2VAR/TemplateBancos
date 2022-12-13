package com.example.pixitau.dto;

import com.example.pixitau.model.ChavePix;
import lombok.Data;


import java.util.UUID;

@Data
public class ChavePixResponse {

    private UUID id;
    public ChavePixResponse (ChavePix chavePix){
        this.id= chavePix.getId();
    }

}
