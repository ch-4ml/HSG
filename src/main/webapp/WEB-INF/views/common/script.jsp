<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
	<!-- input style 관련 스크립트 -->
	<script src="./resources/js/classie.js"></script>
	<script>
	(function() {
		// trim polyfill : https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/String/Trim
		if (!String.prototype.trim) {
			(function() {
				// Make sure we trim BOM and NBSP
				var rtrim = /^[\s\uFEFF\xA0]+|[\s\uFEFF\xA0]+$/g;
				String.prototype.trim = function() {
					return this.replace(rtrim, '');
				};
			})();
		}

//		[].slice.call( document.querySelectorAll( 'input.input__field' ) ).forEach( function( inputEl ) {
//			
//			// in case the input is already filled..
//			if( inputEl.value.trim() !== '' ) {
//				classie.add( inputEl.parentNode, 'input--filled' );
//			}

			// events:
//			inputEl.addEventListener( 'focus', onInputFocus );
//			inputEl.addEventListener( 'blur', onInputBlur );
//		} );
		
		[].slice.call( document.querySelectorAll( 'input:not([type="file"]).input__field' ) ).forEach( function( inputEl ) {
			
			// in case the input is already filled..
			if( inputEl.value.trim() !== '' ) {
				classie.add( inputEl.parentNode, 'input--filled' );
			}

			// events:
			inputEl.addEventListener( 'focus', onInputFocus );
			inputEl.addEventListener( 'blur', onInputBlur );
		} );
		
		[].slice.call( document.querySelectorAll( 'textarea.input__field' ) ).forEach( function( inputEl ) {
			// in case the input is already filled..
			if( inputEl.value.trim() !== '' ) {
				classie.add( inputEl.parentNode, 'input--filled' );
			}

			// events:
			inputEl.addEventListener( 'focus', onInputFocus );
			inputEl.addEventListener( 'blur', onInputBlur );
		} );

		function onInputFocus( ev ) {
			classie.add( ev.target.parentNode, 'input--filled' );
		}

		function onInputBlur( ev ) {
			if( ev.target.value.trim() === '' ) {
				classie.remove( ev.target.parentNode, 'input--filled' );
			}
		}
	})();
	</script>
	<!-- input style 관련 스크립트 -->
    <script src="./resources/js/vendor/jquery-2.2.4.min.js"></script>
	<script src="./resources/js/vendor/bootstrap-4.1.3.min.js"></script>
    <script src="./resources/js/vendor/wow.min.js"></script>
    <script src="./resources/js/vendor/owl-carousel.min.js"></script>
    <script src="./resources/js/vendor/jquery.datetimepicker.full.min.js"></script>
    <!-- <script src="./resources/js/vendor/jquery.nice-select.min.js"></script>  -->
    <script src="./resources/js/vendor/superfish.min.js"></script>
    <script src="./resources/js/main.js"></script>

	<script src="./resources/js/vendor/jquery.min.js"></script>
	<script src="./resources/js/vendor/skel.min.js"></script>
	<script src="./resources/js/vendor/util.js"></script>
	<script src="./resources/js/main2.js"></script>
	<script src="./resources/js/selectFx.js"></script>
	<script>
			(function() {
				[].slice.call( document.querySelectorAll( 'select.cs-select' ) ).forEach( function(el) {	
					new SelectFx(el);
				} );
			})();
	</script>
</body>
</html>