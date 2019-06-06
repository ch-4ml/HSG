<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="https://cloud.tinymce.com/5/tinymce.min.js?apiKey=iiyaoh4ovlz6z3aafb6vdpqtllt555a3g3loxoh2dwetyw3e"></script>
<script>

    $(function() {
    	$("#update").click(function() {
    		var oldContent = $('#contents').html();
    		$("#contents").html("<textarea id='text' name='text'>" + oldContent + "</textarea><br><div style='text-align:center;'><input type='submit' value='수정'></div>");
    		tinymce.init({
    		    selector: 'textarea',
    		    menubar: false,
    		    language_url: 'tinymce/ko_KR.js',
    		    plugins: ['autolink autosave code link media table textcolor autoresize hr image imagetools fullpage'],
    		    toolbar: "undo redo | fontsizeselect | forecolor bold underline italic code | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent | table link media image code hr",
    		    fullpage_default_font_family: "NanumSquareRound",
    		    font_formats: "나눔스퀘어라운드=NanumSquareRound;",
    		    fontsize_formats: "11px 12px 14px 16px 18px 24px 36px 48px"
   		    	file_picker_types: 'image',
   			    file_picker_callback: function (cb, value, meta) {
   			        var input = document.createElement('input');
   			        input.setAttribute('type', 'file');
   			        input.setAttribute('accept', 'image/*');

   			        /*
   			          Note: In modern browsers input[type="file"] is functional without
   			          even adding it to the DOM, but that might not be the case in some older
   			          or quirky browsers like IE, so you might want to add it to the DOM
   			          just in case, and visually hide it. And do not forget do remove it
   			          once you do not need it anymore.
   			        */

   			        input.onchange = function () {
   			          var file = this.files[0];

   			          var reader = new FileReader();
   			          reader.onload = function () {
   			            /*
   			              Note: Now we need to register the blob in TinyMCEs image blob
   			              registry. In the next release this part hopefully won't be
   			              necessary, as we are looking to handle it internally.
   			            */
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
   			      }
    		});
    		$("#updateButton").html("");
    	});
    });
    
</script>
</head>
<body>

</body>
</html>