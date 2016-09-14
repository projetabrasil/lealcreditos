function consultaCep(cep) {
         var cep_code = $('#cep').val();
         if (cep_code.length <= 0)
               return;
         $.get("http://apps.widenet.com.br/busca-cep/api/cep.json", {
               code : cep_code
         }, function(result) {
               if (result.status != 1) {
                      alert(result.message || "Houve um erro desconhecido");
                      return;
               }
               var cep = result.code;
               var estado = result.state;
               var cidade = result.city;
               var bairro = result.district;
               var endereco = result.address;
               setarValoresCEP([ {
                      name : 'cep',
                      value : cep
               }, {
                      name : 'estado',
                      value : estado
               }, {
                      name : 'cidade',
                      value : cidade
               }, {
                      name : 'bairro',
                      value : bairro
               }, {
                      name : 'endereco',
                      value : endereco
               } ]);
         });
  }

function setfocus(id){
	document.getElementById(id).focus()
	}

function log(msg) {
    setTimeout(function() {
        throw new Error(msg);
    }, 0);
}

$(document).ready(function() {
    $(":input:visible:enabled:first").focus()
});