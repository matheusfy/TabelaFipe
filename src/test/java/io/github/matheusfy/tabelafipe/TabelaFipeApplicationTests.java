package io.github.matheusfy.tabelafipe;

import io.github.matheusfy.tabelafipe.model.Fipe;
import io.github.matheusfy.tabelafipe.service.FipeService;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TabelaFipeApplicationTests {

    @Test
    void contextLoads() {
        FipeService fipeService = null;
        fipeService = new FipeService();
        assertThat(fipeService).isNotNull();
    }

    void TestFipeServiceCreation(){

    }
}
