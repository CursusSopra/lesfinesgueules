/*
 * @author Julien J
 * 
 * Fichier de fonctions javascript pour la modification de la base de donnée en mode inline Editing
 */

//turn to inline mode
$.fn.editable.defaults.mode = 'popup';

// Zone de test
$(document).ready(function() {
	
	$('#produit').click(function() {
		loadProduit();
	});
	
	
	var idType1 = $('#designation').val();
	var designation = $('#hiddenData').attr("data-designation");
	var description = $('#hiddenData').attr("data-description");
	var prix = $('#hiddenData').attr("data-prix");
	var action = $('#hiddenData').attr("data-lienUrl");
	 $('#address').editable({
		 url: '/post',
		 title: 'Enter city, street and building #',
		 value: {

			designation: designation,
			description: description,
			prix: prix
		 }
		 });
        
});


function loadType2() {	
	var idType1 = $('#type1').val();
	var action = $('#hiddenData').attr("data-lienUrl");
	console.log(idType1);
	$.getJSON('getJSONType2?idType1=' + idType1)
	.success(function (data) {
        var szOption = '';
        $.each(data.listType2, function (index, elt) {
        	
        });

        $('#retourJSON').html(szOption);
        $('.list-group-item a').editable({
    		
    		clear : false,
    	    params: function(params) {
    	        //originally params contain pk, name and value
    	        params.a = 1;
    	        return params;
    	        }

    	});
    })
    .fail(function () {
    });
}

//Fonction permettant de créer le formulaire à champs multiples en popup
(function ($) {
	"use strict";
	var Address = function (options) {
	this.init('address', options, Address.defaults);
	};
	// inherit from Abstract input
	$.fn.editableutils.inherit(Address, $.fn.editabletypes.abstractinput);
	$.extend(Address.prototype, {
	/**
	 * Renders input from tpl
	 * 
	 * @method render()
	 */
	render: function() {
	this.$input = this.$tpl.find('input');
	},
	/**
	 * Default method to show value in element. Can be overwritten by display
	 * option.
	 * 
	 * @method value2html(value, element)
	 */
	value2html: function(value, element) {
	if(!value) {
	$(element).empty();
	return;
	}
	var html = $('<div>').text(value.designation).html() + ', ' + $('<div>').text(value.description).html() + ' st., bld. ' + $('<div>').text(value.prix).html();
	$(element).html(html);
	},
	/**
	 * Gets value from element's html
	 * 
	 * @method html2value(html)
	 */
	html2value: function(html) {
	/*
	 * you may write parsing method to get value by element's html e.g. "Moscow,
	 * st. Lenina, bld. 15" => {city: "Moscow", street: "Lenina", building:
	 * "15"} but for complex structures it's not recommended. Better set value
	 * directly via javascript, e.g. editable({ value: { city: "Moscow", street:
	 * "Lenina", building: "15" } });
	 */
	return null;
	},
	/**
	 * Converts value to string. It is used in internal comparing (not for
	 * sending to server).
	 * 
	 * @method value2str(value)
	 */
	value2str: function(value) {
	var str = '';
	if(value) {
	for(var k in value) {
	str = str + k + ':' + value[k] + ';';
	}
	}
	return str;
	},
	/*
	 * Converts string to value. Used for reading value from 'data-value'
	 * attribute. @method str2value(str)
	 */
	str2value: function(str) {
	/*
	 * this is mainly for parsing value defined in data-value attribute. If you
	 * will always set value by javascript, no need to overwrite it
	 */
	return str;
	},
	/**
	 * Sets value of input.
	 * 
	 * @method value2input(value)
	 * @param {mixed}
	 *            value
	 */
	value2input: function(value) {
	if(!value) {
	return;
	}
	this.$input.filter('[name="designation"]').val(value.designation);
	this.$input.filter('[name="description"]').val(value.description);
	this.$input.filter('[name="prix"]').val(value.prix);
	},
	
	/**
	 * Returns value of input.
	 * 
	 * @method input2value()
	 */
	input2value: function() {
	return {
	city: this.$input.filter('[name="designation"]').val(),
	street: this.$input.filter('[name="description"]').val(),
	building: this.$input.filter('[name="prix"]').val()
	};
	},
	/**
	 * Activates input: sets focus on the first field.
	 * 
	 * @method activate()
	 */
	activate: function() {
	this.$input.filter('[name="designation"]').focus();
	},
	/**
	 * Attaches handler to submit form in case of 'showbuttons=false' mode
	 * 
	 * @method autosubmit()
	 */
	autosubmit: function() {
	this.$input.keydown(function (e) {
	if (e.which === 13) {
	$(this).closest('form').submit();
	}
	});
	}
	});
	Address.defaults = $.extend({}, $.fn.editabletypes.abstractinput.defaults, {
	tpl: '<div class="editable-address"><label><span>Designation: </span><input type="text" name="designation" class="form-control"></label></div>'+
	'<div class="editable-address"><label><span>Description: </span><input type="textarea" name="description" class="form-control"></label></div>'+
	'<div class="editable-address"><label><span>Prix: </span><input type="number" name="prix" step="0.01" min="0"  class="form-control"></label></div>',
	inputclass: ''
	});
// <input type="number" step="0.01" min="0" class="form-control" id="idPrix"
// name="prix" value="<s:property value="prix" />" >
	$.fn.editabletypes.address = Address;
	}(window.jQuery));

