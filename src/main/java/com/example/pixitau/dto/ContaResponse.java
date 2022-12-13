package com.example.pixitau.dto;


import com.example.pixitau.model.Conta;
import lombok.Data;

import java.util.UUID;
@Data
public class ContaResponse {


        private UUID id;
        public ContaResponse(Conta conta){
            this.id= conta.getId();
        }

    }

