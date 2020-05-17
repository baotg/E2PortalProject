function getMainClasses(){
	var faculty = document.getElementById("faculty-select");
	var facultyId = faculty.options[faculty.selectedIndex].value;
	if(facultyId=='empty'){
		var url = '/mainclass/search?id=';
		$('#mainclass-class-table').load(url);
		return;
	}
	var url = '/mainclass/search?id=' + facultyId;
	$('#main-class-table').load(url);
}