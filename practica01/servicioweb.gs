// Regresar los valores proporcionados con get
  const doGet = (e = {}) => {
    const { parameter } = e;
    const { nombre = 'Alfredo', apellido = 'Issac', tel, dir} = parameter;
    const salida = `Hola ${nombre} ${apellido} ${tel} ${dir}`;
    return ContentService.createTextOutput(salida);
  };

// Regresar los elementos del payload por el post
  const doPost = (e = {}) => {
    const { parameter } = e;
    const { nombre = 'Alfredo', apellido = 'Issac', tel, dir } = parameter;
    const salida = `Hola ${nombre} ${apellido} ${tel} ${dir}`;
    return ContentService.createTextOutput(salida);
  };

  //Actualizaciones de este archivo se pueden encontrar en:
  //https://script.google.com/macros/s/AKfycbx_FZ507t4gm-HvDhyTHxQ-jXUNV5te2Vzc3R4Y3xVceeQZtql0ysnSaHRUgGrM5Ylu/exec