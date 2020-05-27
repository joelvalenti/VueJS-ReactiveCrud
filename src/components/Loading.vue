<template>
  <div class="row text-center animated fadeIn centrado">
    <div class="col">
      <i class="fas fa-sync-alt fa-spin fa-5x"></i>
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

.centrado {
  margin: 15% auto;
}
</style>

<script>
export default { name: "Loading" };
</script>
