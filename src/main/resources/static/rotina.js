function propertiesService(filter) {
	$.ajax({
		type : "GET",
		url : "/api/find/"+filter,
		data : '$format=json',
		dataType : 'json',
		success : function(data) {
			var total=0;
			$.each(data, function(_d, results) {
				$("#propTable tbody").append(
					"<tr>"
					+ "<td class=\"text-nowrap\">"
					+ results.codigo
					+ "</td>" + "<td class=\"text-nowrap\">"
					+ results.numero_de_serie
					+ "</td>" + "<td class=\"text-nowrap\">"
					+ results.status
					+ "</td>"
					+ "</tr>")
				total++;
			})
			$("results").text(total+" encontrados");
		}
	});
};