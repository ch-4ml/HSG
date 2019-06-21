<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="https://cloud.tinymce.com/5/tinymce.min.js?apiKey=iiyaoh4ovlz6z3aafb6vdpqtllt555a3g3loxoh2dwetyw3e"></script>
<script>
tinymce.init({
    selector: 'textarea#content',
    menubar: false,
    plugins: ['autolink autosave code link table textcolor autoresize hr image imagetools fullpage lists advlist'],
    toolbar: "undo redo | fontsizeselect | forecolor bold underline italic code | alignleft aligncenter alignright alignjustify | numlist bullist outdent indent | table link image code hr",
    fullpage_default_font_family: "NanumSquareRound",
    font_formats: "나눔스퀘어라운드=NanumSquareRound;",
    fontsize_formats: "11px 12px 14px 16px 18px 24px 36px 48px",
   	file_picker_types: 'image',
    file_picker_callback: function (cb, value, meta) {
        var input = document.createElement('input');
        input.setAttribute('type', 'file');
        input.setAttribute('accept', 'image/*');
        input.setAttribute('enctype', 'multipart/form-data');
        input.onchange = function () {
        	var file = this.files[0];

	     	var reader = new FileReader();
	        reader.onload = function () {

  				var id = 'blobid' + (new Date()).getTime();
  			    var blobCache =  tinymce.activeEditor.editorUpload.blobCache;
  			    var base64 = reader.result.split(',')[1];
  			    var blobInfo = blobCache.create(id, file, base64);
  			    blobCache.add(blobInfo);

	            /* call the callback and populate the Title field with the file name */
	        	cb(blobInfo.blobUri(), { title: file.name });
	        };
	        reader.readAsDataURL(file);
        };
        input.click();
    },
  	setup: function (editor) {
	    //geditor = editor;
	    editor.on('change', function (e) {
	    	editor.save();
    	});
	    editor.on('KeyDown', function (e) {
	        if ((e.keyCode == 8 || e.keyCode == 46) && editor.selection) { // delete & backspace keys
	            var selectedNode = editor.selection.getNode(); // get the selected node (element) in the editor
	            if (selectedNode && selectedNode.nodeName == 'IMG') {
	                $.ajax({
	                	url: "delete.fi",
	                	data: {"origin": selectedNode.src},
	                	dataType: "json",
	                	success: function(data) {
							alert("됐다")	;
	                	}
	                }); // A callback that will let me invoke the deletion of the image on the server if appropriate for the image source.
	            }
	        }
	    });
		 $('#content_form').click(function(e) {
   			//e.preventDefault();
   			editor.uploadImages(function(success) {
       		});
    	});
  	},
    images_upload_url: 'update.fi',
    images_upload_base_path: 'http://ark9659.cafe24.com/HSG/resources/uploadFiles/common_upload_files/',
    images_reuse_filename: true
});
</script>
</head>
<body>

</body>
</html>