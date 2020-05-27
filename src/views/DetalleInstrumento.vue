<template>
    <b-container  class="mt-5 tarjeta">
      <b-row>
        <b-col class="mt-2">
          <img :src="instrumentoEncontrado.imagen" width="80%" height="80%" />
        </b-col>
        <b-col class="mt-2">
          <b-row>
            <h5>{{instrumentoEncontrado.cantidadVendida}} vendidos</h5>
          </b-row>
          <b-row style="margin-top: 1%">
            <h1>{{instrumentoEncontrado.instrumento}}</h1>
          </b-row>
          <b-row>
            <h2>${{instrumentoEncontrado.precio}}</h2>
          </b-row>
          <b-row>
            <h4>Marca: {{instrumentoEncontrado.marca}}</h4>
          </b-row>
          <b-row>
            <h4>Modelo: {{instrumentoEncontrado.modelo}}</h4>
          </b-row>
          <b-row>
            <div v-if="instrumentoEncontrado.costoEnvio == 'G'">
              <img :src="'/images/camion.png'" width="13%" class="mb-1" />
              <span class="envioGratis">Envío gratis a todo el país</span>
            </div>
            <div v-else>
              <h4 class="envioPago">
                Costo de Envio Interior de Argentina: ${{
                  instrumentoEncontrado.costoEnvio
                }}
              </h4>
            </div>
          </b-row>
          <br>
          <b-row>
        <b-button class="btn" variant="primary" href="/">Agregar al carrito</b-button>
        <b-button style="margin-left: 1%" href="/productos" variant="secondary">Volver</b-button>
      </b-row>
        </b-col>
      </b-row>
      <br>
      <b-row>
          <h4>Descripción: {{ instrumentoEncontrado.descripcion }}</h4>
        </b-row>
    </b-container>
</template>
<style>
.envioGratis {
  color: green;
}
.envioPago {
  color: orange;
}
.tarjeta {
  border-radius: 20px;
  margin-bottom: 1%;
}
</style>
<script>
import InstrumentoDataService from "@/service/InstrumentoDataService.js";

export default {
  name: "DetalleInstrumento",
  mounted() {
    this.getInstrumentoXId();
  },
  data() {
    return {
      instrumentoEncontrado: []
    };
  },
  methods: {
    async getInstrumentoXId() {
      await InstrumentoDataService.getOne(this.$route.params.id)
        .then(response => {
          this.instrumentoEncontrado = response.data;
        })
        .catch(e => {
          console.log(e);
        });
    }
  }
};
</script>