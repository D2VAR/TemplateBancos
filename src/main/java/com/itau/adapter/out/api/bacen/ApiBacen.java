package com.itau.adapter.out.api.bacen;

import com.itau.adapter.out.api.bacen.dto.ApiBacenResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="api-bacen", url="${bacen.api.route}")
public interface ApiBacen{
        @GetMapping("/chave-pix/{valorChave}")
        ApiBacenResponse chavePixExists(@PathVariable String valorChave);
}
