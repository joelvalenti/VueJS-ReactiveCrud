<template>
  <div>
    <b-container class="mt-2 tarjeta animated fadeIn">
      <h1 class="mx-auto" style="text-align:center">ABM Instrumentos</h1>
      <b-button
        class="float-right"
        variant="primary"
        @click="resetModal"
        v-b-modal.modal-prevent-closing>Agregar</b-button>
      <br />
      <br>
      <b-modal id="modal-prevent-closing" ref="modal" title="Instrumentos" hide-footer>
        <b-form ref="form" @submit="onSubmit">
          <b-form-group id="input-group-1">
            <b-form-input
              id="instrumento"
              type="text"
              v-model="instrumento.instrumento"
              placeholder="Nombre del instrumento"
            ></b-form-input>
          </b-form-group>
          <b-form-group id="input-group-2">
            <b-form-input
              id="marca"
              type="text"
              v-model="instrumento.marca"
              placeholder="Marca"
              title="Ingrese la marca del instrumento..."
            ></b-form-input>
          </b-form-group>
          <b-form-group id="input-group-3">
            <b-form-input
              id="modelo"
              type="text"
              v-model="instrumento.modelo"
              placeholder="Modelo"
              title="Ingrese el modelo del instrumento..."
            ></b-form-input>
          </b-form-group>
          <b-form-group id="input-group-4">
            <b-form-input
              id="precio"
              type="number"
              v-model="instrumento.precio"
              placeholder="Precio"
              title="Ingrese solamente números..."
            ></b-form-input>
          </b-form-group>
          <b-form-group id="input-group-5">
            <b-form-input
              id="cantidadVendida"
              type="number"
              v-model="instrumento.cantidadVendida"
              placeholder="Cantidad Vendida"
              title="Ingrese solamente numeros..."
            ></b-form-input>
          </b-form-group>
          <b-form-group id="input-group-6">
            <b-form-input
              id="costoEnvio"
              type="text"
              v-model="instrumento.costoEnvio"
              placeholder="Costo de Envio (si es gratis ingresar la letra G)"
              title="Ingrese costo de envio del instrumento..."
            ></b-form-input>
          </b-form-group>
          <b-form-group id="input-group-7">
            <b-form-input
              id="descripcion"
              type="text"
              v-model="instrumento.descripcion"
              placeholder="Descripcion"
              title="Ingrese la descripcion del instrumento..."
            ></b-form-input>
          </b-form-group>
          <div id="image">
            <input type="file" ref="file" @change="onFileSelected" required />
          </div>
          <br />
          <div class="modal-footer">
            <b-button variant="secondary" class="float-right" @click="resetModal">Cerrar</b-button>
            <b-button type="submit" variant="primary" class="float-right">Guardar</b-button>
          </div>
        </b-form>
      </b-modal>
      <b-col lg="7" class="my-2">
        <b-form-group
          label="Buscar"
          label-cols-sm="1"
          label-align-sm="left"
          label-size="large"
          label-for="filterInput"
          class="mb-0"
        >
          <b-input-group size="large">
            <b-form-input v-model="filter" type="search" id="filterInput" placeholder="Buscar..."></b-form-input>
            <b-input-group-append>
              <b-button :disabled="!filter" @click="filter = ''">Limpiar</b-button>
            </b-input-group-append>
          </b-input-group>
        </b-form-group>
      </b-col>
  
      <b-table
        id="my-table"
        head-variant="dark"
        striped
        :per-page="perPage"
        :current-page="currentPage"
        :sort-by.sync="sortBy"
        :sort-desc.sync="sortDesc"
        :fields="fields"
        :items="instrumentosData"
        :filter="filter"
        :filterIncludedFields="filterOn"
        @filtered="onFiltered"
      >
        <template v-slot:cell(actions)="{item}">
          <b-button
            class="mr-1"
            variant="warning"
            v-b-modal.modal-prevent-closing
            @click="handleGetOne(item.id)"
          >Editar</b-button>
          <b-button class="mr-1" variant="danger" @click="handleDelete(item.id)">Eliminar</b-button>
        </template>
      </b-table>
      <b-pagination
        v-model="currentPage"
        :total-rows="rows"
        :per-page="perPage"
        aria-controls="my-table"
      ></b-pagination>
    </b-container>
  </div>
</template>

<style>
.minicard {
  width: 80%;
  margin: 0 auto;
}
</style>
<script>
import InstrumentoDataService from "@/service/InstrumentoDataService.js";
import Swal from "sweetalert2";

export default {
  computed: {
    rows() {
      return this.instrumentosData.length;
    }
  },
  data() {
    return {
      instrumento: {
        id: "",
        instrumento: "",
        marca: "",
        modelo: "",
        imagen: "",
        precio: "",
        costoEnvio: "",
        cantidadVendida: "",
        descripcion: ""
      },
      selected: null,
      title: "",
      sortBy: "id",
      sortDesc: false,
      editFlag: false,
      fields: [
        { key: "id", sortable: true },
        { key: "instrumento", sortable: true },
        { key: "marca", sortable: true },
        { key: "modelo", sortable: true },
        { key: "precio", sortable: true },
        { key: "costoEnvio", sortable: true },
        { key: "cantidadVendida", sortable: true },
        { key: "descripcion", sortable: true },
        { key: "actions", label: "Actions" }
      ],
      perPage: 5,
      currentPage: 1,
      instrumentosData: [],
      filter: null,
      filterOn: [],
      ids: [],
      selectedFile: null,
      UploadValue: 0,
      picture: null
    };
  },
  mounted() {
    this.getInstrumentos();
    this.totalRows = this.instrumentosData.length;
  },
  methods: {
    getInstrumentos() {
      InstrumentoDataService.getAll()
        .then(response => {
          this.instrumentosData = response.data;
        })
        .catch(e => {
          console.log(e);
        });
    },
    resetModal() {
      console.log("Hi resetModal");
      this.editFlag = false;
      this.instrumento = {};
      this.$bvModal.hide("modal-prevent-closing");
    },
    onSubmit(evt) {
      evt.preventDefault();
      console.log("Hi onSubmit");
      if (this.editFlag === false) {
        console.log("Hi onSubmitAgregar");
        this.handleAgregar();
      } else {
        console.log("Hi onSubmitUpdate");
        this.handleUpdate();
        this.editFlag = false;
      }
    },
    handleAgregar() {
      console.log("Hi handleAgregar");
        this.upload();
        this.instrumento.imagen = "/images/" + this.selectedFile.name;
      console.log("Agregar img: " + this.instrumento.imagen);
      InstrumentoDataService.create(this.instrumento)
        .then(res => {
          this.instrumentosData.push(res.data);
          this.$nextTick(() => {
            this.$bvModal.hide("modal-prevent-closing");
          });
          Swal.fire("Agregado!", "Registro agregado con éxito!", "success");
        })
        .catch(e => {
          console.log("Error agregar imagen: " + e);
          Swal.fire({
            title: "¡Error al agregar!",
            text: "No se pudo agregar el registro",
            icon: "error",
            confirmButtonText: "Cool"
          });
        });
    },
    handleDelete(id) {
      const swalWithBootstrapButtons = Swal.mixin({
        customClass: {
          confirmButton: "btn btn-success",
          cancelButton: "btn btn-danger"
        },
        buttonsStyling: false
      });
      swalWithBootstrapButtons
        .fire({
          title: "Estás seguro?",
          text: "No podrás recuperar el registro!",
          icon: "warning",
          showCancelButton: true,
          confirmButtonText: "Si, eliminalo!",
          cancelButtonText: "No, cancelar!",
          reverseButtons: true
        })
        .then(result => {
          if (result.value) {
            InstrumentoDataService.delete(id)
              .then(() => {
                const index = this.instrumentosData.findIndex(
                  instrumento => instrumento.id === id
                );
                if (~index) {
                  this.instrumentosData.splice(index, 1);
                  Swal.fire(
                    "Eliminado!",
                    "Registro eliminado correctamente!",
                    "success"
                  );
                }
              })
              .catch(e => {
                Swal.fire({
                  title: "Error al eliminar!",
                  text: "No se pudo eliminar el registro",
                  icon: "error",
                  confirmButtonText: "Cool"
                });
                console.log("Error eliminar: " + e);
              });
          } else if (result.dismiss === Swal.DismissReason.cancel) {
            swalWithBootstrapButtons.fire(
              "Cancelado",
              "Tu registro se encuentra a salvo :)",
              "error"
            );
          }
        });
    },
    handleGetOne(id) {
      console.log("Hi handleGetOne");
      this.editFlag = true;
      InstrumentoDataService.getOne(id)
        .then(data => {
          this.instrumento = data.data;
        })
        .catch(e => {
          console.log("Error en el getOne: " + e);
        });
    },
    handleUpdate() {
      console.log("Hi handleUpdate");
      this.upload();
      this.instrumento.imagen = "/images/" + this.selectedFile.name;
      InstrumentoDataService.update(this.instrumento.id, this.instrumento)
        .then((res) => {
          const currentIndex = this.instrumentosData.findIndex(instrumento => instrumento.id === this.instrumento.id);
          this.instrumentosData.splice(currentIndex, 1, res.data)
          this.$nextTick(() => {
            this.$bvModal.hide("modal-prevent-closing");
          });
          Swal.fire(
            "Actualizado!",
            "Registro actualizado con éxito!",
            "success"
          );
        })
        .catch(e => {
          console.log("Error update: " + e);
          Swal.fire({
            title: "Error al actualizar!",
            text: "No se pudo actualizar el registro :(",
            icon: "error",
            confirmButtonText: "Ok"
          });
        });
    },
    onFileSelected(event) {
      this.selectedFile = event.target.files[0];
    },
    async upload() {
      let formData = new FormData();
      formData.append("file", this.selectedFile);
      await fetch("http://localhost:9000/api/v1/instrumento/uploadImg", {
        mode: "no-cors",
        method: "POST",
        body: formData
        }).then(response => {
          this.instrumento.imagen = response;
          return this.instrumento.imagen;
        })
        .catch(e => {
          console.log(e);
        });
    },
    onFiltered: filteredItems => {
      this.totalRows = filteredItems.length;
      this.currentPage = 1;
    }
  }
};
</script>