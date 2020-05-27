<template>
  <div>
    <app-loading v-if="loading"></app-loading>
    <b-container>
      <div
        v-if="instrumentosData.length == 0"
        class="alert alert-success mt-5 text-center animated fadeIn"
        role="alert"
      >
        <h4 class="alert-heading">No se encontraron Registros</h4>
      </div>
    </b-container>
    <div v-if="!loading" class="productos">
      <b-container v-if="instrumentosData.length > 0">
        <b-card>
          <div
            class="cards animated fadeIn"
            v-for="instrumento in instrumentosData"
            :key="instrumento.id"
          >
            <instrumento-item
              :instrumentoParam="instrumento"
            ></instrumento-item>
          </div>
        </b-card>
      </b-container>
    </div>
  </div>
</template>

<style>
.animated {
  -webkit-animation-duration: 1s;
  animation-duration: 1s;
  -webkit-animation-fill-mode: both;
  animation-fill-mode: both;
}

.fast {
  -webkit-animation-duration: 0.4s;
  animation-duration: 0.4s;
  -webkit-animation-fill-mode: both;
  animation-fill-mode: both;
}

@keyframes fadeIn {
  from {
    opacity: 0;
  }
  to {
    opacity: 1;
  }
}

.fadeIn {
  animation-name: fadeIn;
}
</style>

<script>
import Instrumento from "@/components/Instrumento.vue";
import Loading from "@/components/Loading.vue";
import InstrumentoDataService from "@/service/InstrumentoDataService.js";

export default {
  name: "Productos",
  components: {
    "instrumento-item": Instrumento,
    "app-loading": Loading
  },
  mounted() {
    this.getInstrumentos();
  },
  data() {
    return {
      instrumentosData: [],
      ids: [],
      loading: true,
      empty: false
    };
  },
  methods: {
    getInstrumentos() {
      this.loading = false;
      InstrumentoDataService.getAll()
        .then(response => {
          this.instrumentosData = response.data;
        })
        .catch(e => {
          console.log(e);
        });
    },
    refreshList() {
      this.getInstrumentos();
      this.currentTutorial = null;
      this.currentIndex = -1;
    }
  }
};
</script>
