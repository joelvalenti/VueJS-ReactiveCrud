<template>
  <div class="mt-4">
    <b-card :img-src="instrumentoParam.imagen" img-left img-width="26%">
      <b-card-title
        :title="instrumentoParam.instrumento"
        class="titulo"
      ></b-card-title>
      <b-card-text>
        <h5 :precio="instrumentoParam.precio">
          Precio: ${{ instrumentoParam.precio }}
        </h5>
        <h5 v-if="instrumentoParam.costoEnvio == 'G'" class="envioGratis">
          <img :src="'/images/camion.png'" width="4%" class="mb-1" />
          Envio gratis a todo el país
        </h5>
        <h5 v-else class="envioPago">
          Costo de Envio Interior de Argentina: ${{instrumentoParam.costoEnvio}}
        </h5>
        <h6 :cantidadVendida="instrumentoParam.cantidadVendida">
          {{ instrumentoParam.cantidadVendida }} vendidos
        </h6>
      </b-card-text>
      <b-button
        :href="'/detalle/' + instrumentoParam.id"
        variant="primary"
        block
        v-if="!instrumentoParam.disabled"
      >
        <b>Detalle</b>
      </b-button>
    </b-card>
  </div>
</template>
<script>
export default {
  props: ["instrumentoParam"]
};
</script>
<style>
.envioGratis {
  color: green;
}

.envioPago {
  color: orange;
}
</style>
